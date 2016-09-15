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
        <th> First name </th>
        <th> Last name </th>
        <th> Position </th>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td> <a href="${path}/employee/${employee.name}"> ${employee.name} </a> </td>
            <td> ${employee.surname} </td>
            <td> ${employee.job.position} </td>
        </tr>
    </c:forEach>

</table>
<br>
<br>
<a href="${path}/service">Service page</a> |
<a href="${path}/service/employees">Staff</a> |
<a href="${path}/service/menus">Menus</a> |
<a href="${path}/service/dishes">Dishes</a> |
<a href="${path}/service/storage">Storage</a> |
<a href="${path}/service/orders">Orders history</a>


</body>
</html>
