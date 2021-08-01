<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 21.07.2021
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${CSS_HOME_USER_PAGE}">
</head>
<body style="background-color: #61ADB0">
<jsp:include page="${USER_NAVIGATION_BAR_FROM_ROOT}"></jsp:include>
<form class="userAbout" name="userAbout" method="post">
    <h2>${TEXT[WELCOME_TEXT]}, ${sessionScope.CURRENT_USER.name} ${sessionScope.CURRENT_USER.surname}!</h2>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_SETTINGS_PAGE}">
        ${TEXT[SETTINGS_BUTTON]}</button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${FIND_ORDER_BY_CREATOR}">
        ${TEXT[ORDERS_LIST_BUTTON_TEXT]}</button>
</form>
</body>
</html>
