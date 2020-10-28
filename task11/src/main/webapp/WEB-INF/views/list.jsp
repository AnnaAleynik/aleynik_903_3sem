<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List</title>
    <meta charset="UTF-8">
</head>
<body>
<table>
    <tr>
        <th>Name</th>
        <th>Address</th>
    </tr>
    <c:forEach items="${map}" var="map">
        <tr>
            <td>${map.key}</td>
            <td>${map.value}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
