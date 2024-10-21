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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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



}

