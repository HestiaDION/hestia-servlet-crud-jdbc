package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;
import org.example.crud_hestiajdbc_servlet.model.Admin;

// EXPLODIUFDIAufidsaufiuaspo[dfuipisaudfpuiasdpfuasdpfuipua´pf

@WebServlet(name = "login", value = "/login")
public class LoginUwU extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
//        // Declaração de variáveis
//        String email = req.getParameter("email");
//        String password = req.getParameter("senha");
//
//        // Instanciando objetos
//        AdminDAO adminDAO = new AdminDAO();
//        Admin admin = new Admin("", email, password);
        req.setAttribute("action", "read");
        req.getRequestDispatcher("Crud.jsp").forward(req, resp);

        // Exceção caso haja um erro na consulta SQL
//        try
//        {
//            // Verifica se tem alguma linha informando que é um Administrador
//            ResultSet rs = adminDAO.selecionarAdminsParaLogin(admin);
//            if (rs.next())
//            {
//                admin.setcNome(rs.getString("cNome"));
//
//                HttpSession session = req.getSession();
//                session.setAttribute("administrador", admin);
//                req.getRequestDispatcher("pages/TabelasAdministradoras.jsp").forward(req, resp); // Certifique-se de que o caminho esteja correto
//            }
//            else
//            {
//                req.getRequestDispatcher("pages/ErroLogin.jsp").forward(req, resp);
//            }
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//            // Revisitado: Responde com erro de servidor
//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocorreu um erro no servidor.");
//        }
    }
}
