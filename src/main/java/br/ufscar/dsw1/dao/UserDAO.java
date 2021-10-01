package br.ufscar.dsw1.dao;

import br.ufscar.dsw1.domain.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class UserDAO extends GenericDAO {
    private static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    private static byte[] getHashedPassword(@NotNull @NotBlank String plaintextPassword, @NotNull byte[] salt) {
        KeySpec spec = new PBEKeySpec(plaintextPassword.toCharArray(), salt, 65536, 128);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getBase64Salt() {
        return Base64.getEncoder().encodeToString(UserDAO.getSalt());
    }

    private static String getBase64Password(String plaintextPassword, String salt) {
        byte[] byteSalt = Base64.getDecoder().decode(salt.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(UserDAO.getHashedPassword(plaintextPassword, byteSalt));
    }

    public static void changeUserPassword(int userId, String plaintextPassword) {
        String salt = UserDAO.getBase64Salt();
        String hashedPassword = UserDAO.getBase64Password(plaintextPassword, salt);

        final String query = "UPDATE usuario u SET senha = ?, salt = ? WHERE u.id_usuario = ?;";

        try {
            Connection connection = UserDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, hashedPassword);
            statement.setString(2, salt);
            statement.setInt(3, userId);

            final int affectedRows = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean verifyPassword(@NotNull @NotBlank String plaintextPassword, @NotNull int userId) {
        return false;
    }

    /**
     * @param user             O usuário que será criado. Deve conter os dados que
     *                         serão inseridos no banco de dados. Ao final da
     *                         operação, o ID do [user] será atualizado para
     *                         corresponder ao do banco de dados.
     * @param pureTextPassword A senha em texto puro.
     * @return Um boolean indicando se a operação foi bem-sucedida ou não.
     */
    public static boolean insert(User user, String pureTextPassword) {
        final String query = "insert into usuario(nome, username, email, senha, salt, imagem_perfil) values (?, ?, ?, ?, ?, ?);";

        // Faz o hashing da senha.
        byte[] salt = UserDAO.getSalt();
        byte[] hashedPassword = UserDAO.getHashedPassword(pureTextPassword, salt);

        String base64EncodedPasswordHash = Base64.getEncoder().encodeToString(hashedPassword);
        String base64EncodedSalt = Base64.getEncoder().encodeToString(salt);

        // Tenta salvar no banco de dados.
        try {
            Connection connection = UserDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, new String[] { "id_usuario" });

            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.setString(4, base64EncodedPasswordHash);
            statement.setString(5, base64EncodedSalt);
            statement.setString(6, user.getProfileImageUrl());

            final int affectedRows = statement.executeUpdate();

            // Nenhuma linha afetada, erro ao fazer a inserção.
            if (affectedRows == 0) {
                return false;
            }

            // Lê o resultado da consulta.
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    // Atualiza o id do usuário.
                    user.setId(generatedKeys.getInt(1));

                statement.close();
                connection.close();

                return true;
            } catch (SQLException e) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param username          O nome de usuário em texto puro.
     * @param plaintextPassword A senha em texto puro.
     * @return [true], se a senha está correta. [false], caso contrário.
     */
    public static boolean verifyPassword(String username, String plaintextPassword) {

        final String query = "SELECT salt, senha FROM usuario WHERE username=? LIMIT 1";

        // Tenta efetuar a consulta.
        try {
            Connection connection = UserDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            final String base64EncodedPassword = resultSet.getString("senha");
            final String base64EncodedSalt = resultSet.getString("salt");

            statement.close();
            connection.close();

            byte[] decodedSalt = Base64.getDecoder().decode(base64EncodedSalt);
            byte[] verificationHash = UserDAO.getHashedPassword(plaintextPassword, decodedSalt);

            if (Base64.getEncoder().encodeToString(verificationHash).equals(base64EncodedPassword)) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param username Username do usuário que se deseja buscar.
     * @return Um objeto User se bem sucedido. [null], caso contrário.
     */
    public static User getByUsername(String username) {
        final String query = "SELECT nome, id_usuario, email, imagem_perfil, descricao, reputacao, ra FROM usuario WHERE username=?;";

        User user = null;

        try {
            Connection connection = UserDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id_usuario");
                String name = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String profileImageUrl = resultSet.getString("imagem_perfil");
                String description = resultSet.getString("descricao");
                double reputation = resultSet.getDouble("reputacao");
                int academicRecord = resultSet.getInt("ra");

                statement.close();
                connection.close();

                user = new User(id, name, email, username, profileImageUrl, description, reputation,
                        academicRecord != 0, academicRecord);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static User getByEmail(String email) {
        User user = null;
        final String query = "SELECT nome, id_usuario, email, imagem_perfil, descricao, reputacao, ra FROM usuario WHERE email=?;";

        try {
            Connection connection = UserDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static User getById(Long id) {
        final String query = "SELECT nome, username, id_usuario, email, imagem_perfil, descricao, reputacao, ra FROM usuario WHERE id_usuario=?;";

        User user = null;

        try {
            Connection connection = UserDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String profileImageUrl = resultSet.getString("imagem_perfil");
                String description = resultSet.getString("descricao");
                double reputation = resultSet.getDouble("reputacao");
                int academicRecord = resultSet.getInt("ra");

                statement.close();
                connection.close();

                user = new User(id, name, email, username, profileImageUrl, description, reputation,
                        academicRecord != 0, academicRecord);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static boolean verifyAlmaMater(long userId, int ra) {

        final String query = "UPDATE usuario SET ra = ? WHERE id_usuario = ?;";

        System.out.println("Aquii");

        try {
            Connection connection = GenericDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, ra);
            statement.setLong(2, userId);

            final int affectedRows = statement.executeUpdate();

            System.out.println("Linhas afetadas: " + affectedRows);

            statement.close();
            connection.close();

            return affectedRows == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public static boolean verifyUsername(String username) {
        final String query = "SELECT * FROM usuario WHERE username = ?";

        try {
            Connection connection = UserDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean verifyEmail(String email) {
        final String query = "SELECT * FROM usuario WHERE email = ?";

        try {
            Connection connection = UserDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
