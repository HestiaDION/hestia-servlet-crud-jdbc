<%--
  Created by IntelliJ IDEA.
  User: pietromedico-ieg
  Date: 01/11/2024
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Admin</title>
    <link rel="stylesheet" href="login.css">
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
            <img src="icons/hestia light blue.svg" alt="">
            <div class="vertical-line"></div>
            Admin
        </div>
        <p>Os fundadores da empresa DION fazem parte da turma de 2024 do primeiro ano TECH do Instituto!</p>
        <div id="form-container">
            <form action="sign-up">
                <div class="input-container">
                    <input
                            type="text"
                            name="email"
                            id="email"
                            pattern="^[\w\-\.]+@([\w\-]+\.)+[\w\-]{2,}$"
                            placeholder=""
                            required
                    />
                    <label for="email">E-mail</label>
                </div>
                <div class="input-container">
                    <input
                            type="password"
                            name="password"
                            pattern=".{8,}"
                            id="password"
                            placeholder=""
                            required
                    />
                    <label for="password">Senha</label>
                </div>
                <input type="submit" value="Login" />
            </form>
        </div>
        <a href="login-secret-area.html">Clique aqui para acessar a área secreta</a>
    </div>

    <div class="image-container"><img src="icons/web-maintenance.png" alt="" class="main-image"></div>    </div>
</body>
</html>