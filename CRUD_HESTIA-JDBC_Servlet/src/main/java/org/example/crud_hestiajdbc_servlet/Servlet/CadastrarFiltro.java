package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;
import org.example.crud_hestiajdbc_servlet.dao.FiltroDAO;
import org.example.crud_hestiajdbc_servlet.model.Admin;
import org.example.crud_hestiajdbc_servlet.model.Filtro;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "Inserir-Filtro", value = "/Inserir-Filtro-Servlet")
public class CadastrarFiltro extends HttpServlet {
    FiltroDAO filtroDAO = new FiltroDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nome = req.getParameter("cNome");
            String categoria = req.getParameter("cCategoria");
            UUID uId = UUID.randomUUID();
            if (nome == null || categoria == null ) {
                req.setAttribute("errorMessage", "Todos os campos são obrigatórios.");
                req.getRequestDispatcher("page/InserirFiltro.jsp").forward(req, resp);
                return;
            }

            filtroDAO.adicionarFiltro(new Filtro(uId, nome, categoria));

            // Redirecionar para uma página de sucesso ou confirmação
            req.getRequestDispatcher("page/InserirFiltro.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            // Tratar o erro e redirecionar para uma página de erro, se necessário
        }
    }
}
