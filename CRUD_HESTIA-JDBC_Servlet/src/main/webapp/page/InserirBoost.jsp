<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Boost</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 500px;
            margin-top: 100px;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Cadastro de Boost</h2>
    <form action="Inserir-Boost-Servlet">
        <div class="mb-3">
            <label for="cNmBoost" class="form-label">Nome do Boost</label>
            <input type="text" class="form-control" id="cNmBoost" name="cNmBoost" required>
        </div>
        <div class="mb-3">
            <label for="nValor" class="form-label">Valor</label>
            <input type="number" class="form-control" id="nValor" name="nValor" step="0.01" required>
        </div>
        <div class="mb-3">
            <label for="nPctBoost" class="form-label">Percentual de Boost</label>
            <input type="number" class="form-control" id="nPctBoost" name="nPctBoost" step="0.1" required>
        </div>
        <div class="mb-3">
            <label for="cDescricao" class="form-label">Descrição</label>
            <textarea class="form-control" id="cDescricao" name="cDescricao" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary w-100">Cadastrar</button>
    </form>
    <br>
    <form action="Boost-Servlet"><button class="btn btn-primary w-100" type="submit"><a>Voltar</a></button> </form>

    <% if (request.getAttribute("successMessage") != null) { %>
    <div class="alert alert-success mt-3">
        <%= request.getAttribute("successMessage") %>
    </div>
    <% } %>

    <% if (request.getAttribute("errorMessage") != null) { %>
    <div class="alert alert-danger mt-3">
        <%= request.getAttribute("errorMessage") %>
    </div>
    <% } %>
</div>
</body>
</html>