<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${requestScope.forum.titulo} - Novo tópico</title>

        <style>
            body{
                display: flex;
            }

            .topicForm{
                padding: 30px;
            }

            .topicForm h1{
                color: #00171F;
                font-size: 21px;
                margin-bottom: 0;
            }

            .topicForm hr{
                border-top: 1px solid black;
                margin: 0 0 1rem 0;
            }

            form{
                display: flex;
                flex-direction: column;
            }

            .topicForm .forumSelect{
                width: 50%;
            }

            .topicForm .selectTopic{
                width: 25%;
            }

            input, select, textarea{
                margin-bottom: 10px!important;
                padding: 10px;
                background-color: #142931;
                color: white;
                border-radius: 15px;
            }

            input, select{
                height: 50px;
            }

            input[type="submit"], .discard{
                width: fit-content;
                height: 40px;
                padding: 0 15px;
                border-radius: 10px;
                border: 0;
            }

            textarea{
                min-height: 200px
            }

            .topicForm{
                width: 100%;
            }
            .criar .post{
                display: flex;
            }

            .actionButtons{
                display: flex;
                line-height: 35px;
            }

            .discard{
                background-color: white;
                border: 2px solid #142931!important;
                color: #142931;
                margin-right: 10px;
                cursor: pointer;
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
            <hr/>
            <form action="${pageContext.request.contextPath}/forum/topico" method="POST">
                <input name="id_forum" value="${requestScope.forum_id}" hidden required/>

                <label for="nome">Nome</label>
                <input id="nome" name="nome" required
                pattern="[^ ]+([^]*[^ ])*" title="Deve conter ao menos uma letra, não começar e nem terminar com espaço"/>
                <span class="errorMessage">${requestScope.message.name}</span>

                <br />
                <hr />
                <div class="actionButtons">
                    <div class="discard" onclick="window.history.back();">
                        <span>Descartar</span>
                    </div>
                        <input type="submit" value="Criar">
                    </div> 
                </div>
            </form>
        </div>
    </body>
</html>