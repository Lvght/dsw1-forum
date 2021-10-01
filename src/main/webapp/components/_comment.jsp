<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lvght
  Date: 15/09/2021
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach items="${requestScope.comments}" var="comment">
    <div class="container-fluid"
         style="border-bottom: 1px solid rgba(0,0,0,0.2); margin-bottom: 16px; margin-top: 16px">
        <div class="row align-items-center">
            <div class="col-auto p-0">
                <c:choose>
                    <c:when test="${comment.author != null && comment.author.profileImageUrl != null}">
                        <img alt="Imagem de perfil"
                             style="height: 40px; width: 40px;"
                             class="rounded-circle"
                             src="${comment.author.profileImageUrl}"/>

                    </c:when>
                    <c:otherwise>
                        <img alt="Imagem de perfil"
                             style="height: 40px; width: 40px;"
                             class="rounded-circle"
                             src="${pageContext.request.contextPath}/resources/default-profile.jpeg"/>

                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-auto">
                ${comment.author.name}
            </div>
            <div class="col-auto">
                @${comment.author.username}
            </div>
            <div class="col text-end">
                há 15 minutos
            </div>
        </div>

        <div class="row mt-3 mb-3">
            ${comment.content}
        </div>

    </div>
</c:forEach>
