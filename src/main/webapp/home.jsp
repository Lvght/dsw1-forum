<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="br">
    <head>
        <title>Debatr</title>
        <meta charset="utf-8">
        <style>
            body{
                display: flex;
            }
        </style>
    </head>
    <body>
        <%@include file="layout.jsp" %>
        <div class="home">
                <p>Eh isso ai!</p>
                <p>${sessionScope.user.name}</p>
        </div>
</body>
</html>