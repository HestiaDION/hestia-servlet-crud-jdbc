<%--
  Created by IntelliJ IDEA.
  User: pietromedico-ieg
  Date: 19/10/2024
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <style>
        p{
            color: red;
            position: absolute;
            text-align: center;
            transition: none 4s;

        }
    </style>
    <title>Title</title>
</head>
<body>
<jsp:include page="index.html"/>

<p>Senha ou Email Incorreto</p>

</body>
</html>
