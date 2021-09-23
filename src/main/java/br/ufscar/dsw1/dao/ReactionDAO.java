package br.ufscar.dsw1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReactionDAO extends GenericDAO {
    public static boolean reactToPost(long authorId, long postId, int type) {
        final String query = "INSERT INTO debatr.public.usuario_reage_postagem (id_usuario, id_postagem, tipo_reacao) VALUES (?, ?, ?)";

        try {
            Connection connection = ReactionDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, authorId);
            statement.setLong(2, postId);
            statement.setInt(3, type);

            final int affectedRows = statement.executeUpdate();
            if (affectedRows != 0) {
                statement.close();
                connection.close();
                return true;
            };
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
