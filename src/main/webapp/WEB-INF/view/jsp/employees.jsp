<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title>My Restaurant|Employees</title>
</head>
<body>
<h3>Our personal</h3>
<table style="align-items: center" border="1">
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Position</th>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td><a href="${path}/employee/${employee.name}">${employee.name}</a></td>
            <td>${employee.surname}</td>
            <td>${employee.job.position}</td>
        </tr>
    </c:forEach>

</table>
<br>
<br>
<a href="${path}/main">Main page</a> |
<a href="${path}/menus">Our menus</a> |
<a href="${path}/findDishByName">Find your favorite dish</a> |
<a href="${path}/scheme">Restaurant's scheme</a> |
<a href="${path}/contacts">Contacts</a> |
<a href="${path}/waiters">Our Waiters</a> |
<a href="${path}/employees">Our personal</a>

</body>
</html>