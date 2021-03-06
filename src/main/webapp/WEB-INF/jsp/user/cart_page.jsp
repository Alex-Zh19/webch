<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 26.07.2021
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${TEXT[TITLE_USER_CART_PAGE]}</title>
    <link rel="stylesheet" href="${CSS_SETTINGS_FORM}">
</head>
<body style="background-color: #61ADB0">
<jsp:include page="${USER_NAVIGATION_BAR_FROM_ROOT}"></jsp:include>
<c:forEach var="pr" items="${sessionScope.USER_CART}">
    <c:set var="product" value="${pr}" scope="request"></c:set>
    <jsp:include page="${FORM_CART_PRODUCT_FROM_ROOT}"></jsp:include>
</c:forEach>
<form method="post" class="submit-button-form">
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_ORDERING_PAGE}" >
        ${TEXT[CHECKOUT_ORDER_BUTTON_TEXT]}</button>
</form>
</body>
</html>
