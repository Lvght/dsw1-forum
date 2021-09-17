<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="br">
    <head>
        <title>Debatr - Página inicial</title>
        <meta charset="utf-8">
        <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
        <style>
            .dashboard h5{
                margin-top: 40px;
                text-align: center;
                font-size: 1rem;
            }
        </style>
    </head>

    <body>
        <%@include file="layout.jsp" %>
        <div class="dashboard">
            <c:choose>
                <c:when test="${requestScope.homePosts.size() > 0}">
                    <div class="posts">
                        <c:forEach var="post" items="${requestScope.homePosts}">
                            <%@include file="components/_post.jsp" %>
                        </c:forEach>
                    </div>
                    <%@include file="components/_paginator.jsp" %>
                </c:when>    
                <c:otherwise>
                    <h5> Meio vazio por aqui </h5>
                    <h5> Que tal ingressar em alguns fóruns? </h5>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>