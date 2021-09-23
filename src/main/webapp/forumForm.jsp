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

        </style>
    </head>
    <body>
        <%@include file="layout.jsp" %>
        <div class="forumForm">
            <h1>Criar um forum</h1>
            <hr />
            <form action="./forum" method="POST">
                <input name="id_dono" value="${sessionScope.user.id}" hidden required/>

                <label for="escopoAcesso">Quem pode visualizar</label>
                <select id="escopoAcesso" name="escopo_acesso" required>

                    <option value=1>
                        Qualquer um
                    </option>

                    <option value=2>
                        Apenas usuários verificados
                    </option>

                </select>

                <label for="escopoPostagem">Quem pode postar</label>
                <select id="escopoPostagem" name="escopo_postagem" required>
                    <option value=1>
                        Qualquer um
                    </option>

                    <option value=2>
                        Apenas usuários verificados
                    </option>

                </select>

                <label for="titulo">Titulo</label>
                <input id="titulo" name="titulo" required/>

                <label for="descricao">Descricao</label>
                <input id="descricao" name="descricao" required/>

                <label for="icone">icone</label>
                <input id="icone" name="icone" required/>
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