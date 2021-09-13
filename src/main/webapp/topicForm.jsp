<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>

        <style>
            form{
                display: flex;
                flex-direction: column;
            }

            input, select{
                margin-bottom: 10px;
                height: 25px;
            }

            input[type="submit"]{
                width: fit-content;
            }

            .topicForm{
                width: 100%;
            }

            body{
                display: flex;
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
                <input id="nome" name="nome" required/>

                <input type="submit" value="Criar">
            </form>
        </div>
    </body>
</html>