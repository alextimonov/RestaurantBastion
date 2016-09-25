<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathDish" value="${pageContext.request.contextPath}/Restaurant/service/dish"/>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Delete ingredient from dish</title>
</head>
<body>

<div class="container">
    <h2>Are you sure to delete this ingredient from dish ${dish.name}?</h2>
    <c:url var="editDishUrl" value="${pathDish}/${dish.id}/edit"/>
    <c:url var="deleteItemUrl" value="${pathDish}/${dish.id}/deleteItemConfirmed?id=${item.id}"/>

    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label">Ingredient ID</label>
            </div>
            <div class="col-sm-10">
                <label class="control-label">${item.ingredient.id}</label>
            </div>

            <div class="col-sm-2">
                <label class="control-label">Ingredient's name</label>
            </div>
            <div class="col-sm-10">
                <label class="control-label">${item.ingredient.name}</label>
            </div>

            <div class="col-sm-2">
                <label class="control-label">Weight for dish</label>
            </div>
            <div class="col-sm-10">
                <label class="control-label">${item.ingredientWeight}</label>
            </div>

        </div>
    </form>

    <form action="${deleteItemUrl}" method="POST" class="form-horizontal">
        <button class="btn btn-primary" type="submit">Delete this ingredient</button>
    </form>
    <form class="form-inline" action="${editDishUrl}" method="GET">
        <button class="btn btn-primary" type="submit">Return to edit dish</button>
    </form>
</div>

</body>
</html>
