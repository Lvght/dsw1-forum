<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="utf-8">     
 </head>

<body id="body-pd" class="body-pd-left">
    <div class="r-navbar show-right" id="nav-bar">
        <nav class="nav">
            <div class="scrollNav"> <a href="#" class="nav_logo"> <span class="nav_logo-name">Meus fóruns</span> </a>
              <div class="nav_list"> 
                <c:forEach var="forum" items="${sessionScope.userForuns}">
                  <a href="${pageContext.request.contextPath}/forum/especifico?id=${forum.id}" class="nav_link ${pageContext.request.servletPath == "/home.jsp" ? "active" : ""}"> 
                   <%@include file="_forumCard.jsp" %>
                  </a> 
                </c:forEach>  
        </nav>
    </div>
    <!--Container Main start-->
  </body>
</html>