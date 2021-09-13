<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="utf-8">     
               <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
               <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
               <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
               <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script> 
               <script src="${pageContext.request.contextPath}/js/sidebar.js"></script> 
 </head>

<body id="body-pd" class="body-pd body-pd-left">
    <header class="header body-pd" id="header">
        <div class="header_toggle "> <i class='bx bx-menu bx-x' id="header-toggle"></i> </div>
        <div class="header_img"> <img src="https://i.imgur.com/hczKIze.jpg" alt=""> </div>
    </header>
    <div class="l-navbar show" id="nav-bar">
        <nav class="nav">
            <div> 
              <a href="${pageContext.request.contextPath}/post/dashboard" class="nav_logo"> 
                <img src="${pageContext.request.contextPath}/resources/logoReduzido.png" alt=""> 
                <span class="nav_logo-name">Debatr</span> 
              </a>
              <div class="nav_list"> 
                <a href="${pageContext.request.contextPath}/post/dashboard" class="nav_link ${pageContext.request.servletPath == "/dashboard.jsp" ? "active" : ""}"> 
                  <i class='bx bx-grid-alt nav_icon'></i> 
                  <span class="nav_name">Início</span> 
                </a> 
                <a href="${pageContext.request.contextPath}/forumForm.jsp" class="nav_link ${pageContext.request.servletPath == "/forumForm.jsp" ? "active" : ""}"> 
                  <i class='bx bx-user nav_icon'></i> 
                  <span class="nav_name">Criar Forum</span> 
                </a> 
                <a href="${pageContext.request.contextPath}/forum" class="nav_link ${pageContext.request.servletPath == "/listaForuns.jsp" ||  pageContext.request.servletPath == "/forum.jsp"? "active" : ""}">
                  <i class='bx bx-message-square-detail nav_icon'></i> 
                  <span class="nav_name">Listar Foruns</span>
                </a> 
                <a href="${pageContext.request.contextPath}/post" class="nav_link ${pageContext.request.servletPath == "/criarPost.jsp" ? "active" : ""}"> 
                  <i class='bx bx-bookmark nav_icon'></i> 
                  <span class="nav_name">Criar Post</span>
                </a> 
            </div> 
            <a href="${pageContext.request.contextPath}/index.html" class="nav_link"> 
              <i class='bx bx-log-out nav_icon'></i> 
              <span class="nav_name">SignOut</span> 
            </a>
          </div>
        </nav>
    </div>
    <!--Container Main start-->
  </body>
</html>