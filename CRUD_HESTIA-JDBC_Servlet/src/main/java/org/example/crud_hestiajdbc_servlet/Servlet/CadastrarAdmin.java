package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "inserirAdmin",value = "Inserir-Admin")
public class CadastrarAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nome = req.getParameter("cNome");
            String email = req.getParameter("cEmail");
            String senha = req.getParameter("cSenha");
            UUID uId = UUID.randomUUID();
            resp.setContentType("page/InserirAdmin.jsp");


        }
        catch (Exception e) {}
    }
}
