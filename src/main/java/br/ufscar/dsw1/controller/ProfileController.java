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
    /**
     * Permite acessar o perfil de um usuário do Debatr.
     * Acessar a rota sem nenhum perfil poderá emitir 2 resultados:
     * - Se houver um usuário na sessão, este será levado a seu perfil; ou
     * - Na ausência deste, será encaminhado para a página inicial.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String profileUsername = request.getPathInfo();
        profileUsername = profileUsername == null ? "" : profileUsername.replace("/", "");

        User profileOwner = null;
        User sessionUser = (User) request.getSession().getAttribute("user");

        // Se o username é vazio, redirecione para o perfil do usuário da sessão ou para a página inicial.
        if (profileUsername.isEmpty()) {
            // Há alguém logado. Atualize as informações e envie para o perfil.
            if (sessionUser != null) {
                User updatedUser = UserDAO.getById(sessionUser.getId());
                if (updatedUser != null) request.getSession().setAttribute("user", updatedUser);
                request.setAttribute("profileOwner", updatedUser);
                request.getRequestDispatcher("/profile.jsp").forward(request, response);
                return;
            }

            // Não há ningyém logado. Envie para a página inicial.
            response.sendRedirect(request.getContextPath());
            return;
        }

        // Tenta buscar o username;
        profileOwner = UserDAO.getByUsername(profileUsername);

        // A busca pelo dono do perfil falhou.
        if (profileOwner == null) {
            // TODO Precisamos de uma página de 404.
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Se o dono do perfil for o mesmo da sessão, atualize a sessão com os dados mais recentes.
        if (sessionUser != null && sessionUser.getId() == profileOwner.getId())
            request.getSession().setAttribute("user", profileOwner);

        request.setAttribute("profileOwner", profileOwner);
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
