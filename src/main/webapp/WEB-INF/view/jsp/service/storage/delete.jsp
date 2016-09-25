<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathStorage" value="${pageContext.request.contextPath}/Restaurant/service/storage"/>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Delete ingredient</title>
</head>
<body>

<div class="container">
    <h2>Are you sure to delete ingredient?</h2>

    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Ingredient ID</label>
            </div>
            <div class="col-sm-9">
                <label class="control-label">${ingredient.id}</label>
            </div>

            <div class="col-sm-3">
                <label class="control-label">Ingredient name</label>
            </div>
            <div class="col-sm-9">
                <label class="control-label">${ingredient.name}</label>
            </div>

            <div class="col-sm-3">
                <label class="control-label">Amount</label>
            </div>
            <div class="col-sm-9">
                <label class="control-label">${ingredient.amount}</label>
            </div>
        </div>
    </form>

    <form action="${pathStorage}/deleteConfirmed?id=${ingredient.id}" method="POST" class="form-horizontal">
        <button class="btn btn-primary" type="submit">Delete this ingredient</button>
    </form>
    <form class="form-inline" action="${pathStorage}/ingredients" method="GET">
        <button class="btn btn-primary" type="submit">Return to ingredients</button>
    </form>
</div>

</body>
</html>

