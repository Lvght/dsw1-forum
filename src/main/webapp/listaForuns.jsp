<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Foruns</title>
        <style>
            body{
                display: flex;
            }

                        body{
                display: flex;
                color: black;
            }

            body > *{
                color: black;
            }

            .forum{
                width: 100%;
                padding-top: 30px;
            }

            .description h2{
                display: -webkit-box;
                -webkit-line-clamp: 2;
                -webkit-box-orient: vertical;
                overflow: hidden;
            }

            .forum .forumInformation a:hover{
                color: #02181f;
                text-decoration: underline;
            }


            @media only screen and (max-width: 700px){
                .forum{
                    width: 100%;
                }
            }
        </style>
    </head>
    <body>
        <%@include file="layout.jsp" %>
        <div class="listaForum">
            <c:forEach var="forum" items="${requestScope.listaForuns}">
                    <c:choose>
                        <c:when test="${forum.eh_membro eq true}">
                            <c:set var="status" value="sair" />
                        </c:when>    
                        <c:otherwise>
                            <c:set var="status" value="ingressar" />
                        </c:otherwise>
                    </c:choose>
                <div class="forum">
                    <%@include file="../components/_forumInformations.jsp" %>
                </div>
            </c:forEach>  
        </div>
    </body>
</html>