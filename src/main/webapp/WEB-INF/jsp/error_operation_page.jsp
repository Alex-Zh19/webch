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
    <style>
        button {
            background-color: #56baed;
            border: none;
            color: white;
            padding: 15px 80px;
            text-align: center;
            text-decoration: none;
            text-decoration-color: white;
            display: inline-block;
            text-transform: uppercase;
            font-size: 13px;
            -webkit-box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
            box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
            -webkit-border-radius: 5px 5px 5px 5px;
            border-radius: 5px 5px 5px 5px;
            margin: 5px 20px 40px 20px;
            -webkit-transition: all 0.3s ease-in-out;
            -moz-transition: all 0.3s ease-in-out;
            -ms-transition: all 0.3s ease-in-out;
            -o-transition: all 0.3s ease-in-out;
            transition: all 0.3s ease-in-out;
        }

        button:hover {
            background-color: #39ace7;
        }

        button:active {
            -moz-transform: scale(0.95);
            -webkit-transform: scale(0.95);
            -o-transform: scale(0.95);
            -ms-transform: scale(0.95);
            transform: scale(0.95);
        }
    </style>
</head>
<body style="background-color: #61ADB0">
<form method="post">
    <label class="success-label" style="text-decoration-color: white" >${TEXT[ERROR_MESSAGE_TEXT]}</label>
    <c:set var="currentRole" value="${sessionScope.CURRENT_USER_ROLE}"></c:set>
    <c:set var="roleGuest" value="${guest}"></c:set>
    <c:set var="roleUser" value="${user}"></c:set>
    <c:set var="roleEmployee" value="${employee}"></c:set>
    <c:set var="roleAdmin" value="${admin}"></c:set>

    <button class="go-to-home-button" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_HOME_PAGE}" value="${TEXT[PAGE_HOME]}"></button>

</form>
</body>
</html>
