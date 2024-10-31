<%--
  Created by IntelliJ IDEA.
  User: mateusaraujo-ieg
  Date: 28/10/2024
  Time: 09:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setAttribute("action", "read");
    %>
    <form action="login" method="get">
        <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
        <input type="submit" value="Enviar">
    </form>
</body>
</html>
