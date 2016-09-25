<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Menus</title>
</head>
<body background="${pageContext.request.contextPath}/images/back.jpg">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${path}/main">Restaurant Bastion</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${path}/main">Home</a></li>
            <li class="active"><a href="${path}/menus">Menus</a></li>
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
    <h2>Enjoy our menus<small> Click on menu or dish names for details</small></h2>
    <c:forEach var="menu" items="${menus}">
        <h3><a href="${path}/menu/${menu.name}">Menu ${menu.name}</a></h3>
        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Dish name</th>
                <th>Weight</th>
                <th>View</th>
                <th>Cost</th>
            </tr>
            <c:forEach var="dish" items="${menu.dishes}">
                <tr>
                    <td>${dish.id}</td>
                    <td><a href="${path}/dish/${dish.name}">${dish.name}</a></td>
                    <td><img src="${pageContext.request.contextPath}/images/dish_${dish.id}.jpg"/></td>
                    <td>${dish.weight}</td>
                    <td>${dish.cost}</td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>

    <form class="form-horizontal" action="${path}/searchDish" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Find your favorite dish:</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="text" name="dishName" title="input dish name">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">Search!</button>
            </div>
        </div>
    </form>
</div>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${path}/main">Restaurant Bastion</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${path}/main">Home</a></li>
            <li class="active"><a href="${path}/menus">Menus</a></li>
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