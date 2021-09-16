package br.ufscar.dsw1.dao;

import br.ufscar.dsw1.domain.Comment;
import br.ufscar.dsw1.domain.Post;
import br.ufscar.dsw1.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                        UserDAO.getById(resultSet.getLong("id_autor")),
                        originalPost);
            }

            statement.close();
            connection.close();


        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * @param postId aas
     * @return Uma lista de [Comment], que PODE SER VAZIA. Nulo, se mal-sucedido.
     */
    public static ArrayList<Comment> getAllCommentsFromPost(long postId) {
        final String query = "SELECT * FROM comentario WHERE id_postagem = ?;";
        ArrayList<Comment> result = new ArrayList<>();

        try {
            Connection connection = CommentDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, postId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
                result.add(new Comment(resultSet.getLong("id_comentario"),
                        resultSet.getString("conteudo"),
                        UserDAO.getById(resultSet.getLong("id_autor")),
                        null));

            return result;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static Comment insert(Long postId, long userId, String content, boolean includeOriginalPost) {
        final String query = "INSERT INTO comentario (id_postagem, id_autor, conteudo) VALUES (?, ?, ?)";

        try {
            Connection connection = CommentDAO.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, new String[]{"id_comentario"});

            statement.setLong(1, postId);
            statement.setLong(2, userId);
            statement.setString(3, content);

            final int affectedRows = statement.executeUpdate();

            // Sem linhas afetadas. Algo deu errado.
            if (affectedRows == 0) return null;

            Comment result = null;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {

                if (generatedKeys.next()) {
                    Post post = null;
                    if (includeOriginalPost) post = PostDAO.getPost(postId);

                    // Não precisamos do post original nos casos de uso atuais.
                    result = new Comment(generatedKeys.getLong("id_comentario"), content,
                            UserDAO.getById(userId), post);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            statement.close();
            connection.close();

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
