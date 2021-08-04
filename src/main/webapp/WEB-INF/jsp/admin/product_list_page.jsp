<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 22.07.2021
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${TEXT[TITLE_PRODUCT_LIST_PAGE]}</title>

</head>
<body style="background-color: #61ADB0">
<jsp:include page="${USER_NAVIGATION_BAR_FROM_ROOT}"></jsp:include>
<c:forEach var="pr" items="${CURRENT_ENTITY_TO_DISPLAY}">
    <c:set var="product" value="${pr}" scope="request"></c:set>
    <jsp:include page="${FORM_PRODUCT_ADMIN_LIST}"></jsp:include>
</c:forEach>
</body>
</html>
