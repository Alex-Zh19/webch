<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 01.08.2021
  Time: 18:55
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
<c:forEach var="ord" items="${CURRENT_ENTITY_TO_DISPLAY}">
  <c:set var="order" value="${ord}" scope="request"></c:set>
  <jsp:include page="${FORM_ORDER_USER_LIST}"></jsp:include>
</c:forEach>
</body>
</html>
