package br.ufscar.dsw1.dao;

import br.ufscar.dsw1.domain.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private static boolean verifyPassword(@NotNull @NotBlank String plaintextPassword, @NotNull int userId) {
        return false;
    }


    /**
     * @param user O usuário que será criado. Deve conter os dados que serão inseridos no banco de dados. Ao final da
     *             operação, o ID do [user] será atualizado para corresponder ao do banco de dados.
     * @param pureTextPassword - A senha em texto puro.
     * @return Um boolean indicando se a operação foi bem-sucedida ou não.
     */
    public static boolean insert(User user, String pureTextPassword) {
        final String query = "insert into usuario(nome, username, email, senha, salt) values (?, ?, ?, ?, ?);";

        // Faz o hashing da senha.
        byte[] salt = UserDAO.getSalt();
        byte[] hashedPassword = UserDAO.getHashedPassword(pureTextPassword, salt);

        String base64EncodedPasswordHash = Base64.getEncoder().encodeToString(hashedPassword);
        String base64EncodedSalt = Base64.getEncoder().encodeToString(salt);

        // Tenta salvar no banco de dados.
        try {
            Connection connection = UserDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, new String[]{"id_usuario"});

            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.setString(4, base64EncodedPasswordHash);
            statement.setString(5, base64EncodedSalt);

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
            }

            catch (SQLException e) {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        final String password = "1234567";
        final String hashedPassword = "N3uLTBbhRaGJymbpvqxmqg==";
        final String salt = "gBtvn8aKkmNVKD3YVDXIEw==";

        final byte[] decodedPassword = Base64.getDecoder().decode(hashedPassword);
        final byte[] decodedSalt = Base64.getDecoder().decode(salt);

        byte[] newHash = UserDAO.getHashedPassword(password, decodedSalt);
        System.out.println(Base64.getEncoder().encodeToString(newHash));
    }
}
