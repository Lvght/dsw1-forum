<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div class="listaForum">
            <c:forEach var="forum" items="${requestScope.listaForuns}">
                    <h2>Titulo: <a href="./forum/especifico?id=${forum.id}">${forum.titulo}</a></h2>
                    <h3>Descricao: ${forum.descricao}</h3>
                    <hr/>
            </c:forEach>  
        </div>
    </body>
</html>