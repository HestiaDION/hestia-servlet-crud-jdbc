
<%@ page import="org.example.crud_hestiajdbc_servlet.model.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <style>
    h1{
      text-align: center;
    }
    p{
      text-align: center;
    }
    div{
      display: flex;
      justify-content: center;
      align-items: center;
      align-content: space-between;
    }
    button{
      cursor: pointer;
    }

  </style>
  <meta charset="UTF-8">
  <title>Área do Admin</title>
</head>
<body>
<h1>Bem-vindo!</h1>

<p>Essa é a área restrita do administrador.</p>
<div>
<form action="Admin-Servlet"><button type="submit"><a>Admin</a></button> </form>
  <form action="Boost-Servlet"><button type="submit"><a>Boost</a></button> </form>
  <form action="Filtro-Servlet"><button type="submit"><a>Filtro</a></button> </form>
<a href="Pagamento.jsp">Pagamento</a>
<a href="Plano.jsp">Plano</a>
</div>
</body>
</html>