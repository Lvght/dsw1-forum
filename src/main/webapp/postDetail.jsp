<%--
  Created by IntelliJ IDEA.
  User: lvght
  Date: 14/09/2021
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Post de @${requestScope.post.autor.username} em ${requestScope.post.forum.titulo}</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <script>
            function loadComments() {
                const url = '${pageContext.request.contextPath}/comments/?postId=${requestScope.post.id}'

                // Faz um GET, e passa os dados para a função anônima.
                jQuery.get(url, function (data) {
                    document.getElementById("comment-timeline").innerHTML = data;
                });

            }

            loadComments();
        </script>
    </head>
    <body>
        <div class="left">
            <%@ include file="components/_sidebar.jsp" %>
        </div>
        <%@ include file="components/_sidebarRight.jsp" %>
        <%@ include file="components/_post.jsp" %>

        <div id="comment-timeline"></div>
    </body>
</html>
