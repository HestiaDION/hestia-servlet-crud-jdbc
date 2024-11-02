package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.UUID;

import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;
import org.example.crud_hestiajdbc_servlet.model.Admin;

@WebServlet(name = "admin", value = "/admin")
public class AdminControllerUwU extends HttpServlet
{
//    DECLARAÇÃO E INSTANCIAÇÃO DE OBJETO ESTÁTICO PARA MEDIAR A INTERAÇÃO COM O BANCO DE DADOS
    AdminDAO adminDAO = new AdminDAO();

//    DEFINIÇÃO DOS MÉTODOS GET E POST PARA GERENCIAR AS AÇÕES DENTRO DO BANCO DE DADOS
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Espefica com a classe do objeto que está sendo enviado
        req.setAttribute("table-identifier", "admin");

        if (ValidationUtilsUwU.isValidString(action))
        {
            if (action.equals("read"))
            {
                readAdmin(req, resp);
            }
            else
            {
                ValidationUtilsUwU.logActionManagerSetback(req);
                req.getRequestDispatcher("Crud.jsp").forward(req, resp);
            }
        }
        else
        {
            ValidationUtilsUwU.logServerIssue(req);
            req.getRequestDispatcher("Crud.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Espefica com a classe do objeto que está sendo enviado
        req.setAttribute("table-identifier", "admin");

        // Faz a validação do atributo
        if (ValidationUtilsUwU.isValidString(action))
        {
            switch (action)
            {
                case "create":
                    createAdmin(req, resp);
                    break;

                case "update":
                    updateAdmin(req, resp);
                    break;

                case "delete":
                    deleteAdmin(req, resp);
                    break;

                default:
                    ValidationUtilsUwU.logActionManagerSetback(req);
                    req.getRequestDispatcher("Crud.jsp").forward(req, resp);
            }
        }
        else
        {
            ValidationUtilsUwU.logServerIssue(req);
            req.getRequestDispatcher("Crud.jsp").forward(req, resp);
        }
    }

//    DEFINIÇÃO DOS MÉTODOS DE INTERAÇÃO COM O BANCO DE DADOS
    private void createAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String nomeParameter = req.getParameter("cNome");
        String emailParamter = req.getParameter("cEmail");
        String senhaParamter = req.getParameter("cSenha");

        // Verifica se os parâmetros retornaram valores válidos
        if
        (
                ValidationUtilsUwU.isValidString(nomeParameter) &&
                ValidationUtilsUwU.isValidString(emailParamter) &&
                ValidationUtilsUwU.isValidString(senhaParamter)
        )
        {
            String nome  = nomeParameter;
            String email = emailParamter;
            String senha = senhaParamter;
            Admin admin = new Admin(nome, email, senha);

            if (adminDAO.adicionarAdmin(admin) > 0)
                ValidationUtilsUwU.logSuccessfulCreation(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readAdmin(req, resp);
    }

    // O método é público por ser chamado como padrão pela classe de login
    public void readAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetro que pode conter ou não filtro para a pesquisa
        String predicate = req.getParameter("predicate");

        // Declaração de objeto para guardar os registro retornados
        ResultSet list;

        // Verifica se o filtro tem valor ou não
        if (ValidationUtilsUwU.isValidString(predicate))
        {
            switch (predicate)
            {
                case "uId":
                    String codigoParameter = req.getParameter("uId");

                    if (ValidationUtilsUwU.isValidUUID(codigoParameter))
                    {
                        UUID codigo = UUID.fromString(codigoParameter);
                        list = adminDAO.selecionarAdminsPorId(codigo);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toAdminStringList(list));
                            ValidationUtilsUwU.logSuccessfulReading(req);
                        }
                        else
                        {
                            ValidationUtilsUwU.logDatabaseIssue(req);
                        }
                    }
                    else
                    {
                        ValidationUtilsUwU.logInputSetback(req);
                    }
                    break;

                case "cNome":
                    String nomeParameter = req.getParameter("cNome");

                    if (ValidationUtilsUwU.isValidString(nomeParameter))
                    {
                        String nome = nomeParameter;
                        list = adminDAO.selecionarAdminsPorNome(nome);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toAdminStringList(list));
                            ValidationUtilsUwU.logSuccessfulReading(req);
                        }
                        else
                        {
                            ValidationUtilsUwU.logDatabaseIssue(req);
                        }
                    }
                    else
                    {
                        ValidationUtilsUwU.logInputSetback(req);
                    }
                    break;

