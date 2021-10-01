package br.ufscar.dsw1.controller;

import br.ufscar.dsw1.aegis.utils.EmailController;
import br.ufscar.dsw1.dao.UserDAO;
import br.ufscar.dsw1.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RequestPasswordResetController", value = "/request-password-reset")
public class RequestPasswordResetController extends HttpServlet {
    private static String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + serverName + serverPort + contextPath;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("request-password-reset.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String email = request.getParameter("email");

        if (email == null || email.isEmpty()) {
            // TODO ENVIAR PARA O FORMULÁRIO COM UM ERRO ÚTIL
            return;
        }

        User user = UserDAO.getByEmail(email);

        if (user != null) {
            EmailController.sendPasswordResetToken(user, getBaseUrl(request));
        }

        request.getRequestDispatcher("check-your-email.jsp").forward(request, response);
    }
}
