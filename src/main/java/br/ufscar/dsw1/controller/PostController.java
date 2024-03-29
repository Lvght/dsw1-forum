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
import java.util.HashMap;
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
                case "/detail":
                    try {
                        long postId = Long.parseLong(request.getParameter("postId"));
                        postDetail(request, response, postId);
                    } catch (NumberFormatException e) {
                        // Requisição inválida. Envie para a tela inicial.
                        request.getRequestDispatcher("/errorScreen.jsp").forward(request, response);
                    } finally {
                        break;
                    }
                case "/meusPosts":
                    myPosts(request, response);
                    break;
                default:
                    dashboard(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        final Long id_autor = Long.parseLong(request.getParameter("id_autor"));
        final Long id_forum = Long.parseLong(request.getParameter("id_forum"));
        final Long id_topico = !Objects.equals(request.getParameter("id_topico"), "")
                ? Long.parseLong(request.getParameter("id_topico"))
                : null;
        final String titulo = request.getParameter("titulo");
        final String conteudo = request.getParameter("conteudo");

        final HashMap<String, String> errorMessage = new HashMap<>();

        boolean error = false;
        if (titulo.length() > 255) {
            errorMessage.put("titulo", "O título não deve ter mais do que 255 caracteres");
            error = true;
        }

        if (error) {
            request.setAttribute("message", errorMessage);
        }

        Post post = new Post(id_autor, id_forum, id_topico, titulo, conteudo, 0, 0, 0);

        response.setContentType("text/html");

        if (PostDAO.insert(post)) {
            response.sendRedirect(request.getContextPath() + "/post/detail?postId=" + post.getId());
        } else {
            response.getWriter().println("Deu errado :(");
        }
    }

    private void insertForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User sessionUser = (User) request.getSession().getAttribute("user");

        if (sessionUser == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
            dispatcher.forward(request, response);
        } else {
            List<Forum> listaForuns = ForumDAO.getUserForuns(sessionUser.getId());
            request.setAttribute("listaForuns", listaForuns);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/criarPost.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void dashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User sessionUser = (User) request.getSession().getAttribute("user");
        List<Post> homePosts = null;
        Long itemCount = Long.valueOf(0);
        Long pagina = Long.parseLong(request.getParameter("page") != null ? request.getParameter("page") : "1");

        if (sessionUser != null) {
            homePosts = PostDAO.getTimeline(sessionUser.getId(), pagina);
            itemCount = PostDAO.countPostUserForuns(sessionUser.getId());
        } else {
            homePosts = PostDAO.getAll(pagina, 0);
            itemCount = PostDAO.countAllPosts();
        }

        request.setAttribute("page", pagina);
        request.setAttribute("itemCount", itemCount);
        request.setAttribute("homePosts", homePosts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Encaminha o usuário para ver os detalhes de um post. Invocado após clicar em
     * um post card.
     */
    private void postDetail(HttpServletRequest request, HttpServletResponse response, long postId)
            throws ServletException, IOException {

        User sessionUser = (User) request.getSession().getAttribute("user");
        final long userId = sessionUser == null ? 0 : sessionUser.getId();

        Post p = PostDAO.getPost(postId, userId);

        if (p != null) {

            User user = (User) request.getSession().getAttribute("user");

            if (p.getForum().getEscopo_acesso() == 2 && (user == null || user.getAcademicRecord() == 0)) {
                response.sendRedirect(request.getContextPath() + "/post/dashboard");
            }

            request.setAttribute("errored", false);
            request.setAttribute("post", p);
            request.setAttribute("drb", true);

            request.getRequestDispatcher("/postDetail.jsp").forward(request, response);
        } else
            request.setAttribute("errored", true);

        request.getRequestDispatcher("/errorScreen.jsp").forward(request, response);
    }

    private void myPosts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User sessionUser = (User) request.getSession().getAttribute("user");
        List<Post> myPosts = null;
        Long itemCount = Long.valueOf(0);
        Long pagina = Long.parseLong(request.getParameter("page") != null ? request.getParameter("page") : "1");

        myPosts = PostDAO.getMyPosts(sessionUser.getId(), pagina);
        itemCount = PostDAO.countMyPosts(sessionUser.getId());

        request.setAttribute("page", pagina);
        request.setAttribute("itemCount", itemCount);
        request.setAttribute("myPosts", myPosts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/meusPosts.jsp");
        dispatcher.forward(request, response);
    }
}
