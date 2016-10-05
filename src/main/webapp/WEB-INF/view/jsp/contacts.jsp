<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title>My Restaurant|Contacts</title>
</head>
<body>
<h2>Restaurant Bastion</h2>
<h3>We make your free time unforgettable</h3>
<h4>Kyiv, Blvd. Buchmy, 10</h4>
<h4>Tel. 123-45-67, E-mail:bastion@bastion.kiev.ua</h4>
<h3>Location map</h3>
<img src="${pageContext.request.contextPath}/images/location_map.jpg" />
<br>
<a href="${path}/main">Main page</a> |
<a href="${path}/menus">Our menus</a> |
<a href="${path}/findDishByName">Find your favorite dish</a> |
<a href="${path}/scheme">Restaurant's scheme</a> |
<a href="${path}/contacts">Contacts</a> |
<a href="${path}/waiters">Our Waiters</a> |
<a href="${path}/employees">Our personal</a>
<br>
</body>
</html>