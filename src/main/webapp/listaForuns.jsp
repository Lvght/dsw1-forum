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
                    <h2>Titulo: ${forum.titulo}</h2>
                    <h3>Descricao: ${forum.descricao}</h3>
                    <h3>Icone: ${forum.icone}</h3>
                    <h3>Id dono: ${forum.id_dono}</h3>
                    <h3>Escopo de Postagem: ${forum.escopo_postagem}</h3>
                    <h3>Escopo de acesso: ${forum.escopo_acesso}</h3>
                    <hr/>
            </c:forEach>  
        </div>
    </body>
</html>