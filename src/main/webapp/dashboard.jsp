<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="br">
    <head>
        <title>Debatr - Página inicial</title>
        <meta charset="utf-8">
        <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
    </head>

    <body>
        <%@include file="layout.jsp" %>
        <div class="dashboard">
            <div class="posts">
                <c:forEach var="post" items="${requestScope.homePosts}">
                    <%@include file="components/_post.jsp" %>
                </c:forEach>
            </div>
        </div>
    </body>
</html>