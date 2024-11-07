package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.UUID;

import org.example.crud_hestiajdbc_servlet.dao.BoostDAO;
import org.example.crud_hestiajdbc_servlet.model.Boost;

@WebServlet(name = "boost", value = "/boost")
public class BoostController extends HttpServlet
{
//    DECLARAÇÃO E INSTANCIAÇÃO DE OBJETO ESTÁTICO PARA MEDIAR A INTERAÇÃO COM O BANCO DE DADOS
    BoostDAO boostDAO = new BoostDAO();

//    DEFINIÇÃO DOS MÉTODOS GET E POST PARA GERENCIAR AS AÇÕES DENTRO DO BANCO DE DADOS
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Espefica com a classe do objeto que está sendo enviado
        req.setAttribute("table-identifier", "boost");

        if (Utils.isValidString(action))
        {
            if (action.equals("read"))
            {
                readBoost(req, resp);
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
        req.setAttribute("table-identifier", "boost");

        // Faz a validação do atributo
        if (Utils.isValidString(action))
        {
            switch (action)
            {
                case "create":
                    createBoost(req, resp);
                    break;

                case "update":
                    updateBoost(req, resp);
                    break;

                case "delete":
                    deleteBoost(req, resp);
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
    private void createBoost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String nomeParameter        = req.getParameter("cNmBoost");
        String valorParameter       = req.getParameter("nValor");
        String porcentagemParameter = req.getParameter("nPctBoost");
        String descricaoParameter   = req.getParameter("cDescricao");

        // Verifica se os parâmetros retornaram valores válidos
        if
        (
                Utils.isValidString(nomeParameter)             &&
                Utils.isValidDouble(valorParameter)            &&
                Utils.isValidPorcentagem(porcentagemParameter) &&
                Utils.isValidString(descricaoParameter)
        )
        {
            String nome        = nomeParameter;
            Double valor       = Double.parseDouble(valorParameter);
            Double porcentagem = Double.parseDouble(porcentagemParameter);
            String descricao   = descricaoParameter;
            Boost boost = new Boost(nome, valor, porcentagem, descricao);

            if (boostDAO.addBoost(boost) > 0)
                Utils.logSuccessfulCreation(req);
            else
                Utils.logDatabaseIssue(req);
        }
        else
        {
            Utils.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readBoost(req, resp);
    }

    private void readBoost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetro que pode conter ou não filtro para a pesquisa
        String predicate = (String) req.getParameter("predicate");

        // Declaração de objeto para guardar os registro retornados
        ResultSet list;

        // Verifica se o filtro tem valor ou não
        if (Utils.isValidString(predicate))
        {
            switch (predicate)
            {
                case "uId":
                    String codigoParametro = req.getParameter("uId");

                    if (Utils.isValidUUID(codigoParametro))
                    {
                        UUID codigo = UUID.fromString(codigoParametro);
                        list = boostDAO.getBoostById(codigo);

                        if (list != null)
                        {
                            req.setAttribute("list", Utils.toBoostStringList(list));
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

                case "cNmBoost":
                    String nomeParameter = req.getParameter("cNmBoost");

                    if (Utils.isValidString(nomeParameter))
                    {
                        String nome = nomeParameter;
                        list = boostDAO.getBoostByName(nome);

                        if (list != null)
                        {
                            req.setAttribute("list", Utils.toBoostStringList(list));
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

                case "nValor":
                    String valorParameter = req.getParameter("nValor");

                    if (
                            Utils.isValidDouble(valorParameter)
                    )
                    {
                        // Guarda a váriavel em uma String, por ser o tipo necessário para a busca
                        String valor = valorParameter;
                        list = boostDAO.getBoostByValor(valor);

                        if (list != null)
                        {
                            req.setAttribute("list", Utils.toBoostStringList(list));
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

                case "nPctBoost":
                    String pctBoostParameter = req.getParameter("nPctBoost");

                    if (
                            Utils.isValidDouble(pctBoostParameter)
                    )
                    {
                        // Guarda a váriavel em uma String, por ser o tipo necessário para a busca
                        String pctBoost = pctBoostParameter;
                        list = boostDAO.getBoostsByPctBoost(pctBoost);

                        if (list != null)
                        {
                            req.setAttribute("list", Utils.toBoostStringList(list));
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

                default:
                    Utils.logActionManagerSetback(req);
            }
        }
        else
        {
            // Recebe a ação que deve ser ralizada como atributo da requisição
            String action = (String) req.getParameter("action");

            list = boostDAO.getAllBoosts();

            if (list != null && action.equals("read"))
            {
                // Quando o método é chamado de forma primária, action é "read"
                req.setAttribute("list", Utils.toBoostStringList(list));
                Utils.logSuccessfulReading(req);
            }
            else if (list != null && !action.equals("read"))
            {
                // Quando o método é chamado por outro método para obter os registros do banco
                req.setAttribute("list", Utils.toBoostStringList(list));
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

    private void updateBoost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String codigoParameter      = req.getParameter("uId");
        String nomeParameter        = req.getParameter("cNmBoost");
        String valorParameter       = req.getParameter("nValor");
        String porcentagemParameter = req.getParameter("nPctBoost");
        String descricaoParameter   = req.getParameter("cDescricao");

        // Verifica se os parâmetros têm valores válidos
        if
        (
                Utils.isValidUUID(codigoParameter)             &&
                Utils.isValidString(nomeParameter)             &&
                Utils.isValidDouble(valorParameter)            &&
                Utils.isValidPorcentagem(porcentagemParameter) &&
                Utils.isValidString(descricaoParameter)
        )
        {
            UUID codigo        = UUID.fromString(codigoParameter);
            String nome        = nomeParameter;
            Double valor       = Double.parseDouble(valorParameter);
            Double porcentagem = Double.parseDouble(porcentagemParameter);
            String descricao   = descricaoParameter;
            Boost boost = new Boost(codigo, nome, valor, porcentagem,descricao);

            if (boostDAO.updateBoost(boost) > 0)
                Utils.logSuccessfulUpdate(req);
            else
                Utils.logDatabaseIssue(req);
        }
        else
        {
            Utils.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readBoost(req, resp);
    }

    private void deleteBoost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera o parâmetro da requisão e o amarzena
        String codigoParameter = req.getParameter("uId");

        // Verifica se os parâmetros retornaram valores válidos
        if (Utils.isValidUUID(codigoParameter))
        {
            UUID codigo = UUID.fromString(codigoParameter);

            if (boostDAO.removeBoost(codigo) > 0)
                Utils.logSuccessfulRemoval(req);
            else
                Utils.logDatabaseIssue(req);
        }
        else
        {
            Utils.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readBoost(req, resp);
    }
}
