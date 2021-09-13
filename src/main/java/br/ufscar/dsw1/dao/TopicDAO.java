package br.ufscar.dsw1.dao;

import br.ufscar.dsw1.domain.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class TopicDAO extends GenericDAO {

    public static boolean insert(Topic topic) {

        String query = "INSERT INTO topico(id_forum, nome) VALUES (?, ?)";

        try {
            Connection connection = ForumDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, topic.getId_forum());
            statement.setString(2, topic.getNome());

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

    public static List<Topic> getForumTopicos(Long id_forum) {

        List<Topic> listaTopicos = new ArrayList<>();
        String query = "SELECT * FROM topico WHERE id_forum = ?";

        try {
            Connection connection = TopicDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id_forum);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_topico");
                String nome = resultSet.getString("nome");
                Topic topico = new Topic(id, id_forum, nome);
                listaTopicos.add(topico);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTopicos;
    }

    public static Topic getTopic(Long id) {

        Topic topic = null;
        String query = "SELECT * FROM topico WHERE id_topico= ?";

        try {
            Connection connection = TopicDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                topic = new Topic(nome);
                topic.setId(id);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topic;
    }
}
