<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${requestScope.forum.titulo}</title>
        <script src="${pageContext.request.contextPath}/js/ajax.js" type="text/javascript"></script>
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


            @media only screen and (max-width: 700px){
                .forum{
                    width: 100%;
                }
            }
        </style>
    </head>
    <body>
        <%@include file="../layoutForum.jsp" %>
        <div class="forum">
            <c:set var="forum" value="${requestScope.forum}"/>
            <%@include file="../components/_forumInformations.jsp" %>
            <div class="posts">
                <c:forEach var="post" items="${requestScope.posts}">
                    <%@include file="components/_post.jsp" %>
                </c:forEach>  
            </div>
            <%@include file="components/_paginator.jsp" %>
        </div>
    </body>
</html>