<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="utf-8">     
 </head>

<body id="body-pd" class="body-pd-left">
    <div class="r-navbar show-right" id="nav-bar">
        <nav class="nav">
            <div class="scrollNav"> <span class="nav_logo nav_logo-name">Meus fóruns</span>
              <div class="nav_list"> 
                <c:forEach var="forum" items="${sessionScope.userForuns}">
                  <a href="${pageContext.request.contextPath}/forum/especifico?id=${forum.id}" class="nav_link ${pageContext.request.servletPath == "/home.jsp" ? "active" : ""}"> 
                   <%@include file="_forumCard.jsp" %>
                  </a> 
                </c:forEach>
              </div>
            </div>  
        </nav>
    </div>
    <!--Container Main start-->
  </body>
</html>