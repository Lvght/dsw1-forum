package br.ufscar.dsw1.controller;

import br.ufscar.dsw1.dao.PostDAO;
import br.ufscar.dsw1.domain.Post;

import br.ufscar.dsw1.dao.ForumDAO;
import br.ufscar.dsw1.domain.Forum;

import br.ufscar.dsw1.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "PostServlet", value = "/post/*")
public class PostController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/criar":
                    insertForm(request, response);
                    break;
                case "/dashboard":
                    dashboard(request, response);
                    break;
                case "/detail":
                    try {
                        long postId = Long.parseLong(request.getParameter("postId"));
                        postDetail(request, response, postId);
                    } catch (NumberFormatException e) {
                        // Requisição inválida. Envie para a tela inicial.
                        request.getRequestDispatcher("/errorScreen.jsp").forward(request, response);
                    }
                default:
                    insertForm(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long id_autor = Long.parseLong(request.getParameter("id_autor"));
        final Long id_forum = Long.parseLong(request.getParameter("id_forum"));
        final Long id_topico = !Objects.equals(request.getParameter("id_topico"), "")
                ? Long.parseLong(request.getParameter("id_topico"))
                : null;
        final String titulo = request.getParameter("titulo");
        final String conteudo = request.getParameter("conteudo");

        Post post = new Post(id_autor, id_forum, id_topico, titulo, conteudo);

        response.setContentType("text/html");

        if (PostDAO.insert(post)) {
            response.getWriter().println("Deu certo :)");
        } else {
            response.getWriter().println("Deu errado :(");
        }
    }

    private void insertForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Forum> listaForuns = ForumDAO.getUserForuns(((User) request.getSession().getAttribute("user")).getId());
        request.setAttribute("listaForuns", listaForuns);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/criarPost.jsp");
        dispatcher.forward(request, response);
    }

    private void dashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Post> homePosts = PostDAO.getAll();
        request.setAttribute("homePosts", homePosts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(request, response);
    }


    /**
     * Encaminha o usuário para ver os detalhes de um post. Invocado após clicar em um post card.
     */
    private void postDetail(HttpServletRequest request, HttpServletResponse response, long postId)
            throws ServletException, IOException {

        Post p = PostDAO.getPost(postId);

        if (p != null) {
            request.setAttribute("errored", false);
            request.setAttribute("post", p);
            request.getRequestDispatcher("/postDetail.jsp").forward(request, response);
        } else request.setAttribute("errored", true);

        request.getRequestDispatcher("/errorScreen.jsp").forward(request, response);
    }
}
