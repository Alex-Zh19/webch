<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 28.07.2021
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="currentRole" value="${sessionScope.CURRENT_USER_ROLE}"></c:set>
<c:set var="guest" value="${guest}"></c:set>
<c:set var="product" value="${CURRENT_ENTITY_TO_DISPLAY}" scope="request"></c:set>
<c:if test="${currentRole==guest}">
    <jsp:include page="${GUEST_NAVIGATION_BAR}"></jsp:include>
</c:if>
<c:if test="${currentRole!=guest}">
    <jsp:include page="${USER_NAVIGATION_BAR}"></jsp:include>
</c:if>
<jsp:include page="${FORM_PRODUCT_VIEW}"></jsp:include>
</body>
</html>
