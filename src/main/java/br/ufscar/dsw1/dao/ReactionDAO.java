package br.ufscar.dsw1.dao;

import br.ufscar.dsw1.domain.Reaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReactionDAO extends GenericDAO {
    public static boolean reactToPost(long authorId, long postId, int type) {
        final String query = "INSERT INTO usuario_reage_postagem (id_usuario, id_postagem, tipo_reacao) VALUES (?, ?, ?)";

        try {
            Connection connection = ReactionDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            final boolean isAnExclusion = type == getPreviousReactionType(postId, authorId);

            deletePreviousReaction(postId, authorId);

            if (!isAnExclusion) {
                statement.setLong(1, authorId);
                statement.setLong(2, postId);
                statement.setInt(3, type);

                final int affectedRows = statement.executeUpdate();
                if (affectedRows != 0) {
                    statement.close();
                    connection.close();
                    return true;
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    private static int deletePreviousReaction(long postId, long authorId) {
        final String query = "DELETE FROM usuario_reage_postagem WHERE id_usuario = ? AND id_postagem = ?;";


        try {
            Connection connection = ReactionDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, authorId);
            statement.setLong(2, postId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int getPreviousReactionType(long postId, long authorId) {
        final String query = "SELECT tipo_reacao FROM usuario_reage_postagem " +
                "WHERE id_postagem = ? AND id_usuario = ? LIMIT 1;";

        try {
            Connection connection = ReactionDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, postId);
            statement.setLong(2, authorId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return resultSet.getInt("tipo_reacao");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
