<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Dish details</title>
</head>
<body background="${pageContext.request.contextPath}/images/back.jpg">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${path}/main">Restaurant Bastion</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${path}/main">Home</a></li>
            <li><a href="${path}/menus">Menus</a></li>
            <li><a href="${path}/dishes">Dishes</a></li>
            <li><a href="${path}/scheme">Scheme</a></li>
            <li><a href="${path}/contacts">Contacts</a></li>
            <li><a href="${path}/waiters">Waiters</a></li>
            <li><a href="${path}/employees">Personal</a></li>
            <li><a href="${path}/service/service">Service pages</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <table class="table table-striped">
        <h2>Dish ${dish.name}<small>Detail info</small></h2>
        <tr>
            <th>ID</th>
            <th>Dish name</th>
            <th>Description</th>
            <th>View</th>
            <th>Weight</th>
            <th>Cost</th>
        </tr>
        <tr>
            <td>${dish.id}</td>
            <td>${dish.name}</td>
            <td>${dish.description}</td>
            <td><img src="${pageContext.request.contextPath}/images/dish_${dish.id}.jpg"/></td>
            <td>${dish.weight}</td>
            <td>${dish.cost}</td>
        </tr>
    </table>
    <hr>
    <table class="table table-striped">
        <h3>Dish's ingredients</h3>
        <tr>
            <th>ID</th>
            <th>Ingredient name</th>
            <th>Weight for dish</th>
        </tr>
        <c:forEach var="item" items="${itemsInDish}">
            <tr>
                <td>${item.ingredient.id}</td>
                <td>${item.ingredient.name}</td>
                <td>${item.ingredientWeight}</td>
            </tr>
        </c:forEach>

    </table>
</div>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${path}/main">Restaurant Bastion</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${path}/main">Home</a></li>
            <li><a href="${path}/menus">Menus</a></li>
            <li><a href="${path}/dishes">Dishes</a></li>
            <li><a href="${path}/scheme">Scheme</a></li>
            <li><a href="${path}/contacts">Contacts</a></li>
            <li><a href="${path}/waiters">Waiters</a></li>
            <li><a href="${path}/employees">Personal</a></li>
            <li><a href="${path}/service/service">Service pages</a></li>
        </ul>
    </div>
</nav>

</body>
</html>