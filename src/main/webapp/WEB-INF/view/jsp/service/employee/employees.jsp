<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathEmployee" value="${pageContext.request.contextPath}/Restaurant/service/employee"/>
<c:set var="pathService" value="${pageContext.request.contextPath}/Restaurant/service"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Employees</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pathService}/../main">Restaurant Bastion</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pathService}/service">Service page</a></li>
            <li class="active"><a href="${pathService}/employee/employees">Personal</a></li>
            <li><a href="${pathService}/menu/menus">Menus</a></li>
            <li><a href="${pathService}/dish/dishes">Dishes</a></li>
            <li><a href="${pathService}/storage/ingredients">Storage</a></li>
            <li><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h2>Our personal<small> Service page</small></h2>

    <table class="table table-striped">
        <c:url var="addUrl" value="${pathEmployee}/add"/>
        <tr>
            <th>ID</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Birthday</th>
            <th>Position</th>
            <th>Salary</th>
            <th>Edit</th>
            <th>Add employee</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <c:url var="editUrl" value="${pathEmployee}/edit?id=${employee.id}"/>
            <tr>
                <td> ${employee.id} </td>
                <td> ${employee.name} </td>
                <td> ${employee.surname} </td>
                <td> <fmt:formatDate pattern="dd-MM-yyyy" value="${employee.birthday}"/> </td>
                <td> ${employee.job.position} </td>
                <td> ${employee.salary} </td>
                <td><a href="${editUrl}">Edit</a></td>
                <td><a href="${addUrl}">Add</a></td>
            </tr>
        </c:forEach>
    </table>

    <form class="form-horizontal" action="${pathEmployee}/add" method="GET">
        <div class="form-group">
            <div class="col-sm-4">
                <label class="control-label">Add new employee:</label>
            </div>
            <div class="col-sm-6"></div>
            <div class="col-sm-2">
                <button class="btn btn-primary" type="submit">Add new employee</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathEmployee}/edit" method="GET">
        <div class="form-group">
            <div class="col-sm-4">
                <label class="control-label">Edit. Input employee's ID:</label>
            </div>
            <div class="col-sm-6">
                <input class="form-control" type="number" name="id" title="id">
            </div>
            <div class="col-sm-2">
                <button class="btn btn-primary" type="submit">Edit by id</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathEmployee}/editByName" method="GET">
        <div class="form-group">
            <div class="col-sm-4">
                <label class="control-label">Edit. Input employee's name, surname:</label>
            </div>
            <div class="col-sm-3">
                <input class="form-control" type="text" name="name" title="name">
            </div>
            <div class="col-sm-3">
                <input class="form-control" type="text" name="surname" title="surname">
            </div>
            <div class="col-sm-2">
                <button class="btn btn-primary" type="submit">Edit by name</button>
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
            <li class="active"><a href="${pathService}/employee/employees">Personal</a></li>
            <li><a href="${pathService}/menu/menus">Menus</a></li>
            <li><a href="${pathService}/dish/dishes">Dishes</a></li>
            <li><a href="${pathService}/storage/ingredients">Storage</a></li>
            <li><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

</body>
</html>
