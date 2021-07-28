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
<c:set var="role" value="${guest}"></c:set>
<c:if test="${currentRole==role}">
    <jsp:include page="${GUEST_NAVIGATION_BAR_FROM_ROOT}"></jsp:include>
</c:if>
<c:if test="${currentRole!=role}">
    <jsp:include page="${NAVIGATION_BAR_FROM_ROOT}"></jsp:include>
</c:if>
<c:set var="product" value="${PRODUCT}" scope="request"></c:set>
<jsp:include page="${FORM_PRODUCT}"></jsp:include>
</body>
</html>
