<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathEmployee" value="${pageContext.request.contextPath}/Restaurant/service/employee"/>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Add new employee</title>
</head>
<body>

<div class="container">
    <h2>Create new employee</h2>
    <c:url var="saveUrl" value="${pathEmployee}/add"/>
    <form:form class="form-horizontal" modelAttribute="employeeView" method="POST" action="${saveUrl}">
        <div class="form-group">
            <div class="col-sm-2">
                <label class="control-label" for="name">Name:</label>
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
                <label class="control-label" for="surname">Surname:</label>
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
                <label class="label-info">${employeeValidate.salaryLabel}</label>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-floppy-disk"></span> Save new employee</button>
    </form:form>
    <form class="form-inline" action="${pathEmployee}/employees" method="GET">
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-triangle-left"></span> Return to employees</button>
    </form>
</div>

</body>
</html>