<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="utf-8">      
        <style>
            .forumCard .forumIcon, .forumCard .userPicture{
                min-width: 40px;
                max-width: 40px;
                height: 40px;
                border-radius: 100%;
                overflow: hidden;
                display: grid;
                background-color: #f5f5f5;
                margin-right: 8px;
            }

            .forumCard .informations, .postuserInformation, .forumCard .postForumInformation{
                display: flex;
            }

            .forumCard .userInformation, .forumCard .postForumInformation{
                margin-right: 25px;
                margin-left: 0;
            }

            .forumCard .userInformation{
                color: #6b6b6b;
            }

            .forumCard .postForumInformation > *,  .forumCard .userInformation > *{
                display: grid;
                margin-top: auto;
                margin-bottom: auto;
            }

            .forumCard .forumIcon img, .forumCard .userPicture img{
                position: relative;
                width: 100%;
                margin: auto;
            }

            .forumCard .postHeader{
                border: 0;
                padding: 20px 0;
            }

            .forumCard .postHeader h2{
                font-size: 16px;
                margin-left: 10px;
                margin-top: auto;
                margin-bottom: auto;
            }

            .forumCard .titulo{
                padding: 5px 0;
                width: 250px;
            }

            .forumCard .titulo h2{
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 0;
                  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
            }

            .forumCard .conteudo h3{
                font-size: 16px;
                font-weight: 400;
            }

            .forumCard .footer{
                background: transparent;
                border-top: 0;
                padding: 0;
                display: grid;
            }

            .forumCard .topico{
                text-decoration: underline;
                padding: 30px 0;
                cursor: pointer;
            }

            .forumCard .topico h4{
                color: black;
                font-size: 16px;
                font-weight: bold;
            }

            .forumCard .discussao{
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

            .forumCard .discussao .userPicture{
                height: 30px;
                min-width: 30px;
                max-width: 30px;
                margin-right: 10px;
            }

            .forumCard .discussao span{
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
        <div class="forumCard">
            <div class="informations">
                <div class="postForumInformation">
                    <div class="forumIcon">
                        <img src="https://seeklogo.com/images/U/ufscar-logotipo-logo-E01F858A9C-seeklogo.com.png"/>
                    </div>
                    <div class="titulo">
                        <h2>${forum.titulo}</h2>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>