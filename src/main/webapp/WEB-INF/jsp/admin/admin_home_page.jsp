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
    <style >
        input[type=search]{
            width: 300px;
        }
    </style>
    <title>Title</title>
    <link rel="stylesheet" href="${CSS_ADMIN_HOME_PAGE}">
</head>
<body style="background-color: #61ADB0">
<jsp:include page="${USER_NAVIGATION_BAR_FROM_ROOT}"></jsp:include>
<form class="userAbout" name="userAbout" method="post">
    <h2>${TEXT[WELCOME_TEXT]}, ${sessionScope.CURRENT_USER.name} ${sessionScope.CURRENT_USER.surname}!</h2>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_SETTINGS_PAGE}" >${TEXT[SETTINGS_BUTTON]}</button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_EMPLOYEE_LIST}" >${TEXT[EMPLOYEE_LIST_BUTTON_TEXT]}</button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_ORDERS_LIST}" >${TEXT[ORDERS_LIST_BUTTON_TEXT]}</button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_PRODUCTS_LIST}">${TEXT[PRODUCTS_LIST_BUTTON_TEXT]}</button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_USERS_LIST}">${TEXT[USERS_LIST_BUTTON_TEXT]}</button>
    <form class="input-group">
        <div class="form-outline">
            <input type="search" id="form1" class="form-control" name="${USER_EMAIL_TO_FIND}" value="${TEXT[SEARCH_BUTTON_TEXT]}" />
        </div>
        <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${FIND_USER_BY_EMAIL}"
                class="btn btn-primary">${TEXT[SEARCH_BUTTON_TEXT]}
        </button>
    </form>
    <!--<button type="submit" formaction="" value="find user"></button>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=" value="find order"></button>-->
</form>
</body>
</html>
