<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="utf-8">      
        <style>
            .forum .action{
                margin-left: auto;
            }

            .forum .action input{
                width: 100px;
                height: 40px;
                padding: 0 15px;
                border-radius: 10px;
                border: 0;
                transition: 0.1s;
            }

            .forum .action input:hover{
                filter: brightness(1.5);
                box-shadow: 0 10px 10px -10px rgb(0 0 0 / 17%);
                -webkit-transform: scale(1.1);
                transform: scale(1.1);    
                transition: 0.1s;        
            }

            .forum .ingressar{
                background-color: #142931;
                color: white;
            }

            .forum .sair{
                background-color: transparent;
                color: #142931;
                border: 2px solid #142931!important;
            }
        </style>
  </head>

    <body>
        <c:if test="${empty forum}" >
            <c:set var="forum" value="${requestScope.forum}"/>
        </c:if>
        <c:choose>
            <c:when test="${requestScope.status == 'ingressar' || status == 'ingressar'}">
                <div class="action" id="ingressar${forum.id}">
                    <input class="ingressar" type="submit" value="Ingressar" onclick="genericXmlHttpRequest('${pageContext.request.contextPath}/forum/ingressarForum', 'GET', 'id_forum=${forum.id}', 'ingressar${forum.id}');">
                </div>    
            </c:when>    
            <c:otherwise>
                <div class="action" id="sair${forum.id}">
                    <input class="sair" type="submit" value="Sair" onclick="genericXmlHttpRequest('${pageContext.request.contextPath}/forum/sairForum', 'GET', 'id_forum=${forum.id}', 'sair${forum.id}');">
                </div>   
            </c:otherwise>
        </c:choose>
    </body>
</html>