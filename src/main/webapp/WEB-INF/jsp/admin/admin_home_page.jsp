<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 28.07.2021
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${CSS_HOME_USER_PAGE}">
</head>
<body style="background-color: #61ADB0">
<jsp:include page="${USER_NAVIGATION_BAR}"></jsp:include>
<form class="userAbout" name="userAbout" method="post">
    <h2>${TEXT[WELCOME_TEXT]}, ${sessionScope.CURRENT_USER.name} ${sessionScope.CURRENT_USER.surname}!</h2>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_SETTINGS_PAGE}" value="${TEXT[SETTINGS_BUTTON]}"></button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${}" value="Employee list"></button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${}" value="orders List"></button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${}" value="Products List"></button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${}" value="Users list"></button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${}" value="find user"></button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${}" value="find order"></button>
</form>
</body>
</html>
