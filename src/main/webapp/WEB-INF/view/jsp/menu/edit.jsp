<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>
<html>
<head>
    <title>Bastion|Service|Edit menu</title>
</head>
<body>

<h2>Edit existing menu</h2>
<c:url var="saveUrl" value="${path}/menu/edit?id=${menuExisting.id}"/>
<form:form modelAttribute="menuExisting" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="id">ID:</form:label></td>
            <td><form:input path="id" disabled="true"/></td>
        </tr>

        <tr>
            <td><form:label path="name">Dish name:</form:label></td>
            <td><form:input path="Name"/></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>

<h4>Edit dishes in menu</h4>
<table style="align-items: center" border="1">
    <tr>
        <th> ID </th>
        <th> Dish name </th>
        <th> Description </th>
        <th> Cost </th>
        <th> Weight </th>
    </tr>

    <c:forEach var="dish" items="${menuExisting.dishes}">
        <c:url var="deleteUrl" value="${path}/menu/deleteDish?id=${dish.id}" />
        <c:url var="editUrl" value="${path}/dish/editById?id=${dish.id}" />
        <tr>
            <td> ${dish.id} </td>
            <td> <a href="${path}/dish/${dish.name}"> ${dish.name} </a> </td>
            <td> ${dish.description} </td>
            <td> ${dish.cost} </td>
            <td> ${dish.weight} </td>
            <td><a href="${deleteUrl}">Delete dish from menu</a></td>
            <td><a href="${editUrl}">Edit dish for all menus</a></td>
        </tr>
    </c:forEach>

</table>

<br>
<form action="${path}/menu/addDish/${id}" method="POST">
    ADD DISH. Input dish's ID:
    <input type="number" name="id">
    <input type="submit" value="Add dish to menu" >
</form>

<form action="${path}/menu/addDishByName?name=${dishAttribute.name}" method="POST">
    ADD DISH. Input dish's name:
    <input type="text" name="name">
    <input type="submit" value="Add dish to menu" >
</form>

<form action="${path}/menu/deleteDish?id=${dishAttribute.id}" method="GET">
    DELETE. Input dish's ID:
    <input type="number" name="id">
    <input type="submit" value="Delete dish by id" >
</form>

<form action="${path}/menu/deleteDishByName?name=${dishAttribute.name}" method="GET">
    DELETE. Input dish's name:
    <input type="text" name="name">
    <input type="submit" value="Delete by name" >
</form>

</body>
</html>
