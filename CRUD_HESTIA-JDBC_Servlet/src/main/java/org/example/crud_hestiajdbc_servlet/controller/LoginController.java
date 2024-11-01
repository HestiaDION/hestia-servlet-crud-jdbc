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

        AdminDAO adminDAO = new AdminDAO();
        ResultSet rs = null;

        try
        {
            rs = adminDAO.selecionarAdminsParaLogin(email, password);

            if (rs != null && rs.next())
            {
                //Alterando o status do login do usuário para '1' (Ativo)
                adminDAO.atualizarAdminLogin(email);

                ValidationUtilsUwU.logSuccessfulLogin(req);

                req.setAttribute("table-identifier", "admin");
                req.setAttribute("action", "read"); // Define a ação como "read"

                AdminControllerUwU adminControllerUwU = new AdminControllerUwU();

                adminControllerUwU.readAdmin(req, resp);
            }
            else
            {
                // Login falhou
                req.setAttribute("success", false);
                req.setAttribute("log", "Usuário ou senha incorretos.");
                req.setAttribute("table-identifier", "admin");
                req.setAttribute("action", "read");

                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            req.setAttribute("success", false);
            req.setAttribute("log", "Erro ao acessar o banco de dados: " + e.getMessage());
            req.setAttribute("action", "read");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } 
        finally 
        {
            if (rs != null) 
            {
                try 
                {
                    rs.close();
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
}