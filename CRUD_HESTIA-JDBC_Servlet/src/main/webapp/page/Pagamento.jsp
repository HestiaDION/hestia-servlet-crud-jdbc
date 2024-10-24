<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page import="org.example.crud_hestiajdbc_servlet.model.Admin" %>--%>
<%--<%@ page import="org.example.crud_hestiajdbc_servlet.model.Filtro" %>--%>
<%--<%@ page import="org.example.crud_hestiajdbc_servlet.model.Pagamento" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Pagamento</title>--%>
<%--    <style>--%>
<%--        body {--%>
<%--            font-family: Arial, sans-serif;--%>
<%--        }--%>
<%--        .Header {--%>
<%--            text-align: center;--%>
<%--            margin-bottom: 20px;--%>
<%--        }--%>
<%--        table {--%>
<%--            width: 100%;--%>
<%--            border-collapse: collapse;--%>
<%--            margin-top: 10px;--%>
<%--        }--%>
<%--        th, td {--%>
<%--            border: 1px solid #ccc;--%>
<%--            padding: 10px;--%>
<%--            text-align: left;--%>
<%--        }--%>
<%--        th {--%>
<%--            background-color: #f2f2f2;--%>
<%--        }--%>
<%--        a {--%>
<%--            text-decoration: none;--%>
<%--            color: blue;--%>
<%--        }--%>
<%--        .Botao {--%>
<%--            background-color: deepskyblue;--%>
<%--            color: white;--%>
<%--            display: block;--%>
<%--            width: 200px;--%>
<%--            padding: 10px;--%>
<%--            text-align: center;--%>
<%--            border-radius: 5px;--%>
<%--            margin: 20px auto;--%>
<%--            float: right;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="Header">--%>
<%--    <h1>PAINEL DE ADMINISTRAÇÃO</h1>--%>
<%--</div>--%>
<%--<form action="Pagamento-Servlet" method="get">--%>
<%--    <h1>Tabela Admin</h1>--%>
<%--    <a href="InsertPagamento.jsp" class="Botao">Criar</a>--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <th>Id</th>--%>
<%--            <th>Ativo</th>--%>
<%--            <th>Data de Fim</th>--%>
<%--            <th>Porcentagem de desconto</th>--%>
<%--            <th>Valor</th>--%>
<%--            <th>Id Anunciante</th>--%>
<%--            <th>Id Plano</th>--%>
<%--            <th>Id Universitário</th>--%>
<%--            <th>Edit</th>--%>
<%--            <th>Delete</th>--%>
<%--        </tr>--%>
<%--        <%--%>
<%--            List<Pagamento> listPagamento = (List<Pagamento>) request.getAttribute("ListaPagamento");--%>
<%--            if (listPagamento != null && !listPagamento.isEmpty()) {--%>
<%--                for (Pagamento pagamento : listPagamento) {--%>
<%--        %>--%>
<%--        <tr>--%>
<%--            <td><%= pagamento.getuId() %></td>--%>
<%--            <td><%= pagamento.getcAtivo() %></td>--%>
<%--            <td><%= pagamento.getdDtFim() %></td>--%>
<%--            <td><%= pagamento.getnPctDesconto() %></td>--%>
<%--            <td><%= pagamento.getnValor() %></td>--%>
<%--            <td><%= pagamento.getuId_Anunciante() %></td>--%>
<%--            <td><%= pagamento.getuId_Plano() %></td>--%>
<%--            <td><%= pagamento.getuId_Universitario() %></td>--%>
<%--            <td>--%>
<%--                <a href="Pagamento?action=edit&id=<%= pagamento.getuId() %>">Editar</a>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <form action="Pagamento-Servlet" method="post" onsubmit="return confirm('Tem certeza que deseja excluir?');">--%>
<%--                    <input type="hidden" name="uId" value="<%= pagamento.getuId() %>" />--%>
<%--                    <input type="submit" value="Excluir" />--%>
<%--                </form>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <%--%>
<%--            }--%>
<%--        } else {--%>
<%--        %>--%>
<%--        <tr>--%>
<%--            <td colspan="10">Nenhum Pagamento encontrado.</td>--%>
<%--        </tr>--%>
<%--        <%--%>
<%--            }--%>
<%--        %>--%>
<%--    </table>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
