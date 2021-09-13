package br.ufscar.dsw1.controller;

import br.ufscar.dsw1.dao.ForumDAO;
import br.ufscar.dsw1.domain.Forum;

import br.ufscar.dsw1.dao.PostDAO;
import br.ufscar.dsw1.domain.Post;

import br.ufscar.dsw1.dao.TopicDAO;
import br.ufscar.dsw1.domain.Topic;

import br.ufscar.dsw1.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ForumServlet", value = "/forum/*")
public class ForumController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "":
                    listar(request, response);
                    break;
                case "/especifico":
                    getForum(request, response);
                    break;
                case "/ingressarForum":
                    ingressarForum(request, response);
                    break;
                case "/sairForum":
                    sairForum(request, response);
                    break;
                case "/topicoForm":
                    topicoForm(request, response);
                    break;
                default:
                    listar(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "":
                    criar(request, response);
                    break;
                case "/topico":
                    criarTopico(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void criar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final long id_dono = Long.parseLong(request.getParameter("id_dono"));
        final int escopo_postagem = Integer.parseInt(request.getParameter("escopo_postagem"));
        final int escopo_acesso = Integer.parseInt(request.getParameter("escopo_acesso"));
        final String titulo = request.getParameter("titulo");
        final String descricao = request.getParameter("descricao");
        final String icone = request.getParameter("icone");

        Forum forum = new Forum(id_dono, escopo_postagem, escopo_acesso, titulo, descricao, icone);

        response.setContentType("text/html");

        if (ForumDAO.insert(forum)) {
            response.getWriter().println("Deu certo :)");
        } else {
            response.getWriter().println("Deu errado :(");
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Forum> listaForuns = ForumDAO.getAll();
        request.setAttribute("listaForuns", listaForuns);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaForuns.jsp");
        dispatcher.forward(request, response);
    }

    private void getForum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long id = Long.parseLong(request.getParameter("id"));
        final Forum forum = ForumDAO.getForum(id);
        List<Post> posts = PostDAO.getForumPosts(id);
        List<Topic> topicos = TopicDAO.getForumTopicos(id);

        request.setAttribute("forum", forum);
        final User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            if (ForumDAO.usuarioIngressa(user.getId(), id))
                request.setAttribute("status", "sair");
            else
                request.setAttribute("status", "ingressar");
        }

        request.setAttribute("posts", posts);
        request.setAttribute("topicos", topicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/forum.jsp");
        dispatcher.forward(request, response);
    }

    private void ingressarForum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long id = Long.parseLong(request.getParameter("id_forum"));
        final Forum forum = ForumDAO.getForum(id);
        final User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("forum", forum);
        if (ForumDAO.ingressar(user.getId(), Long.parseLong(request.getParameter("id_forum")))) {
            request.setAttribute("status", "sair");
            List<Forum> userForuns = (List<Forum>) request.getSession().getAttribute("userForuns");
            userForuns.add(forum);
            request.setAttribute("userForuns", userForuns);
        } else {
            request.setAttribute("status", "ingressar");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/components/_button.jsp");
        dispatcher.forward(request, response);
    }

    private void sairForum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long id = Long.parseLong(request.getParameter("id_forum"));
        final Forum forum = ForumDAO.getForum(id);
        final User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("forum", forum);
        if (ForumDAO.sair(user.getId(), Long.parseLong(request.getParameter("id_forum")))) {
            request.setAttribute("status", "ingressar");
            List<Forum> userForuns = (List<Forum>) request.getSession().getAttribute("userForuns");
            userForuns.removeIf(obj -> obj.getId() == id);
            request.setAttribute("userForuns", userForuns);
        } else {
            request.setAttribute("status", "sair");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/components/_button.jsp");
        dispatcher.forward(request, response);
    }

    private void topicoForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long forum_id = Long.parseLong(request.getParameter("forum_id"));
        request.setAttribute("forum_id", forum_id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/topicForm.jsp");
        dispatcher.forward(request, response);
    }

    private void criarTopico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long forum_id = Long.parseLong(request.getParameter("id_forum"));
        final String nome = request.getParameter("nome");
        Topic topic = new Topic(forum_id, nome);
        if (TopicDAO.insert(topic))
            response.sendRedirect(request.getContextPath() + "/forum/especifico?id=" + forum_id);
        else
            response.getWriter().println("Deu errado :(");
    }
}
