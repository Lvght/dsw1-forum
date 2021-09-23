<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="utf-8">
        <link href="${pageContext.request.contextPath}/css/_post.css" rel="stylesheet" type="text/css">

        <script>
            function seePostDetail(postId) {
                if (window.location.pathname + location.search != '${pageContext.request.contextPath}/post/detail?postId=' + postId)
                    window.location.href = '${pageContext.request.contextPath}/post/detail?postId=' + postId
            }

            function submitReaction(postId, reaction) {
                alert('Reação ao post ' + postId + ' do tipo ' + reaction + ' enviada!')

                const url = "${pageContext.request.contextPath}/react/"
                const payload = {
                    "postId": postId.toString(),
                    "type": reaction.toString()
                }

                jQuery.post(url, payload, function (data) {
                    console.log(data)
                })
            }
        </script>
    </head>

    <body>
        <div class="post" onclick="seePostDetail(${post.getId()})">
            <div class="postHeader">
                <div class="informations">
                    <a href="${pageContext.request.contextPath}/forum/especifico?id=${post.getForum().getId()}">
                        <div class="postForumInformation">
                            <div class="forumIcon">
                                <img src="https://seeklogo.com/images/U/ufscar-logotipo-logo-E01F858A9C-seeklogo.com.png"/>
                            </div>
                            <div class="forumName">
                                <h2>f: ${post.getForum().getTitulo()}</h2>
                            </div>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/perfil/${post.getAutor().getUsername()}">
                        <div class="userInformation">
                            <div class="userPicture">
                                <img src="https://i1.wp.com/terracoeconomico.com.br/wp-content/uploads/2019/01/default-user-image.png?ssl=1"/>
                            </div>
                            <div class="username">
                                <h2>@${post.getAutor().getUsername()}</h2>
                            </div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="body">
                <div class="titulo">
                    <h2>${post.titulo}</h2>
                </div>
                <div id="content${post.id}" class="conteudo">
                    <h2>${post.conteudo}</h2>
                </div>
            </div>

            <div class="footer">
                <div class="topico">
                    <a href="${pageContext.request.contextPath}/forum/especifico?id=${post.id_forum}&topico=${post.topico != null ? post.topico.id : 0}">
                        <h4>#: ${post.getTopico() != null ? post.getTopico().getNome() : "Geral"}</h4>
                    </a>
                </div>

                <div class="container p-0">
                    <div class="row align-items-center p-0">

                        <!-- Botão de engajamento -->
                        <div class="col-auto">
                            <div class="row align-items-center engage-background p-0">
                                <div class="col-auto engage-tile-content">
                                    <img class="rounded-circle" height="32px" width="32px" display
                                         src="https://i1.wp.com/terracoeconomico.com.br/wp-content/uploads/2019/01/default-user-image.png?ssl=1"/>
                                    <span class="me-2">Participar da Discussão</span>
                                </div>
                            </div>
                        </div>

                        <!-- Botão de like -->
                        <div class="col-auto reaction-btn-container" onclick="submitReaction(${post.id}, 0)">
                            <img width="30px" height="27px"
                                 src="${pageContext.request.contextPath}/resources/u-like.png"/>
                        </div>

                        <!-- Botão de deslike -->
                        <div class="col-auto reaction-btn-container" onclick="submitReaction(${post.id}, 1)">
                            <img width="30px" height="27px"
                                 src="${pageContext.request.contextPath}/resources/u-deslike.png"/>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <hr/>

        <script>
            document.getElementById('content${post.id}').innerHTML = marked('${post.conteudo}');
        </script>
    </body>
</html>