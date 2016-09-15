<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title>Bastion|Service|Menus</title>
</head>
<body>

<h2>Service page for Menus</h2>

<table style="align-items: center" border="1" width="400">
    <c:url var="addUrl" value="${path}/menu/add" />
    <tr>
        <th> ID </th>
        <th> Menu name </th>
    </tr>

    <c:forEach var="menu" items="${menus}">
        <c:url var="editUrl" value="${path}/menu/editById?id=${menu.id}" />
        <c:url var="deleteUrl" value="${path}/menu/deleteById?id=${menu.id}" />
    <tr>
        <td> ${menu.id} </td>
        <td> <a href="${path}/menu/${menu.name}"> ${menu.name} </a> </td>
        <td><a href="${editUrl}">Edit</a></td>
        <td><a href="${deleteUrl}">Delete</a></td>
        <td><a href="${addUrl}">Add</a></td>
    </tr>
    </c:forEach>
</table>
<br>
<form action="${path}/menu/editByName?name=${menuAttribute.name}" method="GET">
    EDIT. Input menu's name:
    <input type="text" name="name">
    <input type="submit" value="Edit by name" >
</form>

<form action="${path}/menu/editById?id=${menuAttribute.id}" method="GET">
    EDIT. Input menu's ID:
    <input type="number" name="id">
    <input type="submit" value="Edit by id" >
</form>
<form action="${path}/menu/add" method="GET">
    ADD
    <input type="submit" value="Add new menu" >
</form>

<form action="${path}/menu/deleteByName?name=${menuAttribute.name}" method="GET">
    DELETE. Input menu's name:
    <input type="text" name="name">
    <input type="submit" value="Delete by name" >
</form>
<form action="${path}/menu/deleteById?id=${menuAttribute.id}" method="GET">
    DELETE. Input menu's ID:
    <input type="number" name="id">
    <input type="submit" value="Delete by id" >
</form>

<c:forEach var="menu" items="${menus}">
    <h4>Menu ${menu.name} (id=${menu.id})</h4>
    <table style="align-items: center" border="1" width="400">
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
<br>
<a href="${path}/service">Service page</a> |
<a href="${path}/service/employees">Staff</a> |
<a href="${path}/service/menus">Menus</a> |
<a href="${path}/service/dishes">Dishes</a> |
<a href="${path}/service/storage">Storage</a> |
<a href="${path}/service/orders">Orders history</a>


</body>
</html>