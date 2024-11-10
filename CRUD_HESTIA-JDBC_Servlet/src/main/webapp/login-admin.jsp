<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="images/icons/hestia_icon-CXk1drsB.svg">    <title>Login Admin</title>
    <link rel="stylesheet" href="css/login.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
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
<div id="loading" class="hide-loading"></div>
<div class="background" id="admin-login">
    <%
        if (request.getAttribute("success") != null) {
        boolean success = (boolean) request.getAttribute("success");
        String log = (String) request.getAttribute("log");
        if (log != null) {
    %>
    <div class="alert" style="--alert-color: <%= success ? "#37c87b" : "#e20a3d" %>">
        <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1">
            <%= success ? "check_circle" : "error" %>
        </span>
        <p><%= log %>
        </p>
        <div class="close-alert">
            <i class="material-icons">close</i>
        </div>
    </div>
    <%
        }
        }
    %>
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
            <form action="login" method="post" onsubmit="document.getElementById('loading').classList.remove('hide-loading')">
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

<script>
    document.querySelector('.close-alert').addEventListener('click', function () {
        document.querySelector('.alert').classList.add('hide-alert');
    });
</script>
</body>
</html>
