<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Dish not found</title>
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
    <div class="form-group">
        <div class="alert alert-danger">
            <h3><strong>HTTP Status 404</strong></h3>
            <h4>The requested resource is not available</h4>
        </div>
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <img src="${pageContext.request.contextPath}/images/oops.png" />
        </div>
        <div class="col-sm-3"></div>
    </div>
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
