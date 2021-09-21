package br.ufscar.dsw1.aegis;

import br.ufscar.dsw1.dao.UserDAO;
import br.ufscar.dsw1.domain.User;

import br.ufscar.dsw1.dao.ForumDAO;
import br.ufscar.dsw1.domain.Forum;

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
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            response.sendRedirect(request.getContextPath() + "/post/dashboard");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.html");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        final String name = request.getParameter("name");
        final String email = request.getParameter("email");
        final String plaintextPassword = request.getParameter("password");
        final String username = request.getParameter("username");

        User user = new User(name, email, username);

        response.setContentType("text/html");

        if (UserDAO.insert(user, plaintextPassword)) {
            request.getSession().setAttribute("user", user);
            List<Forum> userForuns = ForumDAO.getUserForuns(user.getId());
            request.getSession().setAttribute("userForuns", userForuns);
            response.sendRedirect(request.getContextPath() + "/post/dashboard");
        } else {
            response.getWriter().println("Deu errado :(");
        }
    }
}
