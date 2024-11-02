package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;

@WebServlet(name = "login", value = "/login")
public class LoginControllerUwU extends HttpServlet
{
//    DECLARAÇÃO E INSTANCIAÇÃO DE OBJETO ESTÁTICO PARA MEDIAR A INTERAÇÃO COM O BANCO DE DADOS
    AdminDAO adminDAO = new AdminDAO();

//    DEFINIÇÃO DOS MÉTODOS GET E POST PARA GERENCIAR AS AÇÕES DENTRO DO BANCO DE DADOS
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Faz a validação do atributo
        if (ValidationUtilsUwU.isValidString(action))
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
                    ValidationUtilsUwU.logActionManagerSetback(req);
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }
        else
        {
            ValidationUtilsUwU.logServerIssue(req);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
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
                ValidationUtilsUwU.isValidString(emailParameter) &&
                ValidationUtilsUwU.isValidString(senhaParameter)
        )
        {
            String email = emailParameter;
            String senha = senhaParameter;

            // Declara e instancia objeto de controle para chamar o método de leitura da classe
            AdminControllerUwU adminControllerUwU = new AdminControllerUwU();

            // Realiza consulta no banco de dados com os parâmetros passados
            ResultSet rs = adminDAO.selecionarAdminsParaLogin(email, senha);

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

                        // Especifica o e-mail do usuário que está acessando
                        req.setAttribute("user-email", email);

                        // Altera o status do login do usuário, para sinalizar que ele está ativo
                        adminDAO.setAdminActive(email);

                        // Define uma mensagem para informar que foi possível fazer login
                        ValidationUtilsUwU.logSuccessfulLogin(req);

                        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
                        adminControllerUwU.readAdmin(req, resp);
                    }
                    else
                    {
                        ValidationUtilsUwU.logUserNotFound(req);
                    }
                }
                catch (SQLException sqle)
                {
                    // Imprime a exceção no console
                    sqle.printStackTrace();

                    // Erro no banco de dados
                    ValidationUtilsUwU.logDatabaseIssue(req);
                }
            }
            else
            {
                // Erro no banco de dados
                ValidationUtilsUwU.logDatabaseIssue(req);
            }
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }
    }

    private void logout(HttpServletRequest req) throws ServletException, IOException
    {
        // Recupera parâmetro da requisão e o amarzena na variável correspondente
        String emailParameter = req.getParameter("email");

        if (ValidationUtilsUwU.isValidString(emailParameter))
        {
            String email = emailParameter;

            // Altera o status do login do usuário para inativo
            adminDAO.setAdminInactive(email);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }
    }
}
