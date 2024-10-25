package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;
import org.example.crud_hestiajdbc_servlet.model.Admin;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "Inserir-Admin", value = "/Inserir-Admin-Servlet")
public class CadastrarAdmin extends HttpServlet {
    AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nome = req.getParameter("cNome");
            String email = req.getParameter("cEmail");
            String senha = req.getParameter("cSenha");
            UUID uId = UUID.randomUUID();
            if (nome == null || email == null || senha == null) {
                req.setAttribute("errorMessage", "Todos os campos são obrigatórios.");
                req.getRequestDispatcher("page/InserirAdmin.jsp").forward(req, resp);
                return;
            }

            adminDAO.adicionarAdmin(new Admin(uId, nome, email, senha));

            // Redirecionar para uma página de sucesso ou confirmação
            req.getRequestDispatcher("page/InserirAdmin.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            // Tratar o erro e redirecionar para uma página de erro, se necessário
        }
    }
}
