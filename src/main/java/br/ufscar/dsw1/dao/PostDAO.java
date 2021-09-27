package br.ufscar.dsw1.dao;

import br.ufscar.dsw1.domain.Post;

import br.ufscar.dsw1.domain.User;

import br.ufscar.dsw1.domain.Forum;

import br.ufscar.dsw1.domain.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PostDAO extends GenericDAO {

    public static boolean insert(Post post) {
        final String query = "insert into Postagem(id_autor, id_forum, id_topico, titulo, conteudo) values (?, ?, ?, ?, ?);";

        // Tenta salvar no banco de dados.
        try {
            Connection connection = PostDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, new String[]{"id_postagem"});

            statement.setLong(1, post.getId_autor());
            statement.setLong(2, post.getId_forum());

            if (post.getId_topico() == null)
                statement.setNull(3, java.sql.Types.NULL);
            else
                statement.setLong(3, post.getId_topico());

            statement.setString(4, post.getTitulo());
            statement.setString(5, post.getConteudo());

            final int affectedRows = statement.executeUpdate();

            // Nenhuma linha afetada, erro ao fazer a inserção.
            if (affectedRows == 0) {
                return false;
            }

            // Lê o resultado da consulta.
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    // Atualiza o id do usuário.
                    post.setId(Long.valueOf(generatedKeys.getInt(1)));

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

    public static List<Post> getAll(Long page, long sessionUserId) {

        List<Post> listPosts = new ArrayList<>();
        Long offset = (page - 1) * 10;

        final String query = "SELECT p.*, urp.tipo_reacao from Postagem as p LEFT JOIN usuario_reage_postagem as urp " +
                "    on p.id_postagem = urp.id_postagem AND p.id_autor = ? " +
                "    ORDER BY p.id_postagem DESC offset ? limit 10 ;";

        try {
            Connection connection = ForumDAO.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, sessionUserId);
            statement.setLong(2, offset);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_postagem");
                Long id_autor = resultSet.getLong("id_autor");
                Long id_forum = resultSet.getLong("id_forum");
                Long id_topico = resultSet.getLong("id_topico");
                String titulo = resultSet.getString("titulo");
                String conteudo = resultSet.getString("conteudo");
                int sessionUserReaction = resultSet.getInt("tipo_reacao");

                Post post = new Post(id_autor, id_forum, id_topico, titulo, conteudo, sessionUserReaction);
                User user = UserDAO.getById(id_autor);
                Forum forum = ForumDAO.getForum(id_forum);

                Topic topic = null;
                if (id_topico != null)
                    topic = TopicDAO.getTopic(id_topico);
                post.setAutor(user);
                post.setForum(forum);
                post.setTopico(topic);
                post.setId(id);
                listPosts.add(post);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public static List<Post> getTimeline(Long user_id, Long page) {

        List<Post> listPosts = new ArrayList<>();
        Long offset = (page - 1) * 10;

        String query = "SELECT p.*, urp.tipo_reacao FROM usuario_ingressa_forum f " +
                "JOIN postagem p ON p.id_forum = f.id_forum " +
                "LEFT JOIN usuario_reage_postagem urp ON p.id_autor = ? AND urp.id_postagem = p.id_postagem " +
                "WHERE f.id_usuario = ? ORDER BY id_postagem DESC offset ? limit 10;";

        try {
            Connection connection = ForumDAO.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, user_id);
            statement.setLong(2, user_id);
            statement.setLong(3, offset);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_postagem");
                Long id_autor = resultSet.getLong("id_autor");
                Long id_forum = resultSet.getLong("id_forum");
                Long id_topico = resultSet.getLong("id_topico");
                String titulo = resultSet.getString("titulo");
                String conteudo = resultSet.getString("conteudo");
                final int sessionUserReaction = resultSet.getInt("tipo_reacao");

                Post post = new Post(id_autor, id_forum, id_topico, titulo, conteudo, sessionUserReaction);
                User user = UserDAO.getById(id_autor);
                Forum forum = ForumDAO.getForum(id_forum);

                Topic topic = null;
                if (id_topico != null)
                    topic = TopicDAO.getTopic(id_topico);
                post.setAutor(user);
                post.setForum(forum);
                post.setTopico(topic);
                post.setId(id);
                listPosts.add(post);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public static List<Post> getForumPosts(Long id_forum, Long id_topico_filtro, Long filtro, Long page, long sessionUserId) {

        List<Post> listPosts = new ArrayList<>();
        Long offset = (page - 1) * 10;

        String whereCondition = "WHERE id_forum = ? ";
        String query = "SELECT p.*, urp.tipo_reacao from Postagem p left join usuario_reage_postagem urp " +
                "    on p.id_postagem = urp.id_postagem AND urp.id_usuario = ? " +
                "WHERE id_forum = ? ORDER BY p.id_postagem DESC offset ? limit 10;";

        if (id_topico_filtro != 0)
            whereCondition += "AND id_topico = ? ";

        query = "SELECT p.*, urp.tipo_reacao from Postagem p left join usuario_reage_postagem urp " +
                "    on p.id_postagem = urp.id_postagem AND urp.id_usuario = ? " +
                whereCondition + " ORDER BY p.id_postagem DESC offset ? limit 10;";

        try {
            Connection connection = ForumDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, sessionUserId);
            statement.setLong(2, id_forum);

            if (id_topico_filtro != 0) {
                statement.setLong(3, id_topico_filtro);
                statement.setLong(4, offset);
            } else {
                statement.setLong(3, offset);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_postagem");
                Long id_autor = resultSet.getLong("id_autor");
                Long id_topico = resultSet.getLong("id_topico");
                String titulo = resultSet.getString("titulo");
                String conteudo = resultSet.getString("conteudo");
                int sessionUserReaction = resultSet.getInt("tipo_reacao");

                Post post = new Post(id_autor, id_forum, id_topico, titulo, conteudo, sessionUserReaction);
                User user = UserDAO.getById(id_autor);
                Forum forum = ForumDAO.getForum(id_forum);
                Topic topic = null;
                if (id_topico != 0)
                    topic = TopicDAO.getTopic(id_topico);
                post.setAutor(user);
                post.setForum(forum);
                post.setTopico(topic);
                post.setId(id);
                listPosts.add(post);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public static List<Post> getMyPosts(Long user_id, Long page) {

        List<Post> listPosts = new ArrayList<>();
        Long offset = (page - 1) * 10;

        String query = "SELECT p.*, urp.tipo_reacao FROM postagem p LEFT JOIN usuario_reage_postagem urp " +
                "ON p.id_postagem = urp.id_postagem AND urp.id_usuario = ? WHERE p.id_autor = ? " +
                "ORDER BY id_postagem DESC offset ? limit 10;";

        try {
            Connection connection = ForumDAO.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, user_id);
            statement.setLong(2, user_id);
            statement.setLong(3, offset);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_postagem");
                Long id_autor = resultSet.getLong("id_autor");
                Long id_forum = resultSet.getLong("id_forum");
                Long id_topico = resultSet.getLong("id_topico");
                String titulo = resultSet.getString("titulo");
                String conteudo = resultSet.getString("conteudo");
                final int sessionUserReaction = resultSet.getInt("tipo_reacao");

                Post post = new Post(id_autor, id_forum, id_topico, titulo, conteudo, sessionUserReaction);
                User user = UserDAO.getById(id_autor);
                Forum forum = ForumDAO.getForum(id_forum);

                Topic topic = null;
                if (id_topico != null)
                    topic = TopicDAO.getTopic(id_topico);
                post.setAutor(user);
                post.setForum(forum);
                post.setTopico(topic);
                post.setId(id);
                listPosts.add(post);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public static Post getPost(Long postId, Long sessionUserId) {

        Post post = null;

        String query = "SELECT p.*, urp.tipo_reacao from postagem p LEFT JOIN usuario_reage_postagem urp " +
                "ON p.id_postagem = urp.id_postagem AND urp.id_usuario = ? WHERE p.id_postagem = ?";

        try {
            Connection connection = PostDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, sessionUserId);
            statement.setLong(2, postId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id_autor = resultSet.getLong("id_autor");
                Long id_forum = resultSet.getLong("id_forum");
                Long id_topico = resultSet.getLong("id_topico");
                String titulo = resultSet.getString("titulo");
                String conteudo = resultSet.getString("conteudo");
                int sessionUserReaction = resultSet.getInt("tipo_reacao");

                post = new Post(id_autor, id_forum, id_topico, titulo, conteudo, sessionUserReaction);
                User user = UserDAO.getById(id_autor);
                Forum forum = ForumDAO.getForum(id_forum);
                Topic topic = null;
                if (id_topico != 0)
                    topic = TopicDAO.getTopic(id_topico);
                post.setAutor(user);
                post.setForum(forum);
                post.setTopico(topic);
                post.setId(postId);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }

    public static Long countForumPosts(Long id_forum, Long id_topico) {

        Long count = null;
        String query = null;

        if (id_topico == 0)
            query = "SELECT COUNT(*) from postagem WHERE id_forum = ?";
        else
            query = "SELECT COUNT(*) from postagem WHERE id_forum = ? AND id_topico = ?";
        try {
            Connection connection = PostDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id_forum);
            if (id_topico != 0)
                statement.setLong(2, id_topico);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getLong("count");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static Long countPostUserForuns(Long id_user) {

        Long count = null;
        String query = "SELECT COUNT(p) FROM usuario_ingressa_forum f JOIN postagem p ON p.id_forum = f.id_forum WHERE f.id_usuario = ?;";

        try {
            Connection connection = PostDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id_user);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getLong("count");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static Long countAllPosts() {

        Long count = null;
        String query = "SELECT COUNT(*) FROM postagem;";

        try {
            Connection connection = PostDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getLong("count");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static Long countMyPosts(Long id_user) {

        Long count = null;
        String query = "SELECT COUNT(p) FROM postagem p WHERE p.id_autor = ?;";

        try {
            Connection connection = PostDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id_user);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getLong("count");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

}
