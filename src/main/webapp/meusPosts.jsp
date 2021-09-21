<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="br">
    <head>
        <title>Debatr - Meus Posts</title>
        <meta charset="utf-8">
        <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
        <style>
            .myPosts h5{
                margin-top: 40px;
                text-align: center;
                font-size: 1rem;
            }
        </style>
    </head>

    <body>
        <%@include file="layout.jsp" %>
        <div class="myPosts">
            <c:choose>
                <c:when test="${requestScope.myPosts.size() > 0}">
                    <div class="posts">
                        <c:forEach var="post" items="${requestScope.myPosts}">
                            <%@include file="components/_post.jsp" %>
                        </c:forEach>
                    </div>
                    <%@include file="components/_paginator.jsp" %>
                </c:when>    
                <c:otherwise>
                    <h5> Você não tem nenhum post</h5>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>