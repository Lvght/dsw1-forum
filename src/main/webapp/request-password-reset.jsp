<%--
  Created by IntelliJ IDEA.
  User: lvght
  Date: 30/09/2021
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Debatr - Alterar senha</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/request-password-reset" method="post">
            <label>
                Email
                <input type="email" name="email" placeholder="Digite aqui...">
            </label>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
