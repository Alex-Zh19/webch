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
    <title>${TEXT[TITLE_SHOP_PAGE]}</title>

</head>
<body  style="background-color: #61ADB0">
<c:set var="currentRole" value="${sessionScope.CURRENT_USER_ROLE}"></c:set>
<c:set var="role" value="${guest}"></c:set>
<c:if test="${currentRole==role}">
    <jsp:include page="${GUEST_NAVIGATION_BAR}"></jsp:include>
</c:if>
<c:if test="${currentRole!=role}">
    <jsp:include page="${USER_NAVIGATION_BAR}"></jsp:include>
</c:if>
<c:forEach var="pr" items="${sessionScope.CURRENT_ENTITY_TO_DISPLAY}">
    <c:set var="product" value="${pr}" scope="request"></c:set>
    <jsp:include page="${FORM_PRODUCT}"></jsp:include>
</c:forEach>
</body>
</html>
