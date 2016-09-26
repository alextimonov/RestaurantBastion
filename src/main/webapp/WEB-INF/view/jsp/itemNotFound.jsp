<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title>My Restaurant|Item is not found</title>
</head>

<body>

<br>
<h3>Sorry! Item with name "${itemName}" is not found!</h3>

<br>
<img src="${pageContext.request.contextPath}/images/oops.jpg" />
<br>
<h3>Try to search another item</h3>
<br>
<a href="${path}/main">Main page</a> |
<a href="${path}/menus">Our menus</a> |
<a href="${path}/findDishByName">Find your favorite dish</a> |
<a href="${path}/scheme">Restaurant's scheme</a> |
<a href="${path}/contacts">Contacts</a> |
<a href="${path}/waiters">Our Waiters</a> |
<a href="${path}/employees">Our personal</a>

</body>
</html>