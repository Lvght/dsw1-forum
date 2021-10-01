<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${forum.titulo}</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <script src="${pageContext.request.contextPath}/js/ajax.js" type="text/javascript"></script>
        <style>
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

            .forum .forumInformation a{
                color: #02181f;
            }

            .forum .forumInformation a:hover{
                color: #02181f;
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

            .forum .lockImage{

                margin-left: auto;
            }

            .forum .lockImage i{
                padding: 30px;
                font-size: 30px;
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
        <div class="details">
            <div class="forumIcon">
                <img src="${not empty forum.icone ? forum.icone : "https://seeklogo.com/images/U/ufscar-logotipo-logo-E01F858A9C-seeklogo.com.png"}"/>
            </div>
            <div class="forumInformation">
                <a href="${pageContext.request.contextPath}/forum/especifico?id=${forum.id}">
                    <h1>${forum.titulo}</h1>
                </a>
                <h2>${forum.membros} membro${forum.membros == 1 ? "" : "s"} • ${forum.escopo_acesso == 1 ? "livre" : "restrito"}</h2>
            </div>
            <c:choose>
                <c:when test="${(not empty requestScope.status || not empty status) && not empty sessionScope.user && (forum.escopo_acesso == 1 || sessionScope.user.academicRecord != 0) }" >
                    <%@include file="./_button.jsp" %>
                </c:when>    
                <c:otherwise>
                    <c:if test="${forum.escopo_acesso == 2}" >
                        <div class="lockImage">
                            <i class='bx bxs-lock-alt'></i>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="description">
            <h2>${forum.descricao}</h2>
        </div>
        <hr/>
    </body>
</html>