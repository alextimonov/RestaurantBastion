<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title>My Restaurant|Employee|Detail info</title>
</head>
<body>
<table style="align-items: center" border="1">
    <thead>Employee. Detail info</thead>
    <tr>
        <th> ID </th>
        <th> First name </th>
        <th> Last name </th>
        <th> Position </th>
        <th> Salary </th>
        <th> Birthday </th>
    </tr>
    <tr>
        <td> ${employee.id} </td>
        <td> ${employee.name} </td>
        <td> ${employee.surname} </td>
        <td> ${employee.job.position} </td>
        <td> ${employee.salary} </td>
        <td> ${employee.birthday} </td>
    </tr>

</table>

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