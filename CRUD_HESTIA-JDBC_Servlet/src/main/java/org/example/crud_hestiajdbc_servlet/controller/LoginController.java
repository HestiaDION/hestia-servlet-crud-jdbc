package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;
import org.example.crud_hestiajdbc_servlet.model.Admin;

@WebServlet(name = "login", value = "/login1")
public class LoginController extends HttpServlet // REVISAR ESSA CLASSE INTEIRA
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    { // REVISAR ESSA CLASSE INTEIRA
        String email    = req.getParameter("email");
        String password = req.getParameter("password");

        req.setAttribute("action", "read"); // Define a ação como "read"

        AdminControllerUwU adminControllerUwU = new AdminControllerUwU();
        AdminDAO adminDAO = new AdminDAO();

        ResultSet rs = null;

        rs = adminDAO.selecionarAdminsParaLogin(email, password);

        try {
            if (rs != null && rs.next())
            { // REVISAR ESSA CLASSE INTEIRA
                //Alterando o status do login do usuário para '1' (Ativo)
                adminDAO.setAdminActive(email);

                ValidationUtilsUwU.logSuccessfulLogin(req);

                req.setAttribute("table-identifier", "admin");

                // Continua a requisição
                adminControllerUwU.readAdmin(req, resp);
            } // REVISAR ESSA CLASSE INTEIRA
            else
            { // REVISAR ESSA CLASSE INTEIRA
                ValidationUtilsUwU.logInputSetback(req);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } // REVISAR ESSA CLASSE INTEIRA
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // REVISAR ESSA CLASSE INTEIRA
} // REVISAR ESSA CLASSE INTEIRA
