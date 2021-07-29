<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 29.07.2021
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="entity" value="${USER_TO_CHANGE}" scope="request"></c:set>
<jsp:include page="${FORM_ENTITY_SETTINGS_PAGE}"></jsp:include>
</body>
</html>
