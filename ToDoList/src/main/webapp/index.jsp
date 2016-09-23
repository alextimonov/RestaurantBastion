<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! public static final String ENABLED_TASK = "Enabled Task=";%>
<%! public static final String DELETED_TASK = "Deleted Task=";%>

<html>
<head>
    <title> TODO List implementation </title>
</head>

<body>
    <h2> My TODO List </h2>
    <form action="update" method="get">
        <table width = "100%" border = "1" align = "center">
            <tr bgcolor = "#949494">
                <th> Task name </th>
                <th> Category </th>
                <th> Completed </th>
            </tr>

            <%
                Enumeration<String> attributeNames = session.getAttributeNames();
                while (attributeNames.hasMoreElements()) {
                    String taskNameWithPrefix = attributeNames.nextElement();
                    if (taskNameWithPrefix.startsWith(ENABLED_TASK)) {

                        String taskName = taskNameWithPrefix.substring(ENABLED_TASK.length(), taskNameWithPrefix.length());
                        String category = (String) session.getAttribute(taskNameWithPrefix);
                        out.write("<tr>");
                        out.write("<td>" + taskName + "</td>");
                        out.write("<td>" + category + "</td>");
                        out.write("<td> <input type=\"checkbox\" name=\"" + DELETED_TASK + taskNameWithPrefix + "\"></td>");
                        out.print("</tr>");
                    }
                }
            %>
        </table>

        <br>
        <input type="submit" value="Delete completed tasks">
    </form>

    <h3> Add new task </h3>
    <form action="update" method="get">
        <label for="taskName"> Task name: </label>
        <input type="text" name="taskName" id="taskName">
        <br>
        <br>

        <label for="categoryName"> Category name: </label>
        <input type="text" name="categoryName" id="categoryName">
        <br>
        <br>

        <input type="submit" value="Add task">

    </form>

</body>
</html>





