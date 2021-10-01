package br.ufscar.dsw1.aegis;

import br.ufscar.dsw1.dao.UserDAO;
import br.ufscar.dsw1.domain.User;

import br.ufscar.dsw1.dao.ForumDAO;
import br.ufscar.dsw1.domain.Forum;

import javax.servlet.*;
import javax.servlet.http.*;

import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import javax.servlet.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Permite ao usuário criar uma conta no sistema.
 */
@MultipartConfig
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
        } else {
            String fotoPerfil = null;

            if (request.getPart("fotoPerfil") != null) {
                final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
                final String bucketName = "debatr-media";

                Part filePart = request.getPart("fotoPerfil");
                String filename = "user-" + username;
                try {
                    InputStream inputStream = filePart.getInputStream();
                    File uploadedFile = new File("/" + File.separator + filename);
                    OutputStream outputStream = new FileOutputStream(uploadedFile);

                    int read;
                    byte[] bytes = new byte[1024];

                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }

                    outputStream.close();

                    s3.putObject(bucketName, filename, uploadedFile);
                    fotoPerfil = "https://" + bucketName + ".s3.sa-east-1.amazonaws.com/" + filename;
                } catch (IOException | SdkClientException e) {
                    e.printStackTrace();
                }
            }

            User user = new User(name, email, username);
            user.setProfileImageUrl(fotoPerfil);

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
}
