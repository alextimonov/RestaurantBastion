<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathDish" value="${pageContext.request.contextPath}/Restaurant/service/dish"/>
<c:set var="pathService" value="${pageContext.request.contextPath}/Restaurant/service"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Dishes</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pathService}/../main">Restaurant Bastion</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pathService}/service">Service page</a></li>
            <li><a href="${pathService}/employee/employees">Personal</a></li>
            <li><a href="${pathService}/menu/menus">Menus</a></li>
            <li class="active"><a href="${pathService}/dish/dishes">Dishes</a></li>
            <li><a href="${pathService}/storage/ingredients">Storage</a></li>
            <li><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h2>Dishes<small> Service page</small></h2>

    <table class="table table-striped">
        <c:url var="addUrl" value="${pathDish}/add" />
        <tr>
            <th>ID</th>
            <th>Dish name</th>
            <th>Description</th>
            <th>View</th>
            <th>Cost</th>
            <th>Weight</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>Add dish</th>
        </tr>
        <c:forEach var="dish" items="${dishes}">
            <c:url var="editUrl" value="${pathDish}/edit?id=${dish.id}" />
            <c:url var="deleteUrl" value="${pathDish}/delete?id=${dish.id}" />
            <tr>
                <td>${dish.id}</td>
                <td>${dish.name}</td>
                <td>${dish.description}</td>
                <td><img src="${pageContext.request.contextPath}/images/dish_${dish.id}.jpg"/></td>
                <td>${dish.cost}</td>
                <td>${dish.weight}</td>
                <td><a href="${editUrl}">Edit</a></td>
                <td><a href="${deleteUrl}">Delete</a></td>
                <td><a href="${addUrl}">Add</a></td>
            </tr>
        </c:forEach>
    </table>

    <form class="form-horizontal" action="${pathDish}/add" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Add new dish</label>
            </div>
            <div class="col-sm-4"></div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-plus-sign"></span> Add new dish</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathDish}/edit" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Edit. Input dish's ID:</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="number" name="id" title="id">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-edit"></span> Edit by id</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathDish}/editByName" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Edit. Input dish's name:</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="text" name="name" title="name">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-edit"></span> Edit by name</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathDish}/delete" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Delete. Input dish's ID:</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="number" name="id" title="id">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-trash"></span> Delete by id</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathDish}/deleteByName" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Delete. Input dish's name:</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="text" name="name" title="name">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-trash"></span> Delete by name</button>
            </div>
        </div>
    </form>
</div>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pathService}/../main">Restaurant Bastion</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pathService}/service">Service page</a></li>
            <li><a href="${pathService}/employee/employees">Personal</a></li>
            <li><a href="${pathService}/menu/menus">Menus</a></li>
            <li class="active"><a href="${pathService}/dish/dishes">Dishes</a></li>
            <li><a href="${pathService}/storage/ingredients">Storage</a></li>
            <li><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

</body>
</html>
