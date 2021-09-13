<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<option value="" selected>
    Geral
</option>
<c:forEach var="topico" items="${requestScope.topicos}">
    <option value="${topico.getId()}">
        ${topico.getNome()}
    </option>
</c:forEach>  