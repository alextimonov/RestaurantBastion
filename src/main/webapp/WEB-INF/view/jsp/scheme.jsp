<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Scheme</title>
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
            <li class="active"><a href="${path}/scheme">Scheme</a></li>
            <li><a href="${path}/contacts">Contacts</a></li>
            <li><a href="${path}/waiters">Waiters</a></li>
            <li><a href="${path}/employees">Personal</a></li>
            <li><a href="${path}/service/service">Service pages</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h2>Restaurant Bastion <small>We make your free time unforgettable</small></h2>
    <h4>Kyiv, Blvd. Buchmy, 10. Tel. 123-45-67, E-mail:bastion@bastion.kiev.ua</h4>
    <h3>Restaurant scheme</h3>
    <img src="${pageContext.request.contextPath}/images/scheme.jpg" />
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
            <li class="active"><a href="${path}/scheme">Scheme</a></li>
            <li><a href="${path}/contacts">Contacts</a></li>
            <li><a href="${path}/waiters">Waiters</a></li>
            <li><a href="${path}/employees">Personal</a></li>
            <li><a href="${path}/service/service">Service pages</a></li>
        </ul>
    </div>
</nav>

</body>
</html>
