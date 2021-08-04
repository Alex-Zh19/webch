<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 27.07.2021
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${TEXT[TITLE_ORDERING_PAGE]}</title>
</head>
<body  style="background-color: #61ADB0">
<c:set var="currentRole" value="${sessionScope.CURRENT_USER_ROLE}"></c:set>
<c:set var="guest" value="${guest}"></c:set>
<c:if test="${currentRole==guest}">
    <jsp:include page="${GUEST_NAVIGATION_BAR}"></jsp:include>
</c:if>
<c:if test="${currentRole!=guest}">
    <jsp:include page="${USER_NAVIGATION_BAR}"></jsp:include>
</c:if>
<jsp:include page="${FORM_ORDER}"></jsp:include>
</body>
</html>
