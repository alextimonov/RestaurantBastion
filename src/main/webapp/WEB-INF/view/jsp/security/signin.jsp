<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true"%>

<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>
<c:url value="/j_spring_security_check" var="loginUrl" />
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Sign in</title>
</head>
<body>

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
            <li class="active"><a href="${path}/service/service">Service pages</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="alert alert-success">
        <h3>Security Page: Sign in</h3>
        <div style="color: red">${message}</div>
    </div>

    <form:form  class="form-horizontal" method="POST" action="${loginUrl}">
        <div class="alert alert-success">
            <div class="form-group">
                <div class="col-sm-3">
                    <label for="j_username">Username: </label>
                </div>
                <div class="col-sm-4">
                    <input class="form-control" id="j_username" name="j_username" type="text" placeholder="Enter username"/>
                </div>
            </div>

            <div class="form-group" >
                <div class="col-sm-3">
                    <label for="j_password">Password: </label>
                </div>

                <div class="col-sm-4">
                    <input class="form-control" id="j_password" name="j_password" type="password" placeholder="Enter password"/>
                </div>
            </div>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-hand-right"></span> Login</button>
    </form:form>

    <form class="form-inline" action="${path}/main" method="GET">
        <button  class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-hand-left"></span> Return to main page</button>
    </form>
</div>
</body>
</html>