<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title>My Restaurant|Dishes</title>
</head>
<body>
<table style="align-items: center" border="1">
    <thead>All dishes</thead>
    <tr>
        <th>ID</th>
        <th>Dish name</th>
        <th>Cost</th>
    </tr>
    <c:forEach var="dish" items="${dishes}">
        <tr>
            <td>${dish.id}</td>
            <td><a href="${path}/dish/${dish.name}">${dish.name}</a></td>
            <td>${dish.cost}</td>
        </tr>
    </c:forEach>

</table>

<br>
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