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

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        req.setAttribute("action", "read"); // Define a ação como "read"

        AdminControllerUwU adminControllerUwU = new AdminControllerUwU();
        AdminDAO adminDAO = new AdminDAO();
        ResultSet rs = null;

//        try
//        {
            rs = adminDAO.selecionarAdminsParaLogin(email, password);

            if (rs != null)
            {
                //Alterando o status do login do usuário para '1' (Ativo)
//                adminDAO.atualizarAdminLogin(email);

                ValidationUtilsUwU.logSuccessfulLogin(req);

                req.setAttribute("table-identifier", "admin");

                // Continua a requisição
                adminControllerUwU.readAdmin(req, resp);
            }
            else
            {
                // Login falhou
                ValidationUtilsUwU.logInputSetback(req);
            }
//        }
//        catch (SQLException sqle)
//        {
//            sqle.printStackTrace();
//
//            ValidationUtilsUwU.logDatabaseIssue(req);
//            req.getRequestDispatcher("login.jsp").forward(req, resp);
//        }
    }
}
