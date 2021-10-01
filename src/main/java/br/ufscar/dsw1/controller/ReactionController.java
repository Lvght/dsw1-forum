package br.ufscar.dsw1.controller;

import br.ufscar.dsw1.dao.ReactionDAO;
import br.ufscar.dsw1.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReactionController", value = "/react/")
public class ReactionController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        try {
            final long postId = Long.parseLong(request.getParameter("postId"));
            final long userId = ((User) request.getSession().getAttribute("user")).getId();
            final int type = Integer.parseInt(request.getParameter("type"));

            if (ReactionDAO.reactToPost(userId, postId, type)) {
                response.getWriter().write("deu certo");
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
            return;
        }
    }
}
