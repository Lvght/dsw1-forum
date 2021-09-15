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
    <p>${comment.author.name}</p>
    <p>${comment.content}</p>
</c:forEach>
