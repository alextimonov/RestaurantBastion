<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathMenu" value="${pageContext.request.contextPath}/Restaurant/service/menu"/>
<c:set var="pathService" value="${pageContext.request.contextPath}/Restaurant/service"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Edit menu</title>
</head>
<body>

<div class="container">
    <h2>Edit existing menu</h2>
    <c:url var="saveUrl" value="${pathMenu}/edit?id=${menuAttribute.id}"/>
    <form:form class="form-horizontal" modelAttribute="menuAttribute" method="POST" action="${saveUrl}">
        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="id">ID:</label>
            </div>
            <div class="col-sm-3">
                <form:input class="form-control" path="id" disabled="true"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="name">Menu name:</label>
            </div>
            <div class="col-sm-3">
                <form:input class="form-control" path="name" type="text"/>
            </div>
            <div class="col-sm-4">
                <label class="label-info">${menuValidate.nameLabel}</label>
            </div>
        </div>
        <div class="form-group">
            <h3>Dishes</h3>
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>Dish name</th>
                    <th>Description</th>
                    <th>Cost</th>
                    <th>Weight</th>
                    <th>Delete</th>
                    <th>Edit</th>
                </tr>
                <c:url var="addUrl" value="${pathMenu}/${menuAttribute.id}/addDish"/>
                <c:forEach var="dish" items="${menuAttribute.dishes}" varStatus="loop">
                    <c:url var="deleteUrl" value="${pathMenu}/${menuAttribute.id}/deleteDish?id=${dish.id}"/>
                    <c:url var="editUrl" value="${pathService}/dish/edit?id=${dish.id}" />
                    <tr>
                        <td><form:input class="form-control" path="dishes[${loop.index}].id"/></td>
                        <td><form:input class="form-control" path="dishes[${loop.index}].name"/></td>
                        <td><form:input class="form-control" path="dishes[${loop.index}].description"/></td>
                        <td><form:input class="form-control" path="dishes[${loop.index}].cost"/></td>
                        <td><form:input class="form-control" path="dishes[${loop.index}].weight"/></td>
                        <td><a href="${deleteUrl}">Delete</a></td>
                        <td><a href="${editUrl}">Edit</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="container">
            <button class="btn btn-primary" type="submit">
                <span class="glyphicon glyphicon-floppy-disk"></span> Save changes</button>
        </div>
    </form:form>
    <form class="form-inline" action="${pathMenu}/menus" method="GET">
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-triangle-left"></span> Return to menus</button>
    </form>

    <form class="form-horizontal" action="${pathMenu}/${menuAttribute.id}/addDish" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Add dish. Input dish's ID:</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="number" name="id" title="id">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-plus-sign"></span> Add dish (by ID) to menu</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathMenu}/${menuAttribute.id}/addDishByName" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Add dish. Input dish's name:</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="text" name="name" title="name">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-plus-sign"></span> Add dish (by name) to menu</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathMenu}/${menuAttribute.id}/deleteDish" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Delete. Input dish's id:</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="number" name="id" title="id">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-trash"></span> Delete dish by id</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pathMenu}/${menuAttribute.id}/deleteDishByName" method="GET">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="control-label">Delete. Input dish's name:</label>
            </div>
            <div class="col-sm-4">
                <input class="form-control" type="text" name="name" title="name">
            </div>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-trash"></span> Delete by name</button>
            </div>
        </div>
    </form>

</div>
</body>
</html>