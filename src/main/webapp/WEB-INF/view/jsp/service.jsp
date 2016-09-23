<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title>My Restaurant </title>
</head>
<body>
<h2>Restaurant Bastion. Service page</h2>
<h3>Current time: ${currentTime}</h3>
<br>
<img src="${pageContext.request.contextPath}/images/photo_inside.jpg" />
<br>
<a href="${path}/service">Service page</a> |
<a href="${path}/service/employees">Staff</a> |
<a href="${path}/service/menus">Menus</a> |
<a href="${path}/service/dishes">Dishes</a> |
<a href="${path}/service/storage">Storage</a> |
<a href="${path}/service/orders">Orders history</a>
</body>
</html>
