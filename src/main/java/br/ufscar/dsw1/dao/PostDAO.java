package br.ufscar.dsw1.dao;

import br.ufscar.dsw1.domain.Post;

import br.ufscar.dsw1.domain.User;

import br.ufscar.dsw1.domain.Forum;

import br.ufscar.dsw1.domain.Topic;

import br.ufscar.dsw1.dao.UserDAO;

import br.ufscar.dsw1.dao.ForumDAO;

import br.ufscar.dsw1.dao.TopicDAO;

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

    public static List<Post> getAll() {

        List<Post> listPosts = new ArrayList<>();

        String query = "SELECT * from Postagem ORDER BY data_criacao DESC";

        try {
            Connection connection = ForumDAO.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_postagem");
                Long id_autor = resultSet.getLong("id_autor");
                Long id_forum = resultSet.getLong("id_forum");
                Long id_topico = resultSet.getLong("id_topico");
                String titulo = resultSet.getString("titulo");
                String conteudo = resultSet.getString("conteudo");

                Post post = new Post(id_autor, id_forum, id_topico, titulo, conteudo);
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

    public static List<Post> getForumPosts(Long id_forum) {

        List<Post> listPosts = new ArrayList<>();

        String query = "SELECT * from Postagem WHERE id_forum = ? ORDER BY data_criacao DESC";

        try {
            Connection connection = ForumDAO.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id_forum);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_postagem");
                Long id_autor = resultSet.getLong("id_autor");
                Long id_topico = resultSet.getLong("id_topico");
                String titulo = resultSet.getString("titulo");
                String conteudo = resultSet.getString("conteudo");

                Post post = new Post(id_autor, id_forum, id_topico, titulo, conteudo);
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

    public static Post getPost(Long id) {

        Post post = null;

        String query = "SELECT * from postagem WHERE id_postagem = ?";

        try {
            Connection connection = PostDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id_autor = resultSet.getLong("id_autor");
                Long id_forum = resultSet.getLong("id_forum");
                Long id_topico = resultSet.getLong("id_topico");
                String titulo = resultSet.getString("titulo");
                String conteudo = resultSet.getString("conteudo");

                post = new Post(id_autor, id_forum, id_topico, titulo, conteudo);
                User user = UserDAO.getById(id_autor);
                Forum forum = ForumDAO.getForum(id_forum);
                Topic topic = null;
                if (id_topico == 0)
                    topic = TopicDAO.getTopic(id_topico);
                post.setAutor(user);
                post.setForum(forum);
                post.setTopico(topic);
                post.setId(id);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }
}
