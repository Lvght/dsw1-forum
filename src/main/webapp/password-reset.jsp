<%--
  Created by IntelliJ IDEA.
  User: lvght
  Date: 30/09/2021
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Debatr - Alterar senha</title>
    </head>
    <body>
        <form id="password-form" action="${pageContext.request.contextPath}/reset-password" method="post">
            <label>
                Digite sua nova senha
                <input name="password" placeholder="digite aqui">
            </label>

            <input type="hidden" name="token" value="${pageContext.request.getParameter("token")}">
            <input type="submit" value="Alterar senha">
        </form>
    </body>
</html>
