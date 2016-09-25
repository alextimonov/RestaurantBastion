<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathDish" value="${pageContext.request.contextPath}/Restaurant/service/dish"/>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Delete dish</title>
</head>
<body>

<div class="container">
    <h2>Are you sure to delete this dish?</h2>

    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label">Dish ID</label>
            </div>
            <div class="col-sm-10">
                <label class="control-label">${dishToDelete.id}</label>
            </div>

            <div class="col-sm-2">
                <label class="control-label">Dish name</label>
            </div>
            <div class="col-sm-10">
                <label class="control-label">${dishToDelete.name}</label>
            </div>

            <div class="col-sm-2">
                <label class="control-label">Description</label>
            </div>
            <div class="col-sm-10">
                <label class="control-label">${dishToDelete.description}</label>
            </div>

            <div class="col-sm-2">
                <label class="control-label">Weight</label>
            </div>
            <div class="col-sm-10">
                <label class="control-label">${dishToDelete.weight}</label>
            </div>

            <div class="col-sm-2">
                <label class="control-label">Cost</label>
            </div>
            <div class="col-sm-10">
                <label class="control-label">${dishToDelete.cost}</label>
            </div>
        </div>
    </form>

    <form action="${pathDish}/deleteConfirmed?id=${dishToDelete.id}" method="POST" class="form-horizontal">
        <button class="btn btn-primary" type="submit">Delete this dish</button>
    </form>
    <form class="form-inline" action="${pathDish}/dishes" method="GET">
        <button class="btn btn-primary" type="submit">Return to dishes</button>
    </form>
</div>

</body>
</html>

