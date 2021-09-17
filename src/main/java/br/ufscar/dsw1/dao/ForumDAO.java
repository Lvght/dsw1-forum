package br.ufscar.dsw1.dao;

import br.ufscar.dsw1.domain.Forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ForumDAO extends GenericDAO {

    public static boolean insert(Forum forum) {
        final String query = "insert into forum(id_dono, escopo_postagem, escopo_acesso, titulo, descricao, icone) values (?, ?, ?, ?, ?, ?);";

        // Tenta salvar no banco de dados.
        try {
            Connection connection = ForumDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, new String[] { "id_forum" });

            statement.setLong(1, forum.getId_dono());
            statement.setInt(2, forum.getEscopo_postagem());
            statement.setInt(3, forum.getEscopo_acesso());
            statement.setString(4, forum.getTitulo());
            statement.setString(5, forum.getDescricao());
            statement.setString(6, forum.getIcone());

            final int affectedRows = statement.executeUpdate();

            // Nenhuma linha afetada, erro ao fazer a inserção.
            if (affectedRows == 0) {
                return false;
            }

            // Lê o resultado da consulta.
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    // Atualiza o id do usuário.
                    forum.setId(generatedKeys.getInt(1));

                statement.close();
                connection.close();

                return true;
            } catch (SQLException e) {
                statement.close();
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public static List<Forum> getAll(Long user_id) {

        List<Forum> listaForuns = new ArrayList<>();

        String query = "SELECT * from Forum";

        try {
            Connection connection = ForumDAO.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_forum");
                Long id_dono = resultSet.getLong("id_dono");
                int escopo_postagem = resultSet.getInt("escopo_postagem");
                int escopo_acesso = resultSet.getInt("escopo_acesso");
                String titulo = resultSet.getString("titulo");
                String descricao = resultSet.getString("descricao");
                String icone = resultSet.getString("icone");
                Long membros = ForumDAO.contarMembros(id);
                Forum forum = new Forum(id_dono, escopo_postagem, escopo_acesso, titulo, descricao, icone);

                Boolean eh_membro = ForumDAO.usuarioIngressa(user_id, id);
                forum.setId(id);
                forum.setEh_membro(eh_membro);
                forum.setMembros(membros);
                listaForuns.add(forum);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaForuns;
    }

    public static List<Forum> getUserForuns(Long id_usuario) {

        List<Forum> listaForuns = new ArrayList<>();

        String query = "SELECT f.* from Forum f JOIN usuario_ingressa_forum u ON f.id_forum = u.id_forum WHERE id_usuario = ?";

        try {
            Connection connection = ForumDAO.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id_usuario);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_forum");
                Long id_dono = resultSet.getLong("id_dono");
                int escopo_postagem = resultSet.getInt("escopo_postagem");
                int escopo_acesso = resultSet.getInt("escopo_acesso");
                String titulo = resultSet.getString("titulo");
                String descricao = resultSet.getString("descricao");
                String icone = resultSet.getString("icone");
                Forum forum = new Forum(id_dono, escopo_postagem, escopo_acesso, titulo, descricao, icone);
                forum.setId(id);
                listaForuns.add(forum);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaForuns;
    }

    public static Forum getForum(Long id) {

        Forum forum = null;

        String query = "SELECT * from Forum WHERE id_forum = ?";

        try {
            Connection connection = ForumDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id_dono = resultSet.getLong("id_dono");
                int escopo_postagem = resultSet.getInt("escopo_postagem");
                int escopo_acesso = resultSet.getInt("escopo_acesso");
                String titulo = resultSet.getString("titulo");
                String descricao = resultSet.getString("descricao");
                String icone = resultSet.getString("icone");
                Long membros = ForumDAO.contarMembros(id);
                forum = new Forum(id_dono, escopo_postagem, escopo_acesso, titulo, descricao, icone);

                forum.setMembros(membros);
                forum.setId(id);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return forum;
    }

    public static boolean usuarioIngressa(Long id_usuario, Long id_forum) {

        String query = "SELECT * from usuario_ingressa_forum WHERE id_usuario = ? AND id_forum = ?";

        try {
            Connection connection = ForumDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id_usuario);
            statement.setLong(2, id_forum);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                connection.close();
                return true;
            } else {
                resultSet.close();
                statement.close();
                connection.close();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean ingressar(Long id_usuario, Long id_forum) {

        String query = "INSERT INTO usuario_ingressa_forum (id_usuario, id_forum) VALUES (?, ?)";

        if (usuarioIngressa(id_usuario, id_forum))
            return true;
        try {
            Connection connection = ForumDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id_usuario);
            statement.setLong(2, id_forum);

            final int affectedRows = statement.executeUpdate();

            // Nenhuma linha afetada, erro ao fazer a inserção.
            if (affectedRows == 0) {
                statement.close();
                connection.close();
                return false;
            } else {
                statement.close();
                connection.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sair(Long id_usuario, Long id_forum) {

        String query = "DELETE FROM usuario_ingressa_forum WHERE id_usuario = ? AND id_forum = ?";

        if (!usuarioIngressa(id_usuario, id_forum))
            return true;
        try {
            Connection connection = ForumDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id_usuario);
            statement.setLong(2, id_forum);

            final int affectedRows = statement.executeUpdate();

            // Nenhuma linha afetada, erro ao fazer a inserção.
            if (affectedRows == 0) {
                statement.close();
                connection.close();
                return false;
            } else {
                statement.close();
                connection.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static long contarMembros(Long id_forum) {

        String query = "SELECT COUNT(*) AS rowcount FROM usuario_ingressa_forum WHERE id_forum = ?";

        try {
            Connection connection = ForumDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id_forum);

            ResultSet resultSet = statement.executeQuery();

            // Nenhuma linha afetada, erro ao fazer a inserção.
            if (resultSet.next()) {
                int count = resultSet.getInt("rowcount");
                resultSet.close();
                statement.close();
                connection.close();
                return count;
            } else {
                resultSet.close();
                statement.close();
                connection.close();
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
