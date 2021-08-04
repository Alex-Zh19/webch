<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 29.07.2021
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${TEXT[TITLE_PRODUCT_SETTINGS_PAGE]}</title>
</head>
<body style="background-color: #61ADB0">
<jsp:include page="${USER_NAVIGATION_BAR_FROM_ROOT}"></jsp:include>
<c:set var="product" value="${CURRENT_ENTITY_TO_DISPLAY}" scope="request"></c:set>
<jsp:include page="${FORM_PRODUCT_SETTINGS_PAGE}"></jsp:include>
</body>
</html>
