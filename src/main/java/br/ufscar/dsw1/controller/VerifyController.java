package br.ufscar.dsw1.controller;

import br.ufscar.dsw1.aegis.utils.Ava2Connector;
import br.ufscar.dsw1.dao.UserDAO;
import br.ufscar.dsw1.domain.Comment;
import br.ufscar.dsw1.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "VerifyController", value = "/verify/")
public class VerifyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Banana");

        try {
            final int academicRecord = Integer.parseInt(request.getParameter("ra"));
            final String plaintextPassword = request.getParameter("password");

            if (plaintextPassword == null || plaintextPassword.isEmpty()) {
                request.getRequestDispatcher("/errorScreen.jsp").forward(request, response);
                return;
            }

            System.out.println("Foco aqui 1");

            if (Ava2Connector.verifyUserCredentials(Integer.toString(academicRecord), plaintextPassword)) {
                System.out.println("Entrou no if do verify");
                System.out.println("Tem algo na sessão? " + request.getSession().getAttribute("user"));

                final long userId = ((User) request.getSession().getAttribute("user")).getId();

                final boolean success = UserDAO.verifyAlmaMater(userId, academicRecord);

                if (!success) {
                    request.getRequestDispatcher("/errorScreen.jsp").forward(request, response);
                    return;
                }

                // Atualiza o usuário na sessão.
                User user = (User) request.getSession().getAttribute("user");
                user.setAcademicRecord(academicRecord);
                request.getSession().setAttribute("user", user);

                response.sendRedirect(request.getContextPath() + "/perfil/");
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/errorScreen.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/almaMaterVerification.jsp").forward(request, response);
    }
}
