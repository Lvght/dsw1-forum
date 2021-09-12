<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

            input[type="submit"], .discard{
                width: fit-content;
                height: 40px;
                padding: 0 15px;
                border-radius: 10px;
                border: 0;
            }

            .criarPost{
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
            @media only screen and (max-width: 700px){
                .criarPost {
                    width: 100%;
                }
            }

        </style>
    </head>
    <body>
        <%@include file="layout.jsp" %>
        <div class="criarPost">
            <h1>Criar um novo post</h1>
            <hr />
            <form action="./post" method="POST">
                <input name="id_autor" value="${sessionScope.user.id}" hidden required/>

                <select class="forumSelect" name="id_forum" required>
                    <option value="" selected disabled hidden>
                        Escolha um forum
                    </option>

                    <c:forEach var="forum" items="${requestScope.listaForuns}">
                        <option value="${forum.id}">
                            ${forum.titulo}
                        </option>
                    </c:forEach>  

                </select>

                <br/>
                <br/>

                <input class="postTitle" name="titulo" placeholder="Titulo" required/>

                <textarea name="conteudo" placeholder="Conteudo (suporta markdown)" required></textarea>

                <select class="selectTopic" name="id_topico">
                    <option value="" selected>
                        Geral
                    </option>
                </select>
                <br />
                <hr />
                <div class="actionButtons">
                    <div class="discard" onclick="window.history.back();">
                        <span>Descartar</span>
                    </div>
                    <input type="submit" value="Postar">
                </div> 
            </form>
        </div>
    </body>
</html>