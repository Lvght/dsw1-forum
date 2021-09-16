<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${requestScope.forum.titulo}</title>
        <script src="../js/ajax.js" type="text/javascript"></script>
        <style>
            body{
                display: flex;
                color: black;
            }

            body > *{
                color: black;
            }

            .forum{
                width: 100%;
                padding: 30px 0;
            }

            .forum .details{
                display: flex;
            }

            .details > * {
                margin-top: auto;
                margin-bottom: auto;
            }

            .forum .forumIcon{
                min-width: 75px;
                max-width: 75px;
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

            .forum .description{
                padding: 20px 0 0 0;
            }

            @media only screen and (max-width: 700px){
                .forum{
                    width: 100%;
                }

                .forum .details{
                    flex-wrap: wrap;
                }

                .forum .action{
                    margin-top: 20px;
                }

                .forum .details > * {
                    margin-top: auto;
                    margin-bottom: auto;
                    margin: auto;
                }

                .forum .forumIcon{
                    margin-bottom: 10px;
                }

                .forum .forumInformation h2{
                    text-align: center;
                }
            }


        </style>
    </head>
    <body>
        <%@include file="../layoutForum.jsp" %>
        <div class="forum">
            <div class="details">
                <div class="forumIcon">
                    <img src="https://seeklogo.com/images/U/ufscar-logotipo-logo-E01F858A9C-seeklogo.com.png"/>
                </div>
                <div class="forumInformation">
                    <h1>${requestScope.forum.titulo}</h1>
                    <h2>${requestScope.forum.membros} membro${requestScope.forum.membros == 1 ? "" : "s"} • ${requestScope.forum.escopo_acesso == 1 ? "livre" : "restrito"}</h2>
                </div>
                <c:if test="${not empty requestScope.status}" >
                    <%@include file="../components/_button.jsp" %>
                </c:if>
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
            <%@include file="components/_paginator.jsp" %>
        </div>
    </body>
</html>