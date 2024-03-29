<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt">

<head>
    <title>Debatr - Criar conta</title>
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
            background-color: #404040;
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

        a,
        a:hover {
            text-decoration: none;
            color: white;
        }

        .errorMessage{
            font-size: 12px;
            color: red;
            margin-left: 15px;
            min-height: 20px;
            min-width: 100%;
            display: grid;
        }
    </style>
</head>

<body>
    <div class="jumbotron vertical-center">
        <div class="container">
            <div class="col p-4">
                <img src="resources/logo.png" alt="Logotipo do Debatr" height="40px">

                <br />
                <br />

                <h3>Criar conta no Debatr</h3>
                <form action="./register" method="POST" enctype="multipart/form-data">
                    <input placeholder="Nome" value="${requestScope.name}"  name="name" required 
                    pattern="[^ ]+([^]*[^ ])*" title="Deve conter ao menos uma letra, não começar e nem terminar com espaço"/>
                    <span class="errorMessage">${requestScope.message.name}</span>
                    <input placeholder="Nome de usuário" value="${requestScope.username}" name="username" required 
                    pattern="[^ ]*" title="Deve conter ao menos uma letra e não conter espaços"/>
                    <span class="errorMessage">${requestScope.message.username}</span>
                    <input placeholder="Email" value="${requestScope.email}" name="email" type="email" required />
                    <span class="errorMessage">${requestScope.message.email}</span>
                    <input name="fotoPerfil" type="file" />
                    <span class="errorMessage"></span> 
                    <input placeholder="Senha" name="password" type="password" required />
                    <span class="errorMessage">${requestScope.message.password}</span>

                    <input class="buttom" type="submit" value="Criar">
                </form>

                <hr />
                <a href="login">
                    <div class="buttom">
                        <span>
                            Entre na sua conta
                        </span>
                    </div>
                </a>
            </div>
        </div>
    </div>

</body>

</html>