<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Criar novo fórum</title>

        <style>
        body{
                display: flex;
            }

            .forumForm{
                padding: 30px;
            }

            .forumForm h1{
                color: #00171F;
                font-size: 21px;
                margin-bottom: 0;
            }

            .forumForm hr{
                border-top: 1px solid black;
                margin: 0 0 1rem 0;
            }

            form{
                display: flex;
                flex-direction: column;
            }

            .forumForm .forumSelect{
                width: 50%;
            }

            .forumForm .selectTopic{
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

            .forumForm{
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
        <div class="forumForm">
            <h1>Criar um forum</h1>
            <hr />
            <form action="${pageContext.request.contextPath}/forum" method="POST">
                <input name="id_dono" value="${sessionScope.user.id}" hidden required/>

                <label for="escopoAcesso">Quem pode visualizar</label>
                <select id="escopoAcesso" name="escopo_acesso" required>

                    <option value=1 ${requestScope.access_scope == 1 ? 'selected' : ''}>
                        Qualquer um
                    </option>

                    <option value=2 ${requestScope.access_scope == 2 ? 'selected' : ''}>
                        Apenas usuários verificados
                    </option>

                </select>

                <label for="escopoPostagem">Quem pode postar</label>
                <select id="escopoPostagem" name="escopo_postagem" required>
                    <option value=1 ${requestScope.post_scope == 1 ? 'selected' : ''}>
                        Qualquer um
                    </option>

                    <option value=2 ${requestScope.post_scope == 2 ? 'selected' : ''}>
                        Apenas usuários verificados
                    </option>

                </select>

                <label for="titulo">Titulo</label>
                <input id="titulo" name="titulo" required value="${requestScope.title}"
                 pattern="([^ ][^])*[^ ]" title="Deve conter ao menos uma letra, não começar e nem terminar com espaço"/>
                <span class="errorMessage">${requestScope.message.title}</span>

                <label for="descricao">Descricao</label>
                <textarea id="descricao" name="descricao" required>${requestScope.description}</textarea>
                <span class="errorMessage">${requestScope.message.description}</span>

                <label for="icone">icone</label>
                <input id="icone" name="icone" required value="${requestScope.icon}"/>
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