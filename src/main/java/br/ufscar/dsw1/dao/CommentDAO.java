package br.ufscar.dsw1.dao;

import br.ufscar.dsw1.domain.Comment;
import br.ufscar.dsw1.domain.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDAO extends GenericDAO {
    /**
     * @param id                  Identificador do comentário.
     * @param includeOriginalPost Indica se o post  orifinal deve ser incluído ou não. Por exemplo, na página de
     *                            detalhe de post esta informação é completamente desnecessária.
     * @return Objeto [Comment] se bem-sucedido, [null] caso contrário.
     */
    public static Comment getById(long id, boolean includeOriginalPost) {
        final String query = "select * from comentario where id_comentario = ? limit 1";

        try {
            Connection connection = CommentDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id);

            // Executa a query
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Post originalPost = null;

                if (includeOriginalPost) {
                    final Long postId = resultSet.getLong("id_postagem");
                    originalPost = PostDAO.getPost(postId);
                }

                return new Comment(
                        resultSet.getLong("id_comentario"),
                        resultSet.getString("conteudo"),
                        originalPost);
            }


        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
