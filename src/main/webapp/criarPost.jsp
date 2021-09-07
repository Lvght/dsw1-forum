<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>

        <style>
            body{
                display: flex;
            }

            .criarPost{
                padding: 30px;
            }

            .criarPost h1{
                color: #00171F;
                font-size: 21px;
                margin-bottom: 0;
            }

            .criarPost hr{
                border-top: 1px solid black;
                margin: 0 0 1rem 0;
            }

            form{
                display: flex;
                flex-direction: column;
            }

            .criarPost .forumSelect{
                width: 50%;
            }

            .criarPost .postTitle{
                margin-bottom: 10px;
            }

            .criarPost .selectTopic{
                width: 25%;
            }

            input, select, textarea{
                margin-bottom: 10px!important;
                padding: 10px;
                background-color: #142931;
                color: white;
                border-radius: 15px;
            }

            textarea{
                min-height: 200px
            }

            input, select{
                height: 50px;
            }

            input[type="submit"]{
                width: fit-content;
                height: 40px;
                padding: 0 15px;
                border-radius: 10px;
                border: 0;
            }

            .criarPost{
                width: 60%;
            }
            .criar .post{
                display: flex;
            }

            .discard{
                background-color: white;
                border: 2px solid #142931!important;
                color: #142931;
            }

        </style>
    </head>
    <body>
        <%@include file="layout.jsp" %>
        <div class="criarPost">
            <h1>Criar um novo post</h1>
            <hr />
            <form action="./forum" method="POST">
                <input name="id_dono" value="${sessionScope.user.id}" hidden required/>

                <select class="forumSelect" name="id_forum" required>
                    <option value=0 selected disabled hidden>
                        Escolha um forum
                    </option>

                    <option value=1>
                        UFSCar
                    </option>

                    <option value=2>
                        DC
                    </option>

                </select>

                <br/>
                <br/>

                <input class="postTitle" name="titulo" placeholder="Titulo" required/>

                <textarea name="conteudo" placeholder="Conteudo (suporta markdown)" required></textarea>

                <select class="selectTopic" name="id_topico" required>
                    <option value=0 selected disabled hidden>
                        Geral
                    </option>

                    <option value=1>
                        CAP
                    </option>

                    <option value=2>
                        DSW1
                    </option>

                </select>
                <br />
                <hr />
                <div class="actionButtons">
                    <input class="discard" type="submit" value="Descartar">
                    <input type="submit" value="Postar">
                </div> 
            </form>
        </div>
    </body>
</html>