<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div class="forum">
            <h2>Titulo: ${requestScope.forum.titulo}</h2>
            <h3>Descricao: ${requestScope.forum.descricao}</h3>
            <h3>Icone: ${requestScope.forum.icone}</h3>
            <h3>Id dono: ${requestScope.forum.id_dono}</h3>
            <h3>Escopo de Postagem: ${requestScope.forum.escopo_postagem}</h3>
            <h3>Escopo de acesso: ${requestScope.forum.escopo_acesso}</h3>
        </div>
    </body>
</html>