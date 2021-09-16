package br.ufscar.dsw1.controller;

import br.ufscar.dsw1.dao.UserDAO;
import br.ufscar.dsw1.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileController", value = "/perfil/*")
public class ProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String profile = request.getPathInfo();
        User profileOwner = null;

        if (profile == null || profile.length() <= 1) {
            profileOwner = UserDAO.getById(((User) request.getSession().getAttribute("user")).getId());
        } else {
            // Remove a / no início (e potencialmente no final).
            profile = profile.replace("/", "");

            profileOwner = UserDAO.getByUsername(profile);
            if (profileOwner == null) {
                request.getRequestDispatcher("/errorScreen.jsp").forward(request, response);
                return;
            }
        }

        request.setAttribute("profileOwner", profileOwner);
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
