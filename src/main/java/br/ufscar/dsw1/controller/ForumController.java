package br.ufscar.dsw1.controller;

import br.ufscar.dsw1.dao.ForumDAO;
import br.ufscar.dsw1.domain.Forum;

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
        request.setAttribute("forum", forum);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/forum.jsp");
        dispatcher.forward(request, response);
    }
}