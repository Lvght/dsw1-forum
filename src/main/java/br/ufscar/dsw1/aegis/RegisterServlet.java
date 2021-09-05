package br.ufscar.dsw1.aegis;

import br.ufscar.dsw1.dao.UserDAO;
import br.ufscar.dsw1.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Permite ao usuário criar uma conta no sistema.
 */
@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String name = request.getParameter("name");
        final String email = request.getParameter("email");
        final String plaintextPassword = request.getParameter("password");
        final String username = request.getParameter("username");

        User user = new User(name, email, username);

        response.setContentType("text/html");

        if (UserDAO.insert(user, plaintextPassword)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        } else {
            response.getWriter().println("Deu errado :(");
        }
    }
}
