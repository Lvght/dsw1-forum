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
                color: black;
            }

            body > *{
                color: black;
            }

            .forum{
                width: 60%;
                padding: 30px;
            }

            .forum .details{
                display: flex;
            }

            .details > * {
                margin-top: auto;
                margin-bottom: auto;
            }

            .forum .forumIcon{
                width: 75px;
                height: 75px;
                border-radius: 100%;
                overflow: hidden;
                display: grid;
                background-color: #f5f5f5;
            }

            .forum .forumIcon img{
                position: relative;
                width: 100%;
                margin: auto;
            }

            .forum .forumInformation{
                margin-left: 10px;
            }

            .forum .details h1{
                font-size: 24px;
                font-weight: bolder;
            }

            .forum .details h2{
                font-size: 13px;
                font-weight: 400;
            }

            .forum .description h2{
                font-size: 16px;
                font-weight: 400;
            }

            .forum .action{
                margin-left: auto;
            }

            .forum .action input{
                width: fit-content;
                height: 40px;
                padding: 0 15px;
                border-radius: 10px;
                border: 0;
                background-color: #142931;
                color: white;
            }

            .forum .description{
                padding: 20px 0 0 0;
            }

            .posts{
                padding: 0 10px;
            }


        </style>
    </head>
    <body>
        <%@include file="../layout.jsp" %>
        <div class="forum">
            <div class="details">
                <div class="forumIcon">
                    <img src="https://seeklogo.com/images/U/ufscar-logotipo-logo-E01F858A9C-seeklogo.com.png"/>
                </div>
                <div class="forumInformation">
                    <h1>${requestScope.forum.titulo}</h1>
                    <h2>129 membros • ${requestScope.forum.escopo_acesso == 1 ? "livre" : "restrito"}</h2>
                </div>
                <div class="action">
                    <input type="submit" value="Ingressar">
                </div>
            </div>
            <div class="description">
                <h2>${requestScope.forum.descricao}</h2>
            </div>
            <hr/>
            <div class="posts">
                <c:forEach var="post" items="${requestScope.posts}">
                    <%@include file="components/_post.jsp" %>
                </c:forEach>  
            </div>
        </div>
    </body>
</html>