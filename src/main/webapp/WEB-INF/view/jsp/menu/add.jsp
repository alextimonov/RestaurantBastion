<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>
<html>
<head>
    <title>Bastion|Service|Add menu</title>
</head>
<body>

<h1>Create new menu</h1>
<c:url var="saveUrl" value="${path}/menu/add" />

<form:form modelAttribute="menuAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="name">New menu's name:</form:label></td>
            <td><form:input path="Name"/></td>
        </tr>
    </table>
    <input type="submit" value="Save" />
</form:form>

</body>
</html>
