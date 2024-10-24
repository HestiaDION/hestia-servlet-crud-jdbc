<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.crud_hestiajdbc_servlet.model.Admin" %>
<%@ page import="org.example.crud_hestiajdbc_servlet.model.Filtro" %>
<html>
<head>
    <title>Filtro</title>
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
<form action="Filtro-Servlet" method="get">
    <h1>Tabela Filtro</h1>
    <a href="InsertAdmin.jsp" class="Botao">Criar</a>
    <table>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Categoria</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <%
            List<Filtro> listFiltro = (List<Filtro>) request.getAttribute("ListaFiltro");
            if (listFiltro != null && !listFiltro.isEmpty()) {
                for (Filtro filtro : listFiltro) {
        %>
        <tr>
            <td><%= filtro.getuId() %></td>
            <td><%= filtro.getcNome() %></td>
            <td><%= filtro.getcCategoria() %></td>
            <td>
                <a href="Filtro?action=edit&id=<%= filtro.getuId() %>">Editar</a>
            </td>
            <td>
                <form action="Filtro-Servlet" method="post" onsubmit="return confirm('Tem certeza que deseja excluir?');">
                    <input type="hidden" name="uId" value="<%= filtro.getuId() %>" />
                    <input type="submit" value="Excluir" />
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5">Nenhum Filtro encontrado.</td>
        </tr>
        <%
            }
        %>
    </table>
</form>
</body>
</html>