                case "cEmail":
                    String emailParameter = req.getParameter("cEmail");

                    if (ValidationUtilsUwU.isValidString(emailParameter))
                    {
                        String email = emailParameter;
                        list = adminDAO.selecionarAdminsPorEmail(email);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toAdminStringList(list));
                            ValidationUtilsUwU.logSuccessfulReading(req);
                        }
                        else
                        {
                            ValidationUtilsUwU.logDatabaseIssue(req);
                        }
                    }
                    else
                    {
                        ValidationUtilsUwU.logInputSetback(req);
                    }
                    break;

                case "cSenha":
                    // Não recebe uma senha especifica, e sim um tamanho de senha (Integer)
                    String tamanhoSenhaParameter = req.getParameter("length");

                    if (ValidationUtilsUwU.isValidInteger(tamanhoSenhaParameter))
                    {
                        int tamanhoSenha = Integer.parseInt(tamanhoSenhaParameter);
                        list = adminDAO.selecionarAdminsPorTamanhoSenha(tamanhoSenha);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toAdminStringList(list));
                            ValidationUtilsUwU.logSuccessfulReading(req);
                        }
                        else
                        {
                            ValidationUtilsUwU.logDatabaseIssue(req);
                        }
                    }
                    else
                    {
                        ValidationUtilsUwU.logInputSetback(req);
                    }
                    break;

                default:
                    ValidationUtilsUwU.logActionManagerSetback(req);
            }
        }
        else
        {
            // Recebe a ação que deve ser ralizada como atributo da requisição
            String action = (String) req.getParameter("action");

            list = adminDAO.selecionarTodosAdmins();

            if (list != null && action.equals("read"))
            {
                // Quando o método é chamado de forma primária, action é "read"
                req.setAttribute("list", ValidationUtilsUwU.toAdminStringList(list));
                ValidationUtilsUwU.logSuccessfulReading(req);
            }
            else if (list != null && !action.equals("read"))
            {
                // Quando o método é chamado por outro método para obter os registros do banco
                req.setAttribute("list", ValidationUtilsUwU.toAdminStringList(list));
            }
            else
            {
                // Quando ocorre erro no banco de dados
                ValidationUtilsUwU.logDatabaseIssue(req);
            }
        }

        // Redireciona a requisição e resposta de volta à página de administração
        req.getRequestDispatcher("Crud.jsp").forward(req, resp);
    }

    private void updateAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String codigoParameter = req.getParameter("uId");
        String nomeParameter   = req.getParameter("cNome");
        String emailParamter   = req.getParameter("cEmail");
        String senhaParamter   = req.getParameter("cSenha");

        // Verifica se os parâmetros têm valores válidos
        if
        (
                ValidationUtilsUwU.isValidUUID(codigoParameter) &&
                ValidationUtilsUwU.isValidString(nomeParameter) &&
                ValidationUtilsUwU.isValidString(emailParamter) &&
                ValidationUtilsUwU.isValidString(senhaParamter)
        )
        {
            UUID codigo  = UUID.fromString(codigoParameter);
            String nome  = nomeParameter;
            String email = emailParamter;
            String senha = senhaParamter;
            Admin admin = new Admin(codigo, nome, email, senha);

            if (adminDAO.atualizarAdmin(admin) > 0)
                ValidationUtilsUwU.logSuccessfulUpdate(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readAdmin(req, resp);
    }

    private void deleteAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera o parâmetro da requisão e o amarzena
        String codigoParameter = req.getParameter("uId");

        // Verifica se os parâmetros retornaram valores válidos
        if
        (ValidationUtilsUwU.isValidUUID(codigoParameter))
        {
            UUID codigo = UUID.fromString(codigoParameter);

            if (adminDAO.removerAdmin(codigo) > 0)
                ValidationUtilsUwU.logSuccessfulRemoval(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readAdmin(req, resp);
    }
}
