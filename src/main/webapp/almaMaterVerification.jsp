<%--
  Created by IntelliJ IDEA.
  User: lvght
  Date: 16/09/2021
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Verificar Instituição de Ensino</title>
    </head>
    <body>

        <%@ include file="components/_sidebar.jsp" %>

        <form accept-charset="${pageContext.request.contextPath}/verify/" method="post" autocomplete="off">
            <input name="ra" type="number" placeholder="RA">
            <input name="password" type="password" placeholder="Senha">

            <input type="submit" value="Verificar">
        </form>
    </body>
</html>
