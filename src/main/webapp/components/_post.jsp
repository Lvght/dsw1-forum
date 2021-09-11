<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="utf-8">      
        <style>
            .post .forumIcon, .post .userPicture{
                min-width: 40px;
                max-width: 40px;
                height: 40px;
                border-radius: 100%;
                overflow: hidden;
                display: grid;
                background-color: #f5f5f5;
            }

            .informations, .userInformation, .postForumInformation{
                display: flex;
            }

            .userInformation, .postForumInformation{
                margin-right: 25px;
                margin-left: 0;
            }

            .post .userInformation{
                color: #6b6b6b;
            }

            .post .postForumInformation > *,  .post .userInformation > *{
                display: grid;
                margin-top: auto;
                margin-bottom: auto;
            }

            .post .forumIcon img, .post .userPicture img{
                position: relative;
                width: 100%;
                margin: auto;
            }

            .post .postHeader{
                border: 0;
                padding: 20px 0;
            }

            .post .postHeader h2{
                font-size: 16px;
                margin-left: 10px;
                margin-top: auto;
                margin-bottom: auto;
            }

            .post .titulo{
                padding: 5px 0;
            }

            .post .titulo h2{
                font-size: 20px;
                font-weight: bold;
            }

            .post .conteudo h3{
                font-size: 16px;
                font-weight: 400;
            }

            .post .footer{
                background: transparent;
                border-top: 0;
                padding: 0;
                display: grid;
            }

            .post .topico{
                text-decoration: underline;
                padding: 30px 0;
                cursor: pointer;
            }

            .post .topico h4{
                color: black;
                font-size: 16px;
                font-weight: bold;
            }

            .post .discussao{
                width: fit-content;
                height: 40px;
                padding: 3px 10px 3px 3px;
                border-radius: 30px;
                border: 0;
                background-color: #142931;
                color: white;
                display: flex;
                align-items: center;
                cursor: pointer;
            }

            .post .discussao .userPicture{
                height: 30px;
                min-width: 30px;
                max-width: 30px;
                margin-right: 10px;
            }

            .post .discussao span{
                font-size: 16px;
            }
            @media only screen and (max-width: 700px){
                .informations, .userInformation, .postForumInformation {
                    flex-wrap: wrap;
                }
            }

        </style>
  </head>

    <body>
        <div class="post">
            <div class="postHeader">
                <div class="informations">
                    <div class="postForumInformation">
                        <div class="forumIcon">
                            <img src="https://seeklogo.com/images/U/ufscar-logotipo-logo-E01F858A9C-seeklogo.com.png"/>
                        </div>
                        <div class="forumName">
                            <h2>f: ${post.getForum().getTitulo()}</h2>
                        </div>
                    </div>
                    <div class="userInformation">
                        <div class="userPicture">
                            <img src="https://i1.wp.com/terracoeconomico.com.br/wp-content/uploads/2019/01/default-user-image.png?ssl=1"/>
                        </div>
                        <div class="username">
                            <h2>@${post.getAutor().getUsername()}</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="body">
                <div class="titulo">
                    <h2>${post.titulo}</h2>
                </div>
                <div class="conteudo">
                    <h3>${post.conteudo}</h2>
                </div>
            </div>

            <div class="footer">
                <div class="topico">
                    <h4>#: Geral</h4>
                </div>
                <div class="discussao">
                    <div class="userPicture">
                        <img src="https://i1.wp.com/terracoeconomico.com.br/wp-content/uploads/2019/01/default-user-image.png?ssl=1"/>
                    </div>
                    <span>Participar da discussao</span>
                </div>
            </div>
            <hr/>
        </div>
    </body>
</html>