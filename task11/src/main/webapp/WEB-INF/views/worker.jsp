<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.10.2020
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Workers</title>
</head>
<body>
<table>
    <tr>
        <th>BranchID</th>
        <th>City</th>
        <th>Worker</th>
    </tr>
    <c:forEach items="${list}" var="branch">
        <tr>
            <td>${branch.id}</td>
            <td>${branch.city}</td>
            <td></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
