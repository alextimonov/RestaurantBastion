<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathService" value="${pageContext.request.contextPath}/Restaurant/service"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Item is not found</title>
</head>

<body background="${pageContext.request.contextPath}/images/back.jpg">
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
            <li><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="form-group">
        <div class="alert alert-danger">
            <h4><strong>Error message! ${errorMessage}</strong></h4>
            <h5>${additionalMessage}</h5>
        </div>
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <img src="${pageContext.request.contextPath}/images/oops.jpg" />
        </div>
        <div class="col-sm-3"></div>
    </div>
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
            <li><a href="${pathService}/order/orders">Orders</a></li>
        </ul>
    </div>
</nav>

</body>
</html>
