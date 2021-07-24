<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 22.07.2021
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="pr" items="${PRODUCT_LIST}">
    <c:set var="product" value="${pr}" scope="request"></c:set>
   <jsp:include page="templates/form/product_form.jsp"></jsp:include>
</c:forEach>
</body>
</html>
