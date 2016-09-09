<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title>My Restaurant|Menus</title>
</head>
<body>

<h2>Our menus</h2>
<c:forEach var="menu" items="${menus}">
    <h3>Menu ${menu.name}</h3>
    <table border="1">
        <tr>
            <th> ID </th>
            <th> Dish name </th>
            <th> Weight </th>
            <th> Cost </th>
        </tr>
        <c:forEach var="dish" items="${menu.dishes}">
            <tr>
                <td> ${dish.id} </td>
                <td> <a href="${path}/dish/${dish.name}"> ${dish.name} </a> </td>
                <td> ${dish.weight}</td>
                <td> ${dish.cost} </td>
            </tr>
        </c:forEach>
    </table>
    <br>
</c:forEach>

<br>
<form action="${path}/searchDish" method="GET">
    <h4>Input dish's name to search:</h4>
    <input type="text" name="dishName">
    <input type="submit" value="Search" >
</form>


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