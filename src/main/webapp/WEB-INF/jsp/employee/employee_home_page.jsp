<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 01.08.2021
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${CSS_ADMIN_HOME_PAGE}">
</head>
<body style="background-color: #61ADB0">
<jsp:include page="${USER_NAVIGATION_BAR_FROM_ROOT}"></jsp:include>
<form class="userAbout" name="userAbout" method="post">
    <h2>${TEXT[WELCOME_TEXT]}, ${sessionScope.CURRENT_USER.name} ${sessionScope.CURRENT_USER.surname}!</h2>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_SETTINGS_PAGE}" >${TEXT[SETTINGS_BUTTON]}</button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_ORDERS_LIST}" >${TEXT[ORDERS_LIST_BUTTON_TEXT]}</button>
    <!--<button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=" value="find user"></button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=" value="find order"></button>-->
</form>
</body>
</html>

