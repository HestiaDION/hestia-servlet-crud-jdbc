<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Formulário de Login</h2>
    <%
        request.setAttribute("action", "read");
    %>
    <form action="login" method="post">

        <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
        <label for="email">Usuário:</label>
        <input type="text" id="email" name="email" required>
        <br>
        <label for="password">Senha:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Entrar</button>
    </form>
</body>
</html>