package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;
import org.example.crud_hestiajdbc_servlet.model.Admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//           instanciando variaveis com o uso do request
        String email = req.getParameter("Email");
        String password = req.getParameter("password");
//           instanciando objetos
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = new Admin("", email, password);

//           excessão caso haja um erro na consulta SQL
        try
        {
//           Verifica se tem alguma linha informando que é um Administrador
            ResultSet rs = adminDAO.selecionarAdminsParaLogin(admin);
            if (rs.next())
            {
                admin.setcNome( rs.getString("cNome"));

                HttpSession session = req.getSession();
                session.setAttribute("administrador", admin);
                req.getRequestDispatcher("../webapp/page/TabelasAdministradoras.jsp").forward(req, resp); // Certifique-se de que o caminho esteja correto
            }
            else
            {
                req.getRequestDispatcher("../page/ErroLogin.jsp").forward(req, resp);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocorreu um erro no servidor.");
        }


    }
}