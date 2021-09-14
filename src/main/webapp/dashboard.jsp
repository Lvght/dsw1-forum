<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="br">
    <head>
        <title>Debatr - Página inicial</title>
        <meta charset="utf-8">
    </head>

    <body>
        <%@include file="layout.jsp" %>
        <div class="dashboard">
            <c:forEach var="post" items="${requestScope.homePosts}">
                <%@include file="components/_post.jsp" %>
            </c:forEach>
        </div>
    </body>
</html>