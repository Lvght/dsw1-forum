<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt">

<head>
    <title>JSP - Hello World</title>
    <meta charset="utf8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!--  Importa os arquivos do Bootstrap  -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

    <style>
        body {
            background-color: #DCDCDC;
        }

        input {
            width: 100%;
            border-radius: 30px;
            height: 50px;
            border-color: transparent;
            margin-top: 10px;
            background-color: #C4C4C4;
            padding: 10px;
        }

        .vertical-center {
            min-height: 100vh;
            display: flex;
            align-items: center;
        }

        .jumbotron {
            margin-bottom: 0 !important;
        }

        .container {
            width: 100%;
            max-width: 530px;
        }

        hr {
            border-top: 2px solid #6c6c6c;
        }

        .buttom {
            background-color: #404040 !important;
            border-radius: 30px;
            height: 50px;
            justify-content: center;
            display: grid;
            color: white;
        }

        .buttom span {
            vertical-align: middle;
            height: fit-content;
            margin: auto;
            color: white;
        }

        a {
            color: #00171F;
            transition: 1s ease-in;
        }

        a:hover {
            text-decoration: none;
            color: black;
        }

        .errorMessage{
            font-size: 12px;
            color: red;
            padding-left: 15px;
        }
    </style>
</head>

<body>
    <div class="jumbotron vertical-center">
        <div class="container">
            <div class="col p-4">
                <img src="${pageContext.request.contextPath}/resources/logo.png" alt="Logotipo do Debatr" height="40px">

                <br />
                <div class="py-4">
                    <h2 class="fw-bold">Bem vindo(a)</h2>
                </div>

                <span>Entre em sua conta para acessar o sistema</span>

                <c:if test="${(not empty requestScope.message)}" >
                    <br/>
                    <span class="errorMessage"> ${requestScope.message} </span>
                </c:if>

                <form action="${pageContext.request.contextPath}/login" method="post">
                    <input type="text" placeholder="Nome de usuário" value="${requestScope.username}" name="username" required>
                    <input type="password" placeholder="Senha" name="password" required>

                    <input class="buttom" type="submit" value="Entrar">
                </form>
                <hr />
                <a href="${pageContext.request.contextPath}/register">
                    <div class="buttom">
                        <span>
                            Criar conta
                        </span>
                    </div>
                </a>

                <div class="container text-center pt-4">
                    <a href="${pageContext.request.contextPath}/request-password-reset">Esqueci minha senha</a>
                </div>
            </div>
        </div>
    </div>

</body>

</html>