<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 28.07.2021
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="${CSS_SUCCESS_OPERATION_PAGE}">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: #61ADB0">
<form method="post">
    <label class="success-label">${TEXT[ERROR_MESSAGE_TEXT]}</label>
    <c:set var="currentRole" value="${sessionScope.CURRENT_USER_ROLE}"></c:set>
    <c:set var="roleGuest" value="${guest}"></c:set>
    <c:set var="roleUser" value="${user}"></c:set>
    <c:set var="roleEmployee" value="${employee}"></c:set>
    <c:set var="roleAdmin" value="${admin}"></c:set>

    <c:if test="${currentRole==roleGuest}">
        <button class="go-to-home-button" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_HOME_GUEST_PAGE}" value="${TEXT[PAGE_HOME]}"></button>
    </c:if>
    <c:if test="${currentRole==roleUser}">
        <button class="go-to-home-button" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_HOME_USER_PAGE}" value="${TEXT[PAGE_HOME]}"></button>
    </c:if>
    <c:if test="${currentRole==roleEmployee}">
        <button class="go-to-home-button" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_HOME_EMPLOYEE_PAGE}" value="${TEXT[PAGE_HOME]}"></button>
    </c:if>
    <c:if test="${currentRole==roleAdmin}">
        <button class="go-to-home-button" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_HOME_ADMIN_PAGE}" value="${TEXT[PAGE_HOME]}"></button>
    </c:if>
</form>
</body>
</html>
