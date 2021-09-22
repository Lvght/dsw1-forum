package br.ufscar.dsw1.aegis;

import br.ufscar.dsw1.dao.UserDAO;
import br.ufscar.dsw1.domain.User;

import br.ufscar.dsw1.dao.ForumDAO;
import br.ufscar.dsw1.domain.Forum;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login/*")
public class LoginServlet extends HttpServlet {
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
                    loginForm(request, response);
                    break;
                case "/logout":
                    logout(request, response);
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
                    login(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void loginForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            response.sendRedirect(request.getContextPath() + "/post/dashboard");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String plaintextPassword = request.getParameter("password");

        request.setAttribute("username", username);

        if (UserDAO.verifyPassword(username, plaintextPassword)) {
            User user = UserDAO.getByUsername(username);
            List<Forum> userForuns = ForumDAO.getUserForuns(user.getId());
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userForuns", userForuns);
            response.sendRedirect(request.getContextPath() + "/post/dashboard");
        } else {
            request.setAttribute("message", "Usuário ou senha inválidos. Tente novamente.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login");
    }

}
