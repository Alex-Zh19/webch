<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 29.07.2021
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: #61ADB0">
<jsp:include page="${USER_NAVIGATION_BAR_FROM_ROOT}"></jsp:include>
<c:forEach var="en" items="${ENTITY_LIST}">
    <c:set var="entity" value="${en}" scope="request"></c:set>
    <jsp:include page="${FORM_ENTITY_ADMIN_LIST}"></jsp:include>
</c:forEach>
</body>
</html>

