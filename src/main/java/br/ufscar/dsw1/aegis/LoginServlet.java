package br.ufscar.dsw1.aegis;

import br.ufscar.dsw1.dao.UserDAO;
import br.ufscar.dsw1.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String plaintextPassword = request.getParameter("password");

        if (UserDAO.verifyPassword(username, plaintextPassword)) {
            response.getWriter().println("Você acertou a senha");
        } else {
            response.getWriter().println("Você errou a senha");
        }
    }
}
