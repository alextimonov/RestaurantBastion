<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathDish" value="${pageContext.request.contextPath}/Restaurant/service/dish"/>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Add ingredient to dish</title>
</head>
<body>

<div class="container">
    <h2>Add ingredient to dish ${dish.name}</h2>
    <c:url var="saveUrl" value="${pathDish}/${dish.id}/addItem" />
    <c:url var="editDishUrl" value="${pathDish}/${dish.id}/edit"/>
    <form:form class="form-horizontal" modelAttribute="item" method="POST" action="${saveUrl}">
        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="ingredient.name">Ingredient's name</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="ingredient.name" type="text"/>
            </div>
            <div class="col-sm-4">
                <label class="label-info">${itemValidate.nameLabel}</label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="ingredientWeight">Weight for dish</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="ingredientWeight" type="number"/>
            </div>
            <div class="col-sm-4">
                <label class="label-info">${itemValidate.weightLabel}</label>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-floppy-disk"></span> Add new ingredient</button>
    </form:form>
    <form class="form-inline" action="${editDishUrl}" method="GET">
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-triangle-left"></span> Return to edit dish</button>
    </form>
</div>

</body>
</html>
