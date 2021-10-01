package br.ufscar.dsw1.controller;

import br.ufscar.dsw1.aegis.utils.JWTManager;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ResetPasswordController", value = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String token = request.getParameter("token");
        DecodedJWT jwt = null;

        if ((jwt = JWTManager.getDecodedJWT(token)) != null)
            request.getRequestDispatcher("password-reset.jsp").forward(request, response);
        else {
            System.out.println("Redirecionando para a página: " + request.getContextPath());
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String password = request.getParameter("password");
        final String token = request.getParameter("token");

        JWTManager.changePasswordForTokenOwner(token, password);

        response.sendRedirect(request.getContextPath());
    }
}
