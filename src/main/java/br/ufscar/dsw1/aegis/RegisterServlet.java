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
import java.util.HashMap;
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
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

        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("username", username);

        final HashMap<String, String> errorMessage = new HashMap<>();

        boolean error = false;
        if (UserDAO.verifyEmail(email)) {
            errorMessage.put("email", "Email já cadastrado");
            error = true;
            request.setAttribute("email", "");
        }

        if (UserDAO.verifyUsername(username)) {
            errorMessage.put("username", "Username já cadastrado");
            error = true;
            request.setAttribute("username", "");
        }

        if (name.length() > 50) {
            errorMessage.put("name", "O nome deve conter no máximo 50 caracteres");
            error = true;
            request.setAttribute("name", "");
        }

        if (username.length() > 15) {
            errorMessage.put("username", "O username deve conter no máximo 15 caracteres");
            error = true;
            request.setAttribute("username", "");
        }

        if (email.length() > 255) {
            errorMessage.put("email", "O email deve conter no máximo 255 caracteres");
            error = true;
            request.setAttribute("email", "");
        }

        if (plaintextPassword.length() > 20 || plaintextPassword.length() < 8) {
            errorMessage.put("password", "A senha deve conter no máximo 20 e no mínimo 8 caracteres");
            error = true;
        }

        request.setAttribute("message", errorMessage);

        if (error) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request, response);
        }

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
