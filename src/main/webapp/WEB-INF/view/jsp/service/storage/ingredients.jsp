<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathStorage" value="${pageContext.request.contextPath}/Restaurant/service/storage"/>
<c:set var="pathService" value="${pageContext.request.contextPath}/Restaurant/service"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Storage</title>
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
            <li><a href="${pathService}/dish/dishes">Dishes</a></li>
            <li class="active"><a href="${pathService}/storage/ingredients">Storage</a></li>
            <li><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h2>Storage<small> Service page</small></h2>

    <table class="table table-striped">
        <c:url var="addUrl" value="${pathStorage}/add" />
        <tr>
            <th>ID</th>
            <th>Ingredient name</th>
            <th>Amount</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>Add ingredient</th>
        </tr>
        <c:forEach var="item" items="${ingredients}">
            <c:url var="editUrl" value="${pathStorage}/edit?id=${item.id}" />
            <c:url var="deleteUrl" value="${pathStorage}/delete?id=${item.id}" />
            <tr>
                <td> ${item.id} </td>
                <td> ${item.name} </td>
                <td> ${item.amount} </td>
                <td><a href="${editUrl}">Edit</a></td>
                <td><a href="${deleteUrl}">Delete</a></td>
                <td><a href="${addUrl}">Add</a></td>
            </tr>
        </c:forEach>

    </table>

    <form class="form-horizontal" action="${pathStorage}/add" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Add new ingredient</label>
            </div>
            <div class="col-sm-4"></div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-plus-sign"></span> Add new ingredient</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathStorage}/filter" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Filter by first chars</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" ype="text" name="name">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-font"></span> Filter</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathStorage}/ingredients" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Show all ingredients</label>
            </div>
            <div class="col-sm-4"></div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-repeat"></span> Show all</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathStorage}/edit" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Edit. Input ingredient's ID:</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="number" name="id">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-edit"></span> Edit by id</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathStorage}/editByName" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Edit. Input ingredient's name:</label>
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

    <form class="form-horizontal" action="${pathStorage}/delete" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Delete. Input ingrdient's ID:</label>
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

    <form class="form-horizontal" action="${pathStorage}/deleteByName" method="GET">
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
            <li><a href="${pathService}/dish/dishes">Dishes</a></li>
            <li class="active"><a href="${pathService}/storage/ingredients">Storage</a></li>
            <li><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

</body>
</html>
