<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="br">
    <head>
        <meta charset="utf-8">
        <style>
            .pagination {
                width: fit-content;
                margin: 15px auto;
            }

            .page-link {
                color: #033142fa;
            }

            .page-item.active .page-link {
                z-index: 3;
                color: #fff;
                background-color: #033142fa;
                border-color: #033142fa;
            }

            .numbers{
                display: flex;
            }

            @media only screen and (max-width: 700px){
                .numbers{
                    display: none;
                }
            } 
        </style>

    </head>

    <body>
        <c:if test="${Math.floor(Math.abs(requestScope.itemCount - 1) / 10 + 1) > 1}" >
            <ul class="pagination">
                <li class="page-item ${requestScope.page < 2 ? "disabled" : ""}"><a class="page-link" href="<%= (request.getAttribute("javax.servlet.forward.request_uri") + "?" + (request.getQueryString() != null ? request.getQueryString() + "&" : "")).replace("&page=" + request.getAttribute("page"), "").replace("page=" + request.getAttribute("page"), "") + "page="%>${requestScope.page - 1}">Anterior</a></li>
                <li class="page-item ${requestScope.page >= Math.floor(Math.abs(requestScope.itemCount - 1) / 10 + 1) ? "disabled" : ""}"><a class="page-link" href="<%= (request.getAttribute("javax.servlet.forward.request_uri") + "?" + (request.getQueryString() != null ? request.getQueryString() + "&" : "")).replace("&page=" + request.getAttribute("page"), "").replace("page=" + request.getAttribute("page"), "") + "page="%>${requestScope.page + 1}">Próximo</a></li>
            </ul>
        </c:if>
    </body>
</html>