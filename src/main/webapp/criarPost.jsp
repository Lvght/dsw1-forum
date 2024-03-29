<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/ajax.js" type="text/javascript"></script>
        <style>
            body{
                display: flex;
            }

            .criarPost{
                padding: 30px;
            }

            .criarPost h1{
                color: #00171F;
                font-size: 21px;
                margin-bottom: 0;
            }

            .criarPost hr{
                border-top: 1px solid black;
                margin: 0 0 1rem 0;
            }

            form{
                display: flex;
                flex-direction: column;
            }

            .criarPost .forumSelect{
                width: 50%;
            }

            .criarPost .selectTopic{
                width: 25%;
            }

            input, select, textarea{
                margin-bottom: 10px!important;
                padding: 10px;
                background-color: #142931;
                color: white;
                border-radius: 15px;
            }

            .criarPost .postTitle{
                margin-bottom: 0px!important;
            }

            textarea{
                min-height: 200px
            }

            input, select{
                height: 50px;
            }

            input[type="submit"], .discard{
                width: fit-content;
                height: 40px;
                padding: 0 15px;
                border-radius: 10px;
                border: 0;
            }

            .criarPost{
                width: 100%;
            }
            .criar .post{
                display: flex;
            }

            .actionButtons{
                display: flex;
                line-height: 35px;
            }

            .discard{
                background-color: white;
                border: 2px solid #142931!important;
                color: #142931;
                margin-right: 10px;
                cursor: pointer;
            }

            .counter{
                text-align: end;
                margin-bottom:10px;
                font-size: 12px;
                padding-right: 10px;
            }

            @media only screen and (max-width: 700px){
                .criarPost {
                    width: 100%;
                }
            }

        </style>
    </head>
    <body>
        <%@include file="layout.jsp" %>
        <div class="criarPost">
            <h1>Criar um novo post</h1>
            <hr />
            <form action="./post" method="POST">
                <input name="id_autor" value="${sessionScope.user.id}" hidden required/>

                <select class="forumSelect" name="id_forum" required onchange="genericXmlHttpRequest('${pageContext.request.contextPath}/forum/getTopicosForum', 'GET', 'id_forum=' + this.value, 'selectTopic');">
                    <option value="" selected disabled hidden>
                        Escolha um forum
                    </option>

                    <c:forEach var="forum" items="${requestScope.listaForuns}">
                        <option value="${forum.id}">
                            ${forum.titulo}
                        </option>
                    </c:forEach>  

                </select>

                <br/>
                <br/>

                <input class="postTitle" id="title" name="titulo" placeholder="Titulo" maxlength="255" required
                pattern="[^ ]+([^]*[^ ])*" title="Deve conter ao menos uma letra, não começar e nem terminar com espaço"/>
                
                <span id="counter" class="counter">255</span>

                <textarea name="conteudo" placeholder="Conteudo (suporta markdown)" required></textarea>

                <select id="selectTopic" class="selectTopic" name="id_topico">
                    <option value="" selected>
                        Geral
                    </option>
                </select>
                <br />
                <hr />
                <div class="actionButtons">
                    <div class="discard" onclick="window.history.back();">
                        <span>Descartar</span>
                    </div>
                    <input type="submit" value="Postar">
                </div> 
            </form>
        </div>
        <script>
            function changeCounter() { 
                $("#counter").html(255 - $("#title").val().length);
            }

            const debounce = (func, wait) => {
                let timeout;

                return function executedFunction(...args) {
                    const later = () => {
                        clearTimeout(timeout);
                        func(...args);
                    };

                    clearTimeout(timeout);
                    timeout = setTimeout(later, wait);
                };
            };

            $("#title").keyup(debounce(changeCounter, 500));
        </script>
    </body>
</html>