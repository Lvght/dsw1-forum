package br.ufscar.dsw1.controller;

import br.ufscar.dsw1.dao.ForumDAO;
import br.ufscar.dsw1.domain.Forum;

import br.ufscar.dsw1.dao.PostDAO;
import br.ufscar.dsw1.domain.Post;

import br.ufscar.dsw1.dao.TopicDAO;
import br.ufscar.dsw1.domain.Topic;

import br.ufscar.dsw1.domain.User;

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
import java.util.HashMap;
import java.util.List;

@MultipartConfig
@WebServlet(name = "ForumServlet", value = "/forum/*")
public class ForumController extends HttpServlet {

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
                    listar(request, response);
                    break;
                case "/especifico":
                    getForum(request, response);
                    break;
                case "/ingressarForum":
                    ingressarForum(request, response);
                    break;
                case "/sairForum":
                    sairForum(request, response);
                    break;
                case "/forumForm":
                    forumForm(request, response);
                    break;
                case "/topicoForm":
                    topicoForm(request, response);
                    break;
                case "/getTopicosForum":
                    getTopicosForum(request, response);
                    break;
                default:
                    listar(request, response);
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
                    criar(request, response);
                    break;
                case "/topico":
                    criarTopico(request, response);
                    break;
                case "/forumForm":
                    forumForm(request, response);
                    break;
                case "/topicoForm":
                    topicoForm(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void criar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            final long id_dono = Long.parseLong(request.getParameter("id_dono"));
            final int escopo_postagem = Integer.parseInt(request.getParameter("escopo_postagem"));
            final int escopo_acesso = Integer.parseInt(request.getParameter("escopo_acesso"));
            final String titulo = request.getParameter("titulo");
            final String descricao = request.getParameter("descricao");

            request.setAttribute("post_scope", escopo_postagem);
            request.setAttribute("access_scope", escopo_acesso);
            request.setAttribute("title", titulo);
            request.setAttribute("description", descricao);

            final HashMap<String, String> errorMessage = new HashMap<>();

            boolean error = false;

            if (titulo.length() > 50) {
                errorMessage.put("title", "O título deve conter no máximo 50 caracteres");
                error = true;
                request.setAttribute("title", "");
            }

            if (descricao.length() > 15) {
                errorMessage.put("description", "A descrição deve conter no máximo 255 caracteres");
                error = true;
                request.setAttribute("description", "");
            }

            if (ForumDAO.verifyTitle(titulo)) {
                errorMessage.put("title", "Já existe um fórum com este título");
                error = true;
                request.setAttribute("title", "");
            }

            request.setAttribute("message", errorMessage);

            if (error) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/forum/forumForm");
                dispatcher.forward(request, response);
            } else {

                final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
                final String bucketName = "debatr-media";

                Part filePart = request.getPart("icone");
                String filename = "forum-" + titulo;
                String icone = null;
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
                    icone = "https://" + bucketName + ".s3.sa-east-1.amazonaws.com/" + filename;
                } catch (IOException | SdkClientException e) {
                    e.printStackTrace();
                }

                Forum forum = new Forum(id_dono, escopo_postagem, escopo_acesso, titulo, descricao, icone);

                response.setContentType("text/html");

