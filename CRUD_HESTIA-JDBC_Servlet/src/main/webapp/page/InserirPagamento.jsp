<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Pagamento</title>
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
    <h2>Cadastro de Pagamento</h2>
    <form action="Inserir-Pagamento-Servlet" >
        <div class="mb-3">
            <label for="cAtivo" class="form-label">Ativo (Sim/Não)</label>
            <input type="text" class="form-control" id="cAtivo" name="cAtivo" required>
        </div>
        <div class="mb-3">
            <label for="dDtFim" class="form-label">Data de Fim</label>
            <input type="date" class="form-control" id="dDtFim" name="dDtFim" required>
        </div>
        <div class="mb-3">
            <label for="nPctDesconto" class="form-label">Percentual de Desconto</label>
            <input type="number" class="form-control" id="nPctDesconto" name="nPctDesconto" step="0.1" required>
        </div>
        <div class="mb-3">
            <label for="nTotal" class="form-label">Total</label>
            <input type="number" class="form-control" id="nTotal" name="nTotal" step="0.01" required>
        </div>
        <div class="mb-3">
            <label for="uId_Anunciante" class="form-label">ID do Anunciante</label>
            <input type="text" class="form-control" id="uId_Anunciante" name="uId_Anunciante" required>
        </div>
        <div class="mb-3">
            <label for="uId_Plano" class="form-label">ID do Plano</label>
            <input type="text" class="form-control" id="uId_Plano" name="uId_Plano" required>
        </div>
        <div class="mb-3">
            <label for="uId_Universitario" class="form-label">ID do Universitário</label>
            <input type="text" class="form-control" id="uId_Universitario" name="uId_Universitario" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Cadastrar</button>
    </form>
    <form action="Pagamento-Servlet">
        <button class="btn btn-secondary w-100 mt-2" type="submit">Voltar</button>
    </form>
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
