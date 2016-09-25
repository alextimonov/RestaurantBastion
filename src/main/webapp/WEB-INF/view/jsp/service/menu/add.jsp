<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathMenu" value="${pageContext.request.contextPath}/Restaurant/service/menu"/>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Add new menu</title>
</head>
<body>

<div class="container">
    <h2>Create new menu</h2>
    <c:url var="saveUrl" value="${pathMenu}/add"/>

    <form:form class="form-horizontal" modelAttribute="menuAttribute" method="POST" action="${saveUrl}">
        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="name">New menu's name:</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="name" type="text"/>
            </div>
            <div class="col-sm-4">
                <label class="label-info">${menuValidate.nameLabel}</label>
            </div>
        </div>
        <input type="submit" class="btn btn-primary" value="Save new menu"/>
    </form:form>
    <form class="form-inline" action="${pathMenu}/menus" method="GET">
        <button class="btn btn-primary" type="submit">Return to menus</button>
    </form>
</div>

</body>
</html>
