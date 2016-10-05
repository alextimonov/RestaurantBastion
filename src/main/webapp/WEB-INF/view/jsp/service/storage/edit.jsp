<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathStorage" value="${pageContext.request.contextPath}/Restaurant/service/storage"/>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Edit ingredient</title>
</head>
<body>

<div class="container">
    <h2>Edit existing ingredient</h2>
    <c:url var="saveUrl" value="${pathStorage}/edit?id=${ingredient.id}"/>
    <form:form class="form-horizontal" modelAttribute="ingredient" method="POST" action="${saveUrl}">

        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="id">ID</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="id" disabled="true"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="name">Ingredient name</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="name" type="text"/>
            </div>
            <div class="col-sm-4">
                <label class="label-warning">${itemValidate.nameLabel}</label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="amount">Amount</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="amount" type="text"/>
            </div>
            <div class="col-sm-4">
                <label class="label-warning">${itemValidate.amountLabel}</label>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-floppy-disk"></span> Save changes</button>
    </form:form>
    <form class="form-inline" action="${pathStorage}/ingredients" method="GET">
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-triangle-left"></span> Return to ingredients</button>
    </form>
</div>
</body>
</html>
