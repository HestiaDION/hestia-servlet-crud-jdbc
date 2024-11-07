package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet
{
//    DECLARAÇÃO E INSTANCIAÇÃO DE OBJETO ESTÁTICO PARA MEDIAR A INTERAÇÃO COM O BANCO DE DADOS
    AdminDAO adminDAO = new AdminDAO();

//    DEFINIÇÃO DOS MÉTODOS GET E POST PARA GERENCIAR AS AÇÕES DENTRO DO BANCO DE DADOS
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Faz a validação do atributo
        if (Utils.isValidString(action))
        {
            switch (action)
            {
                case "login":
                    login(req, resp);
                    break;

                // Só precisa da requisição, uma vez que é o evento de fechar a aba
                case "logout":
                    logout(req);
                    break;

                default:
                    Utils.logActionManagerSetback(req);
                    req.getRequestDispatcher("login-admin.jsp").forward(req, resp);
            }
        }
        else
        {
            Utils.logServerIssue(req);
            req.getRequestDispatcher("login-admin.jsp").forward(req, resp);
        }
    }

//    DEFINIÇÃO DOS MÉTODOS DE INTERAÇÃO COM O BANCO DE DADOS
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String emailParameter = req.getParameter("email");
        String senhaParameter = req.getParameter("senha");

        if
        (
                Utils.isValidString(emailParameter) &&
                Utils.isValidString(senhaParameter)
        )
        {
            String email = emailParameter;
            String senha = senhaParameter;
            try {
                senha = Utils.encryptPassword(senhaParameter);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            // Declara e instancia objeto de controle para chamar o método de leitura da classe
            AdminController adminController = new AdminController();

            // Realiza consulta no banco de dados com os parâmetros passados
            ResultSet rs = adminDAO.getAdminForLogin(email, senha);

            // Verifica se os parâmetros passados são válidos
            if (rs != null)
            {
                try
                {
                    // Verifica se existe um usuário com essas credenciais
                    if (rs.next())
                    {
                        // Espefica com a classe do objeto que está sendo enviado
                        req.setAttribute("table-identifier", "admin");

                        // Passa o nome do usuário que está acessando, pegando o valor do ResultSet
                        req.setAttribute("user-email", email);

                        // Passa o nome do usuário que está acessando, pegando o valor do ResultSet
                        req.setAttribute("user-name", rs.getString("cNome"));

                        String foto = rs.getString("cFoto");
                        // Passa a foto do usuário que está acessando, pegando o valor do ResultSet
                        req.setAttribute("user-photo", foto);

                        // Altera o status do login do usuário, para sinalizar que ele está ativo
                        adminDAO.setAdminActive(email);

                        // Define uma mensagem para informar que foi possível fazer login
                        Utils.logSuccessfulLogin(req);

                        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
                        adminController.readAdmin(req, resp);

                        // Retorna para não chamar outra página
                        return;
                    }
                    else
                    {
                        Utils.logUserNotFound(req);

                        req.getRequestDispatcher("login-admin.jsp").forward(req, resp);
                    }
                }
                catch (SQLException sqle)
                {
                    // Imprime a exceção no console
                    sqle.printStackTrace();

                    // Erro no banco de dados
                    Utils.logDatabaseIssue(req);

                    req.getRequestDispatcher("login-admin.jsp").forward(req, resp);
                }
            }
            else
            {
                // Erro no banco de dados
                Utils.logDatabaseIssue(req);

                req.getRequestDispatcher("login-admin.jsp").forward(req, resp);
            }
        }
        else
        {
            Utils.logInputSetback(req);

            req.getRequestDispatcher("login-admin.jsp").forward(req, resp);
        }

        // Direciona de volta à página de login, que só é executado em caso de desvio do fluxo esperado
        req.getRequestDispatcher("login-admin.jsp").forward(req, resp);
    }

    private void logout(HttpServletRequest req) throws ServletException, IOException
    {
        // Recupera parâmetro da requisão e o amarzena na variável correspondente
        String emailParameter = req.getParameter("email");

        if (Utils.isValidString(emailParameter))
        {
            String email = emailParameter;

            // Altera o status do login do usuário para inativo
            adminDAO.setAdminInactive(email);
        }
        else
        {
            Utils.logInputSetback(req);
        }
    }
}
