<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pathOrder" value="${pageContext.request.contextPath}/Restaurant/service/order"/>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bastion|Service|Dishes in Order</title>
</head>
<body>

<div class="container">
    <h2>Dishes in chosen order</h2>

    <h4>Order</h4>
    <table class="table table-striped">
        <tr>
            <th>ID</th>
            <th>Waiter</th>
            <th>Table</th>
            <th>Date</th>
            <th>Is closed</th>
        </tr>
        <tr>
            <td>${order.id}</td>
            <td>${order.waiter.name}</td>
            <td>${order.tableNumber}</td>
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${order.date}"/></td>
            <td> ${order.closed} </td>
        </tr>
    </table>

    <hr>
    <h4>Dishes</h4>
    <table class="table table-striped">
        <tr>
            <th>ID</th>
            <th>name</th>
            <th>cost</th>
            <th>weight</th>
        </tr>
        <c:forEach var="dish" items="${order.dishes}">
        <tr>
            <td>${dish.id}</td>
            <td>${dish.name}</td>
            <td>${dish.cost}</td>
            <td>${dish.weight}</td>
        </tr>
        </c:forEach>
    </table>

    <form action="${pathOrder}/orders" method="GET" class="form-horizontal">
        <button class="btn btn-primary" type="submit">Back to all orders</button>
    </form>

</div>

</body>
</html>

