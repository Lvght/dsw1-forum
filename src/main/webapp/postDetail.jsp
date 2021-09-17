<%--
  Created by IntelliJ IDEA.
  User: lvght
  Date: 14/09/2021
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Post de @${requestScope.post.autor.username} em ${requestScope.post.forum.titulo}</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>

        <script>
            function loadComments() {
                const url = '${pageContext.request.contextPath}/comments/?postId=${requestScope.post.id}'

                // Faz um GET, e passa os dados para a função anônima.
                jQuery.get(url, function (data) {
                    document.getElementById("comment-timeline").innerHTML = data;
                });

            }

            function submitComment(postId) {
                const url = '${pageContext.request.contextPath}/comments/'
                const textArea = $("#input-area")

                // Salva o conteúdo, e limpa a caixa de texto.
                const content = textArea.val()
                textArea.val("")

                if (content == null || content.length === 0) {
                    alert("Você não pode fazer um post vazio.")
                    return
                }

                const payload = {
                    "content": content,
                    "postId": ${requestScope.post.id}
                };

                jQuery.post(url, payload, function (data) {
                    $("#comment-timeline").prepend(data)
                })
            }

            loadComments();
        </script>
    </head>
    <body>
        <%@include file="layout.jsp" %>

        <c:set var="post" value="${requestScope.post}"/>
        <%@ include file="components/_post.jsp" %>

        <div class="form-group mb-5 mt-3" id="postCreation">
            <label for="input-area">Comente algo sobre isso</label>
            <textarea class="form-control" id="input-area" rows="3"
                      placeholder="Escreva seu comentário aqui (suporta markdown)"></textarea>

            <input type="button"
                   value="Enviar comentário"
                   class="mt-2"
                   onclick="submitComment(${requestScope.post.id})"/>
        </div>

        <div id="comment-timeline"></div>
    </body>
</html>


