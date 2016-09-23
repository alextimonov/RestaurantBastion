<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title>Bastion|Service|Dishes</title>
</head>
<body>
<h2>Service page for Dishes</h2>
<table style="align-items: center" border="1">
    <c:url var="addUrl" value="${path}/dish/add" />
    <tr>
        <th> ID </th>
        <th> Dish name </th>
        <th> Description </th>
        <th> Cost </th>
        <th> Weight </th>
    </tr>
    <c:forEach var="dish" items="${dishes}">
        <c:url var="editUrl" value="${path}/dish/editById?id=${dish.id}" />
        <c:url var="deleteUrl" value="${path}/dish/deleteById?id=${dish.id}" />
        <tr>
            <td> ${dish.id} </td>
            <td> <a href="${path}/dish/${dish.name}"> ${dish.name} </a> </td>
            <td> ${dish.description} </td>
            <td> ${dish.cost} </td>
            <td> ${dish.weight} </td>
            <td><a href="${editUrl}">Edit</a></td>
            <td><a href="${deleteUrl}">Delete</a></td>
            <td><a href="${addUrl}">Add</a></td>
        </tr>
    </c:forEach>

</table>
<br>

<%--<form:form action="${path}/dish/editByName?name=${dishAttribute.name}" method="GET" modelAttribute="dishAttribute">--%>
    <%--<tr>--%>
        <%--<td>EDIT. Input dish's name:</td>--%>
        <%--<td><form:label path="Name"> Dish name:</form:label></td>--%>
        <%--<td><form:input path="name" type="text"/></td>--%>
    <%--</tr>--%>
<%--</form:form>--%>

<form action="${path}/dish/editByName?name=${dishAttribute.name}" method="GET">
    EDIT. Input dish's name:
    <input type="text" name="name">
    <input type="submit" value="Edit by name" >
</form>

<form action="${path}/dish/editById?id=${dishAttribute.id}" method="GET">
    EDIT. Input dish's ID:
    <input type="number" name="id">
    <input type="submit" value="Edit by id" >
</form>
<form action="${path}/dish/add" method="GET">
    ADD
    <input type="submit" value="Add new dish" >
</form>

<form action="${path}/dish/deleteByName?name=${dishAttribute.name}" method="GET">
    DELETE. Input dish's name:
    <input type="text" name="name">
    <input type="submit" value="Delete by name" >
</form>
<form action="${path}/dish/deleteById?id=${dishAttribute.id}" method="GET">
    DELETE. Input dish's ID:
    <input type="number" name="id">
    <input type="submit" value="Delete by id" >
</form>

<br>
<a href="${path}/service">Service page</a> |
<a href="${path}/service/employees">Staff</a> |
<a href="${path}/service/menus">Menus</a> |
<a href="${path}/service/dishes">Dishes</a> |
<a href="${path}/service/storage">Storage</a> |
<a href="${path}/service/orders">Orders history</a>

</body>
</html>
