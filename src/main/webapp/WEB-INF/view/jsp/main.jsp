<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Restaurant Bastion</title>
</head>
<body background="${pageContext.request.contextPath}/images/back.jpg">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${path}/main">Restaurant Bastion</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="${path}/main">Home</a></li>
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
    <form class="form-horizontal">
        <h2>Restaurant Bastion 30.09.2016 15:55<small> We make your free time unforgettable</small></h2>
        <h4>Kyiv, Blvd. Buchmy, 10. Tel. 123-45-67, E-mail:bastion@bastion.kiev.ua</h4>
        <img src="${pageContext.request.contextPath}/images/photo_inside.jpg" class="img-rounded"/>
    </form>
    <hr>
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

</body>
</html>
