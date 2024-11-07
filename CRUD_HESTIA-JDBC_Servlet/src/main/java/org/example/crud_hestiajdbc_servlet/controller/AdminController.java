package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.UUID;

import jakarta.servlet.http.Part;
import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;
import org.example.crud_hestiajdbc_servlet.model.Admin;

@WebServlet(name = "admin", value = "/admin")
// Permite trabalhar com arquivos
@MultipartConfig
public class AdminController extends HttpServlet
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

        if (Utils.isValidString(action))
        {
            if (action.equals("read"))
            {
                readAdmin(req, resp);
            }
            else
            {
                Utils.logActionManagerSetback(req);
                req.getRequestDispatcher("Crud.jsp").forward(req, resp);
            }
        }
        else
        {
            Utils.logServerIssue(req);
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
        if (Utils.isValidString(action))
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
                    Utils.logActionManagerSetback(req);
                    req.getRequestDispatcher("Crud.jsp").forward(req, resp);
            }
        }
        else
        {
            Utils.logServerIssue(req);
            req.getRequestDispatcher("Crud.jsp").forward(req, resp);
        }
    }

//    DEFINIÇÃO DOS MÉTODOS DE INTERAÇÃO COM O BANCO DE DADOS
    private void createAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String nomeParameter  = req.getParameter("cNome");
        String emailParameter = req.getParameter("cEmail");
        String senhaParameter  = req.getParameter("cSenha");
        Part imagemParameter  = req.getPart("cFoto");

        // Verifica se os parâmetros retornaram valores válidos
        if
        (
                Utils.isValidString(nomeParameter)  &&
                Utils.isValidString(emailParameter) &&
                Utils.isValidString(senhaParameter)  &&
                Utils.isValidPhoto(imagemParameter)
        )
        {
            String nome  = nomeParameter;
            String email = emailParameter;
            String senha = senhaParameter;
            try {
                senha = Utils.encryptPassword(senhaParameter);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            String imagem = Base64.getEncoder().encodeToString(Utils.toPhotoByteArray(imagemParameter.getInputStream()));
            Admin admin = new Admin(nome, email, imagem, senha);

            if (adminDAO.addAdmin(admin) > 0)
                Utils.logSuccessfulCreation(req);
            else
                Utils.logDatabaseIssue(req);
        }
        else
        {
            Utils.logInputSetback(req);
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
        if (Utils.isValidString(predicate))
        {
            switch (predicate)
            {
                case "uId":
                    String codigoParameter = req.getParameter("uId");

                    if (Utils.isValidUUID(codigoParameter))
                    {
                        UUID codigo = UUID.fromString(codigoParameter);
                        list = adminDAO.getAdminById(codigo);

                        if (list != null)
                        {
                            req.setAttribute("list", Utils.toAdminStringList(list));
                            Utils.logSuccessfulReading(req);
                        }
                        else
                        {
                            Utils.logDatabaseIssue(req);
                        }
                    }
                    else
                    {
                        Utils.logInputSetback(req);
                    }
                    break;

                case "cNome":
                    String nomeParameter = req.getParameter("cNome");

                    if (Utils.isValidString(nomeParameter))
                    {
                        String nome = nomeParameter;
                        list = adminDAO.getAdminByName(nome);

                        if (list != null)
                        {
                            req.setAttribute("list", Utils.toAdminStringList(list));
                            Utils.logSuccessfulReading(req);
                        }
                        else
                        {
                            Utils.logDatabaseIssue(req);
                        }
                    }
                    else
                    {
                        Utils.logInputSetback(req);
                    }
                    break;

                case "cEmail":
                    String emailParameter = req.getParameter("cEmail");

                    if (Utils.isValidString(emailParameter))
                    {
                        String email = emailParameter;
                        list = adminDAO.getAdminByEmail(email);

                        if (list != null)
                        {
                            req.setAttribute("list", Utils.toAdminStringList(list));
                            Utils.logSuccessfulReading(req);
                        }
                        else
                        {
                            Utils.logDatabaseIssue(req);
                        }
                    }
                    else
                    {
                        Utils.logInputSetback(req);
                    }
                    break;

//                case "cSenha":
//                    // Não recebe uma senha especifica, e sim um tamanho de senha (Integer)
//                    String tamanhoSenhaParameter = req.getParameter("length");
//
//                    if (Utils.isValidInteger(tamanhoSenhaParameter))
//                    {
//                        int tamanhoSenha = Integer.parseInt(tamanhoSenhaParameter);
//                        list = adminDAO.getAdminsByPasswordSize(tamanhoSenha);
//
//                        if (list != null)
//                        {
//                            req.setAttribute("list", Utils.toAdminStringList(list));
//                            Utils.logSuccessfulReading(req);
//                        }
//                        else
//                        {
//                            Utils.logDatabaseIssue(req);
//                        }
//                    }
//                    else
//                    {
//                        Utils.logInputSetback(req);
//                    }
//                    break;

                default:
                    Utils.logActionManagerSetback(req);
            }
        }
        else
        {
            // Recebe a ação que deve ser ralizada como atributo da requisição
            String action = (String) req.getParameter("action");

            list = adminDAO.getAllAdmins();

            if (list != null && action.equals("read"))
            {
                // Quando o método é chamado de forma primária, action é "read"
                req.setAttribute("list", Utils.toAdminStringList(list));
                Utils.logSuccessfulReading(req);
            }
            else if (list != null && !action.equals("read"))
            {
                // Quando o método é chamado por outro método para obter os registros do banco
                req.setAttribute("list", Utils.toAdminStringList(list));
            }
            else
            {
                // Quando ocorre erro no banco de dados
                Utils.logDatabaseIssue(req);
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
        Part imagemParameter   = req.getPart("cFoto");
        String senhaParameter  = req.getParameter("cSenha");

        // Verifica se os parâmetros têm valores válidos
        if
        (
                Utils.isValidUUID(codigoParameter)  &&
                Utils.isValidString(nomeParameter)  &&
                Utils.isValidString(emailParamter)  &&
                Utils.isValidPhoto(imagemParameter) &&
                Utils.isValidString(senhaParameter)
        )
        {
            UUID codigo   = UUID.fromString(codigoParameter);
            String nome   = nomeParameter;
            String email  = emailParamter;
            String imagem = Base64.getEncoder().encodeToString(Utils.toPhotoByteArray(imagemParameter.getInputStream()));
            String senha = senhaParameter;
            try {
                senha = Utils.encryptPassword(senhaParameter);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            Admin admin = new Admin(codigo, nome, email, imagem, senha);

            if (adminDAO.updateAdmin(admin) > 0)
                Utils.logSuccessfulUpdate(req);
            else
                Utils.logDatabaseIssue(req);
        }
        else
        {
            Utils.logInputSetback(req);
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
        (Utils.isValidUUID(codigoParameter))
        {
            UUID codigo = UUID.fromString(codigoParameter);

            if (adminDAO.removeAdmin(codigo) > 0)
                Utils.logSuccessfulRemoval(req);
            else
                Utils.logDatabaseIssue(req);
        }
        else
        {
            Utils.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readAdmin(req, resp);
    }
}
