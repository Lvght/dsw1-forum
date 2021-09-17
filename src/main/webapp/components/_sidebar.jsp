<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <div class="header_toggle "><i class='bx bx-menu bx-x' id="header-toggle"></i></div>
            <div class="header_img"><img src="https://i.imgur.com/hczKIze.jpg" alt=""></div>
        </header>
        <div class="l-navbar show" id="nav-bar">
            <nav class="nav">
                <div>
                    <a href="${pageContext.request.contextPath}/post/dashboard" class="nav_logo">
                        <img src="${pageContext.request.contextPath}/resources/logoReduzido.png" alt="">
                        <span class="nav_logo-name">Debatr</span>
                    </a>
                    <div class="nav_list">
                        <a href="${pageContext.request.contextPath}/post/dashboard"
                           class="nav_link ${pageContext.request.servletPath == "/dashboard.jsp" ? "active" : ""}">
                            ${pageContext.request.servletPath == "/dashboard.jsp" ? "<i class='bx bxs-grid-alt'></i>" : "<i class='bx bx-grid-alt nav_icon'></i>" }
                            <span class="nav_name">Início</span>
                        </a>
                        <c:if test="${sessionScope.user != null}" >
                            <a href="${pageContext.request.contextPath}/post/criar" class="nav_link ${pageContext.request.servletPath == "/criarPost.jsp" ? "active" : ""}">
                                ${pageContext.request.servletPath == "/criarPost.jsp" ? "<i class='bx bxs-message-square-add'></i>" : "<i class='bx bx-message-square-add'></i>"}
                                <span class="nav_name">Novo Post</span>
                            </a>
                            <a href="${pageContext.request.contextPath}/forumForm.jsp"
                            class="nav_link ${pageContext.request.servletPath == "/forumForm.jsp" ? "active" : ""}">
                                ${pageContext.request.servletPath == "/forumForm.jsp" ? "<i class='bx bxs-group' ></i>" : "<i class='bx bx-group'></i>"}
                                <span class="nav_name">Criar Forum</span>
                            </a>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/forum"
                           class="nav_link ${pageContext.request.servletPath == "/listaForuns.jsp" ||  pageContext.request.servletPath == "/forum.jsp"? "active" : ""}">
                            ${pageContext.request.servletPath == "/listaForuns.jsp" ||  pageContext.request.servletPath == "/forum.jsp" ? "<i class='bx bxs-message-square-detail'></i>" : "<i class='bx bx-message-square-detail nav_icon'></i>"}
                            <span class="nav_name">Listar Foruns</span>
                        </a>
                    </div>
                    <c:if test="${sessionScope.user != null}" >
                        <a href="${pageContext.request.contextPath}/perfil/${sessionScope.user.username}" class="nav_link">
                            <img src="https://i1.wp.com/terracoeconomico.com.br/wp-content/uploads/2019/01/default-user-image.png"
                                class="rounded-circle p-0" style="height: 16px; width: 16px;"/>
                            <span class="nav_name">Meu perfil</span>
                        </a>
                    </c:if>
                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                            <a href="${pageContext.request.contextPath}/login/logout" class="nav_link">
                                <i class='bx bx-log-out nav_icon'></i>
                                <span class="nav_name">Sair da conta</span>
                            </a>
                        </c:when>    
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/login" class="nav_link">
                                <i class='bx bx-log-in nav_icon'></i>
                                <span class="nav_name">Logar</span>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </nav>
        </div>
        <!--Container Main start-->
    </body>
</html>