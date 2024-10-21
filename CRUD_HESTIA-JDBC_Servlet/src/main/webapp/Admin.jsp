<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.crud_hestiajdbc_servlet.model.Admin" %>
<html>
<head>
    <title>Admin</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .Header {
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        a {
            text-decoration: none;
            color: blue;
        }
        .Botao {
            background-color: deepskyblue;
            color: white;
            display: block;
            width: 200px;
            padding: 10px;
            text-align: center;
            border-radius: 5px;
            margin: 20px auto;
            float: right;
        }
    </style>
</head>
<body>
<div class="Header">
    <h1>PAINEL DE ADMINISTRAÇÃO</h1>
</div>
<form action="Admin-Servlet" method="get">
    <h1>Tabela Admin</h1>
    <a href="InsertAdmin.jsp" class="Botao">Criar</a>
    <table>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <%
            List<Admin> listAdmin = (List<Admin>) request.getAttribute("ListaAdmins");
            if (listAdmin != null && !listAdmin.isEmpty()) {
                for (Admin adm : listAdmin) {
        %>
        <tr>
            <td><%= adm.getuId() %></td>
            <td><%= adm.getcNome() %></td>
            <td><%= adm.getcEmail() %></td>
            <td>
                <a href="Admin?action=edit&id=<%= adm.getuId() %>">Editar</a>
            </td>
            <td>
                <a href="Admin?action=delete&id=<%= adm.getuId() %>" onclick="return confirm('Tem certeza que deseja excluir?');">Excluir</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5">Nenhum administrador encontrado.</td>
        </tr>
        <%
            }
        %>
    </table>
</form>
</body>
</html>