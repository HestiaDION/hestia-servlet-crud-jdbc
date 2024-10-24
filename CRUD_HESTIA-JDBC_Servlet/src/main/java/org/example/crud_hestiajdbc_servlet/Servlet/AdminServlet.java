package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;
import org.example.crud_hestiajdbc_servlet.model.Admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "AdminServlet", value = "/Admin-Servlet")
public class AdminServlet extends HttpServlet {
    private final AdminDAO adminDAO = new AdminDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        listAdmins(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            excluirAdmin(request, response);
            listAdmins(request, response);


    }

    private void listAdmins(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Admin> admins = new ArrayList<>();

        try (ResultSet rs = adminDAO.selecionarTodosAdmins()) {
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setuId(UUID.fromString(rs.getString("uId")));
                admin.setcNome(rs.getString("cNome"));
                admin.setcEmail(rs.getString("cEmail"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Erro ao obter dados do banco de dados.");
            request.getRequestDispatcher("erro.html").forward(request, response);
            return;
        }
        System.out.println(admins);
        request.setAttribute("ListaAdmins", admins);
        request.getRequestDispatcher("page/Admin.jsp").forward(request, response);
    }

    private void excluirAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uId = request.getParameter("uId");
        if (uId != null && !uId.isEmpty()) {
            try {
                Admin admin = new Admin();
                admin.setuId(UUID.fromString(uId));

                int isDeleted = adminDAO.removerAdmin(admin);
                if (isDeleted == 1) {
                    request.setAttribute("successMessage", "Admin excluído com sucesso!");
                    request.getRequestDispatcher("page/Admin.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Erro ao excluir o admin.");
                }
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMessage", "ID do admin inválido.");
            }
        } else {
            request.setAttribute("errorMessage", "ID do admin não fornecido.");
        }
    }
    private void inserirAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("cNome");
        String email = request.getParameter("cEmail");
        String senha = request.getParameter("cSenha");
        UUID uId = UUID.randomUUID();



        Admin admin = new Admin(uId,nome,email,senha);
        adminDAO.adicionarAdmin(admin);

        request.getRequestDispatcher("page/Admin.jsp");
    }

    private void editarAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("cNome");
        String email = request.getParameter("cEmail");
        String senha = request.getParameter("cSenha");

        Admin admin = new Admin(nome, email, senha);
        adminDAO.atualizarAdmin(admin);

        request.getRequestDispatcher("page/Admin.jsp");
    }

}





