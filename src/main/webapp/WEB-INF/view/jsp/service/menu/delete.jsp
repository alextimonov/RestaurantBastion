<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathMenu" value="${pageContext.request.contextPath}/Restaurant/service/menu"/>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Delete menu</title>
</head>
<body>

<div class="container">
    <h2>Are you sure to delete this menu?</h2>

    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label">Menu ID</label>
            </div>
            <div class="col-sm-10">
                <label class="control-label">${menuToDelete.id}</label>
            </div>

            <div class="col-sm-2">
                <label class="control-label">Menu name</label>
            </div>
            <div class="col-sm-10">
                <label class="control-label">${menuToDelete.name}</label>
            </div>
        </div>
        <h3>Dishes</h3>
        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Dish name</th>
                <th>Weight</th>
                <th>Cost</th>
            </tr>
            <c:forEach var="dish" items="${menu.dishes}">
                <tr>
                    <td>${dish.id}</td>
                    <td>${dish.name}</td>
                    <td>${dish.weight}</td>
                    <td>${dish.cost}</td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <form action="${pathMenu}/deleteConfirmed?id=${menuToDelete.id}" method="POST" class="form-horizontal">
        <button class="btn btn-primary" type="submit">Delete this menu</button>
    </form>
    <form action="${pathMenu}/menus" method="GET" class="form-horizontal">
        <button class="btn btn-primary" type="submit">Return to menus</button>
    </form>
</div>

</body>
</html>

