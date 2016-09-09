<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title>My Restaurant|Dish</title>
</head>
<table style="align-items: center" border="1">
    <thead>Dish. Detail info</thead>
    <tr>
        <th> ID </th>
        <th> Dish name </th>
        <th> Description </th>
        <th> Weight </th>
        <th> Cost </th>
    </tr>
    <tr>
        <td> ${dish.id} </td>
        <td> ${dish.name} </td>
        <td> ${dish.description} </td>
        <td> ${dish.weight} </td>
        <td> ${dish.cost} </td>
    </tr>
</table>
<br>
<br>

<table style="align-items: center" border="1">
    <thead>Dish's ingredients</thead>
    <tr>
        <th> ID </th>
        <th> Ingredient name </th>
    </tr>
    <c:forEach var="ingredient" items="${ingredients}">
        <tr>
            <td> ${ingredient.id} </td>
            <td> ${ingredient.name} </td>
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