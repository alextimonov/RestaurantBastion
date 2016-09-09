<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>

<html>
<head>
    <title> My Restaurant </title>
</head>
<body>
<h2>Restaurant Bastion. Service page</h2>
<h3>Current time: ${currentTime}</h3>
<br>
<img src="${pageContext.request.contextPath}/images/photo_inside.jpg" />
<br>
<a href="${path}/main">Main page</a>
<br>
<a href="${path}/employees">Show all employees</a>
<br>
<a href="${path}/menus">Show all menus</a>
<br>
<a href="${path}/dishes">Show all dishes</a>

</body>
</html>
