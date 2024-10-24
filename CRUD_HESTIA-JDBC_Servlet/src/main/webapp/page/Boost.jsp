<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.crud_hestiajdbc_servlet.model.Admin" %>
<%@ page import="org.example.crud_hestiajdbc_servlet.model.Filtro" %>
<%@ page import="org.example.crud_hestiajdbc_servlet.model.Boost" %>
<html>
<head>
    <title>Boost</title>
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
<form action="Boost-Servlet" method="get">
    <h1>Tabela Admin</h1>
    <a href="InsertBoost.jsp" class="Botao">Criar</a>
    <table>
        <tr>
            <th>Id</th>
            <th>Tipo do Boost</th>
            <th>Valor</th>
            <th>Porcentagem de Boost</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <%
            List<Boost> listBoost = (List<Boost>) request.getAttribute("ListaBoost");
            if (listBoost != null && !listBoost.isEmpty()) {
                for (Boost boost : listBoost) {
        %>
        <tr>
            <td><%= boost.getuId() %></td>
            <td><%= boost.getcNmBoost() %></td>
            <td><%= boost.getnValor() %></td>
            <td><%= boost.getnPctBoost() %></td>
            <td>
                <a href="Boost?action=edit&id=<%= boost.getuId() %>">Editar</a>
            </td>
            <td>
                <form action="Boost-Servlet" method="post" onsubmit="return confirm('Tem certeza que deseja excluir?');">
                    <input type="hidden" name="uId" value="<%= boost.getuId() %>" />
                    <input type="submit" value="Excluir" />
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Nenhum Boost encontrado.</td>
        </tr>
        <%
            }
        %>
    </table>
</form>
</body>
</html>
