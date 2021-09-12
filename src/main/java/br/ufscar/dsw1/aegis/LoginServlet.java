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

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String plaintextPassword = request.getParameter("password");

        if (UserDAO.verifyPassword(username, plaintextPassword)) {
            User user = UserDAO.getByUsername(username);
            List<Forum> userForuns = ForumDAO.getUserForuns(user.getId());
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userForuns", userForuns);
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        } else {
            response.getWriter().println("Você errou a senha");
        }
    }
}