                if (ForumDAO.insert(forum)) {
                    response.sendRedirect(request.getContextPath() + "/forum/especifico?id=" + forum.getId());
                }
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sessionUser = (User) request.getSession().getAttribute("user");
        List<Forum> listaForuns = ForumDAO.getAll(sessionUser != null ? sessionUser.getId() : Long.valueOf(0));
        request.setAttribute("listaForuns", listaForuns);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaForuns.jsp");
        dispatcher.forward(request, response);
    }

    private void getForum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long id = Long.parseLong(request.getParameter("id"));
        final Forum forum = ForumDAO.getForum(id);
        final Long itemCount = PostDAO.countForumPosts(id,
                Long.valueOf(request.getParameter("topico") != null ? request.getParameter("topico") : "0"));
        request.setAttribute("topico",
                Long.valueOf(request.getParameter("topico") != null ? request.getParameter("topico") : "0"));
        request.setAttribute("filtro",
                Long.valueOf(request.getParameter("filtro") != null ? request.getParameter("filtro") : "0"));

        Long pagina = Long.parseLong(request.getParameter("page") != null ? request.getParameter("page") : "1");

        User sessionUser = (User) request.getSession().getAttribute("user");
        final long sessionUserId = sessionUser == null ? 0 : sessionUser.getId();

        List<Post> posts = PostDAO.getForumPosts(id, (Long) request.getAttribute("topico"),
                (Long) request.getAttribute("filtro"), pagina, sessionUserId);
        List<Topic> topicos = TopicDAO.getForumTopicos(id);

        request.setAttribute("forum", forum);
        final User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            if (ForumDAO.usuarioIngressa(user.getId(), id))
                request.setAttribute("status", "sair");
            else
                request.setAttribute("status", "ingressar");
        }

        request.setAttribute("page", pagina);
        request.setAttribute("posts", posts);
        request.setAttribute("topicos", topicos);
        request.setAttribute("itemCount", itemCount);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/forum.jsp");
        dispatcher.forward(request, response);
    }

    private void ingressarForum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long id = Long.parseLong(request.getParameter("id_forum"));
        final Forum forum = ForumDAO.getForum(id);
        final User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("forum", forum);
        if (ForumDAO.ingressar(user.getId(), Long.parseLong(request.getParameter("id_forum")))) {
            request.setAttribute("status", "sair");
            List<Forum> userForuns = (List<Forum>) request.getSession().getAttribute("userForuns");
            userForuns.add(forum);
            request.setAttribute("userForuns", userForuns);
        } else {
            request.setAttribute("status", "ingressar");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/components/_button.jsp");
        dispatcher.forward(request, response);
    }

    private void sairForum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long id = Long.parseLong(request.getParameter("id_forum"));
        final Forum forum = ForumDAO.getForum(id);
        final User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("forum", forum);
        if (ForumDAO.sair(user.getId(), Long.parseLong(request.getParameter("id_forum")))) {
            request.setAttribute("status", "ingressar");
            List<Forum> userForuns = (List<Forum>) request.getSession().getAttribute("userForuns");
            userForuns.removeIf(obj -> obj.getId() == id);
            request.setAttribute("userForuns", userForuns);
        } else {
            request.setAttribute("status", "sair");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/components/_button.jsp");
        dispatcher.forward(request, response);
    }

    private void forumForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/forumForm.jsp");
        dispatcher.forward(request, response);
    }

    private void topicoForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long forum_id = Long.parseLong(request.getParameter("forum_id"));
        request.setAttribute("forum_id", forum_id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/topicForm.jsp");
        dispatcher.forward(request, response);
    }

    private void criarTopico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        final Long forum_id = Long.parseLong(request.getParameter("id_forum"));
        final String nome = request.getParameter("nome");
        Topic topic = new Topic(forum_id, nome);

        final HashMap<String, String> errorMessage = new HashMap<>();

        boolean error = false;

        if (nome.length() > 50) {
            errorMessage.put("name", "O topico deve conter no máximo 50 caracteres");
            error = true;
        }

        if (TopicDAO.verifyTopic(nome) || nome == "Geral") {
            errorMessage.put("name", "Já existe um tópico com este nome");
            error = true;
        }

        request.setAttribute("message", errorMessage);

        if (error) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/forum/topicoForm?forum_id=" + forum_id);
            dispatcher.forward(request, response);
        } else {
            if (TopicDAO.insert(topic))
                response.sendRedirect(
                        request.getContextPath() + "/forum/especifico?id=" + forum_id + "&topico=" + topic.getId());
            else
                response.getWriter().println("Deu errado :(");
        }
    }

    private void getTopicosForum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long id = Long.parseLong(request.getParameter("id_forum"));
        List<Topic> topicos = TopicDAO.getForumTopicos(id);
        request.setAttribute("topicos", topicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/components/_topicOptions.jsp");
        dispatcher.forward(request, response);
    }
}
