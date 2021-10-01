<%--
  Created by IntelliJ IDEA.
  User: lvght
  Date: 30/09/2021
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Debatr - Email enviado</title>
        <meta charset="UTF-8">

        <!--  Importa os arquivos do Bootstrap  -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container-fluid px-2 px-lg-5 px-xl-5 pt-4">
            <div class="row">
                <div class="col-auto mb-5">
                    <img src="${pageContext.request.contextPath}/resources/logo.png" height="64px"
                         alt="Logotipo do Debatr">
                </div>
            </div>

            <h1>Verifique o seu email</h1>
            <p>Se o endereço informado corresponder a alguma das contas cadastradas em nosso sistema, enviaremos um email com as instruções para trocar de senha.</p>

            <a href="https://mail.google.com" target="_self" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Abrir GMail</a>
            <a href="https://mail.google.com" target="_self" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Abrir Outlook</a>
        </div>
    </body>
</html>
