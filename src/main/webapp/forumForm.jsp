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

            .forumForm{
                width: 60%;
            }

            body{
                display: flex;
            }
        </style>
    </head>
    <body>
        <%@include file="layout.jsp" %>
        <div class="forumForm">
            <h1>Criar um forum</h1>
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
                <input type="submit" value="Enviar">
            </form>
        </div>
    </body>
</html>