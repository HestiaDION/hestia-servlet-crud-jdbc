<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Admin</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body
        style="
      --white: #fdfdfd;
      --main-color: #195198;
      --secondary-color: #2e1e92;
      --text-color: #00224d;
      --red: #e20a3d;
      --shadow: #00224daa;
    "
>
<div class="background" id="admin-login">
    <div class="login-card">
        <h1>Welcome to</h1>
        <div class="login-title">
            <img src="images/icons/hestia light blue.svg" alt="">
            <div class="vertical-line"></div>
            Admin
        </div>
        <p>Os fundadores da empresa DION fazem parte da turma de 2024 do primeiro ano TECH do Instituto!</p>
        <div id="form-container">
            <%
                request.setAttribute("action", "login");
            %>
            <form action="login" method="post">
                <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
                <div class="input-container">
                    <label for="email">E-mail</label>
                    <input
                            type="text"
                            name="email"
                            id="email"
                            pattern="^\S{8,100}@germinare\.org\.br$"
                            placeholder=""
                            required
                    />

                </div>
                <div class="input-container">
                    <label for="senha">Senha</label>
                    <input
                            type="password"
                            name="senha"
                            pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])\S{4,100}$"
                            id="senha"
                            placeholder=""
                            required
                    />

                </div>
                <input type="submit" value="Login" />
            </form>
        </div>
        <a href="login-secret-area.html">Clique aqui para acessar a área secreta</a>
    </div>

    <div class="image-container"><img src="images/icons/Teamwork.png" alt="" class="main-image"></div>    </div>
</body>
</html>
