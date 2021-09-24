<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${requestScope.forum.titulo} - Novo tópico</title>

        <style>
            form {
                display: flex;
                flex-direction: column;
            }

            input, select {
                margin-bottom: 10px;
                height: 25px;
            }

            input[type="submit"] {
                width: fit-content;
            }

            .topicForm {
                width: 100%;
            }

            body {
                display: flex;
            }

            .errorMessage{
                font-size: 12px;
                color: red;
                margin-left: 15px;
                min-height: 20px;
                min-width: 100%;
                display: grid;
            }
        </style>
    </head>
    <body>
        <%@include file="layout.jsp" %>
        <div class="topicForm">
            <h1>Criar um topico</h1>
            <form action="${pageContext.request.contextPath}/forum/topico" method="POST">
                <input name="id_forum" value="${requestScope.forum_id}" hidden required/>

                <label for="nome">Nome</label>
                <input id="nome" name="nome" required
                pattern="[^ ]+([^]*[^ ])*" title="Deve conter ao menos uma letra, não começar e nem terminar com espaço"/>
                <span class="errorMessage">${requestScope.message.name}</span>

                <input type="submit" value="Criar">
            </form>
        </div>
    </body>
</html>