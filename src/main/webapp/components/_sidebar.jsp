<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="utf-8">      
        <style>
            .sidebar{
                height: 100vh;
                background-color: #00171F;
            }

            .nav-title{
              display: flex;    
              justify-content: center;   
              align-items: center;
            }

            .nav-title h1{
              padding: 10px;
              font-size: 30px;
              margin: 0;
            }

        </style>
  </head>

    <body>
      <div class="sidebar sidebar-show">
        <ul class="sidebar-nav">
          <li class="nav-title">
            <img src="resources/logoReduzido.png" alt="Logotipo do Debatr" height="40px">
            <h1>Debatr</h1>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./home.jsp">
              <i class="nav-icon cil-speedometer"></i>Inicio
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./forumForm.jsp">
              <i class="nav-icon cil-speedometer"></i>Criar Forum
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./forum">
              <i class="nav-icon cil-speedometer"></i>Listar Foruns
            </a>
          </li>
        </ul>
        <button class="sidebar-toggler" type="button"></button>
      </div>
    </body>
</html>