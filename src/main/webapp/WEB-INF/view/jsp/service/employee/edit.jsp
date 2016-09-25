<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathEmployee" value="${pageContext.request.contextPath}/Restaurant/service/employee"/>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Edit employee</title>
</head>
<body>

<div class="container">
    <h1>Edit existing employee</h1>
    <c:url var="saveUrl" value="${pathEmployee}/edit?id=${employeeView.id}"/>
    <form:form class="form-horizontal" modelAttribute="employeeView" method="POST" action="${saveUrl}">
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
                <label class="control-label" for="name">Employee name:</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="name" type="text"/>
            </div>
            <div class="col-sm-4">
                <label class="label-info">${employeeValidate.nameLabel}</label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="surname">Employee surname:</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="surname" type="text"/>
            </div>
            <div class="col-sm-4">
                <label class="label-info">${employeeValidate.surnameLabel}</label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="birthday">Birthday:</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="birthday" type="date"/>
            </div>
            <div class="col-sm-4">
                <label class="label-info">${employeeValidate.birthdayLabel}</label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="position">Position:</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="position" type="text"/>
            </div>
            <div class="col-sm-4">
                <label class="label-info">${employeeValidate.positionLabel}</label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="salary">Salary:</label>
            </div>
            <div class="col-sm-4">
                <form:input class="form-control" path="salary" type="number"/>
            </div>
            <div class="col-sm-4">
                <label class="label-warning">${employeeValidate.salaryLabel}</label>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Save changes</button>
    </form:form>
    <form class="form-inline" action="${pathEmployee}/employees" method="GET">
        <button class="btn btn-primary" type="submit">Return to employees</button>
    </form>
</div>
</body>
</html>
