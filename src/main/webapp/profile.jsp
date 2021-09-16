<%@ page import="br.ufscar.dsw1.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: lvght
  Date: 16/09/2021
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>

        <script>
            function moveToVerifyPage() {
                window.location.replace("${pageContext.request.contextPath}/verify/")
            }
        </script>
    </head>
    <body>
        <%@include file="components/_sidebar.jsp" %>
        <%@include file="components/_sidebarRight.jsp" %>

        <div class="container-fluid">
            <div class="row align-items-center mt-5 mb-2">
                <div class="col-auto">
                    <img src="https://exame.com/wp-content/uploads/2021/02/bill.jpg"
                         class="rounded-circle p-0"
                         style="width: 48px; height: 48px; object-fit: cover"/>
                </div>
                <div class="col-auto p-0">
                    <span class="font-weight-bold d-block">${requestScope.profileOwner.name}</span>
                    <span class="d-block">@${requestScope.profileOwner.username}</span>
                </div>
                <div class="col text-end p-0">
                    ${sessionScope.user.id == requestScope.profileOwner.id ? "<input type='button'
                                                                                     value='Editar perfil'>" : ""}
                </div>
            </div>

            <p class="mt-4 mb-3 p-0">O abacate é o fruto comestível do abacateiro, uma árvore da família da Lauraceae
                nativa do México ou da América do Sul, hoje extensamente cultivada em regiões tropicais e subtropicais,
                inclusive nas Ilhas Canárias, na Ilha da Madeira e na Sicília.</p>

            <h1 class="mt-4">Estatísticas</h1>
            <div class="row align-items-center">
                <div class="col-auto align-items-center justify-content-center">
                    <span class="d-block" style="font-size: 2.5rem">
                        18
                    </span>
                    <span class="d-block text-center">posts</span>
                </div>

                <div class="col-auto align-items-center justify-content-center">
                    <span class="d-block text-center" style="font-size: 2.5rem">
                        +3
                    </span>
                    <span class="d-block text-center">reputação</span>
                </div>

                <div class="col-auto align-items-center justify-content-center">
                    ${
                        requestScope.profileOwner.academicRecord != 0
                            ? "<img class='text-center' src='https://lh3.googleusercontent.com/proxy/vhYftDaMkVl6bQwuR83U39kJYx5tG1Ha92Xi2Kc1MVrcdx_OyOPBiA0QLmMkGrL92bQYKnqeq-B2QKG-UZ6zBvOBHPq2-nGRrDqhAe5CZ6mEqw0KOJWsTfIrSDGEwqWgZelEKGylKAo98RKMQPu3' height='64px'>"
                            : requestScope.profileOwner.id == sessionScope.user.id
                                ? "<input type='button' value='Verificar perfil' onclick='moveToVerifyPage()'>"
                                : ""}
                </div>
            </div>
        </div>
    </body>
</html>
