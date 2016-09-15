<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>
<html>
<head>
    <title>Add new dish</title>
</head>
<body>

<h1>Edit existing dish</h1>
<c:url var="saveUrl" value="${path}/dish/edit?id=${dishExisting.id}"/>
<form:form modelAttribute="dishExisting" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="id">ID:</form:label></td>
            <td><form:input path="id" disabled="true"/></td>
        </tr>


        <tr>
            <td><form:label path="name">Dish name:</form:label></td>
            <td><form:input path="Name"/></td>
        </tr>

        <tr>
            <td><form:label path="description">Description: </form:label></td>
            <td><form:input path="Description"/></td>
        </tr>

        <tr>
            <td><form:label path="weight">Weight:</form:label></td>
            <td><form:input path="Weight"/></td>
        </tr>

        <tr>
            <td><form:label path="cost">Cost: </form:label></td>
            <td><form:input path="Cost"/></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>

</body>
</html>
