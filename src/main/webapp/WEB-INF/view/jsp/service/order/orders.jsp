<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathOrder" value="${pageContext.request.contextPath}/Restaurant/service/order"/>
<c:set var="pathService" value="${pageContext.request.contextPath}/Restaurant/service"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Orders</title>
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
            <li><a href="${pathService}/storage/ingredients">Storage</a></li>
            <li class="active"><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h2>Orders<small> Service page</small></h2>

    <table class="table table-striped">
        <tr>
            <th> ID </th>
            <th> Waiter </th>
            <th> Table </th>
            <th> Date </th>
            <th> Is closed </th>
            <th> Dishes </th>
        </tr>
        <c:forEach var="item" items="${orders}">
            <c:set var="nDishes" value="${item.getDishes().size()}"/>
            <tr>
                <td>${item.id}</td>
                <td>${item.waiter.name}</td>
                <td>${item.tableNumber}</td>
                <td><fmt:formatDate pattern="dd-MM-yyyy" value="${item.date}"/></td>
                <td>${item.closed}</td>
                <td> <a href="${pathOrder}/dishes?orderId=${item.id}">${nDishes} dishes</a></td>
            </tr>
        </c:forEach>

    </table>

    <form class="form-horizontal" action="${pathOrder}/filterByTableNumber" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Filter by table number</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="number" name="tableNumber">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-sort-by-order-alt"></span> Filter by table number</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathOrder}/filterByWaiter" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Filter by waiter's name</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="text" name="waiterName">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-font"></span> Filter by waiter's name</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathOrder}/filterByDate" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Filter by date</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="date" name="date">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-equalizer"></span> Filter by date</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathOrder}/orders" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Show all orders</label>
            </div>
            <div class="col-sm-4"></div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-repeat"></span> Show all orders</button>
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
            <li><a href="${pathService}/storage/ingredients">Storage</a></li>
            <li class="active"><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

</body>
</html>