<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathMenu" value="${pageContext.request.contextPath}/Restaurant/service/menu"/>
<c:set var="pathService" value="${pageContext.request.contextPath}/Restaurant/service"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Menus</title>
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
            <li class="active"><a href="${pathService}/menu/menus">Menus</a></li>
            <li><a href="${pathService}/dish/dishes">Dishes</a></li>
            <li><a href="${pathService}/storage/ingredients">Storage</a></li>
            <li><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h2>Menus<small> Service page</small></h2>

    <table class="table table-striped">
        <c:url var="addUrl" value="${pathMenu}/add" />
        <tr>
            <th>ID</th>
            <th>Menu name</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>Add menu</th>
        </tr>

        <c:forEach var="menu" items="${menus}">
            <c:url var="editUrl" value="${pathMenu}/edit?id=${menu.id}" />
                <c:url var="deleteUrl" value="${pathMenu}/delete?id=${menu.id}" />
            <tr>
                <td>${menu.id}</td>
                <td>${menu.name}</td>
                <td><a href="${editUrl}">Edit</a></td>
                <td><a href="${deleteUrl}">Delete</a></td>
                <td><a href="${addUrl}">Add</a></td>
            </tr>
        </c:forEach>
    </table>

    <form class="form-horizontal" action="${pathMenu}/add" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Add new menu</label>
            </div>
            <div class="col-sm-4"></div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-plus-sign"></span> Add new menu</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathMenu}/edit" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Edit. Input menu's ID:</label>
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

    <form class="form-horizontal" action="${pathMenu}/editByName" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Edit. Input menu's name:</label>
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

    <form class="form-horizontal" action="${pathMenu}/delete" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Delete. Input menu's ID:</label>
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

    <form class="form-horizontal" action="${pathMenu}/deleteByName" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Delete. Input menu's name:</label>
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

    <c:forEach var="menu" items="${menus}">
        <h3>Menu ${menu.name} (id=${menu.id})</h3>
        <table class="table table-striped">
            <tr>
                <th> ID </th>
                <th> Dish name </th>
                <th> Weight </th>
                <th> Cost </th>
            </tr>
            <c:forEach var="dish" items="${menu.dishes}">
                <tr>
                    <td> ${dish.id} </td>
                    <td> <a href="${pathService}/dish/editByName?name=${dish.name}"> ${dish.name} </a> </td>
                    <td> ${dish.weight}</td>
                    <td> ${dish.cost} </td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>
</div>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pathService}/../main">Restaurant Bastion</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pathService}/service">Service page</a></li>
            <li><a href="${pathService}/employee/employees">Personal</a></li>
            <li class="active"><a href="${pathService}/menu/menus">Menus</a></li>
            <li><a href="${pathService}/dish/dishes">Dishes</a></li>
            <li><a href="${pathService}/storage/ingredients">Storage</a></li>
            <li><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

</body>
</html>