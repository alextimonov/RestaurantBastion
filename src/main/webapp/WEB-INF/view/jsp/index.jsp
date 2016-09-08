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
<p>
<a href="${path}/main">Main page</a> |
<a href="${path}/employees">Show all employees</a> |
<a href="${path}/menus">Show all menus</a> |
<a href="${path}/dishes">Show all dishes</a> </p>

<p>REST queries</p>
<p> <a href="${path}/restMenus">Our menus</a> |
    <a href="${path}/restMenus/spring">Spring menu</a> | <a href="${path}/restMenus/summer">Summer menu</a> |
    <a href="${path}/restMenus/autumn">Autumn menu</a> | <a href="${path}/restMenus/winter">Winter menu</a> </p>
<p> <a href="${path}/restOrders">All orders</a> |
    <a href="${path}/restOrders/open">Open orders</a> | <a href="${path}/restOrders/closed">Closed orders</a> |
    <a href="${path}/restOrders/1">Order #1</a> | <a href="${path}/restOrders/2">Order #2</a> </p>
<p> <a href="${path}/restEmployees">All employees</a> |
    <a href="${path}/restEmployees/1">Employee #1</a> | <a href="${path}/restEmployees/2">Employee #2</a> </p>

</body>
</html>
