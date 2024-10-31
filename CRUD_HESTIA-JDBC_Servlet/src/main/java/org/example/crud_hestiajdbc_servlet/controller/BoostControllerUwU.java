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
public class BoostControllerUwU extends HttpServlet
{
//    DECLARAÇÃO E INSTANCIAÇÃO DE OBJETO ESTÁTICO PARA MEDIAR A INTERAÇÃO COM O BANCO DE DADOS
    BoostDAO boostDAO = new BoostDAO();

//    DEFINIÇÃO DOS MÉTODOS GET E POST PARA GERENCIAR AS AÇÕES DENTRO DO BANCO DE DADOS
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getAttribute("action");

        if (ValidationUtilsUwU.isValidString(action))
        {
            if (action.equals("read"))
            {
                readBoost(req, resp);
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
        String action = (String) req.getAttribute("action");

        // Faz a validação do atributo
        if (ValidationUtilsUwU.isValidString(action))
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
                ValidationUtilsUwU.isValidString(nomeParameter)             &&
                ValidationUtilsUwU.isValidDouble(valorParameter)            &&
                ValidationUtilsUwU.isValidPorcentagem(porcentagemParameter) &&
                ValidationUtilsUwU.isValidString(descricaoParameter)
        )
        {
            String nome        = nomeParameter;
            Double valor       = Double.parseDouble(valorParameter);
            Double porcentagem = Double.parseDouble(porcentagemParameter);
            String descricao   = descricaoParameter;
            Boost boost = new Boost(nome, valor, porcentagem, descricao);

            if (boostDAO.adicionarBoost(boost) > 0)
                ValidationUtilsUwU.logSuccessfulCreation(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Redireciona a requisição e resposta de volta à página de administração
        req.getRequestDispatcher("Crud.jsp").forward(req, resp);
    }

    private void readBoost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetro que pode conter ou não filtro para a pesquisa
        String predicate = (String) req.getAttribute("predicate");

        // Declaração de objeto para guardar os registro retornados
        ResultSet list;

        // Verifica se o filtro tem valor ou não
        if (ValidationUtilsUwU.isValidString(predicate))
        {
            switch (predicate)
            {
                case "uId":
                    String codigoParametro = req.getParameter("uId");

                    if (ValidationUtilsUwU.isValidUUID(codigoParametro))
                    {
                        UUID codigo = UUID.fromString(codigoParametro);
                        list = boostDAO.selecionarBoostsPorId(codigo);

                        if (list != null)
                        {
                            req.setAttribute("list", list);
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

                case "cNmBoost":
                    String nomeParameter = req.getParameter("cNmBoost");

                    if (ValidationUtilsUwU.isValidString(nomeParameter))
                    {
                        String nome = nomeParameter;
                        list = boostDAO.selecionarBoostsPorNome(nome);

                        if (list != null)
                        {
                            req.setAttribute("list", list);
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

                case "nValor":
                    // Não recebe um valor, e sim um char ('>' para crescente ou '<' para decrescente)
                    String ordenacaoValorParameter = req.getParameter("sort");

                    if (
                            ValidationUtilsUwU.isValidChar(ordenacaoValorParameter) &&
                            ordenacaoValorParameter.charAt(0) == '>'                ||
                            ordenacaoValorParameter.charAt(0) == '<'
                    )
                    {
                        char ordenacaoValor = ordenacaoValorParameter.charAt(0);

                        if (ordenacaoValor == '>')
                            list = boostDAO.selecionarBoostsPorPctBoostCrescente();
                        else
                            list = boostDAO.selecionarBoostsPorValorDecrescente();

                        if (list != null)
                        {
                            req.setAttribute("list", list);
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

                case "nPctBoost":
                    // Não recebe um valor, e sim um char ('>' para crescente ou '<' para decrescente)
                    String ordenacaoPctParameter = req.getParameter("sort");

                    if (
                            ValidationUtilsUwU.isValidChar(ordenacaoPctParameter) &&
                            ordenacaoPctParameter.charAt(0) == '>'                ||
                            ordenacaoPctParameter.charAt(0) == '<'
                    )
                    {
                        char ordenacaoPct = ordenacaoPctParameter.charAt(0);

                        if (ordenacaoPct == '>')
                            list = boostDAO.selecionarBoostsPorPctBoostCrescente();
                        else
                            list = boostDAO.selecionarBoostsPorPctBoostDecrescente();

                        if (list != null)
                        {
                            req.setAttribute("list", list);
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
            list = boostDAO.selecionarTodosBoosts();

            if (list != null)
            {
                req.setAttribute("list", list);
                ValidationUtilsUwU.logSuccessfulReading(req);
            }
            else
            {
                ValidationUtilsUwU.logDatabaseIssue(req);
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
                ValidationUtilsUwU.isValidUUID(codigoParameter)             &&
                ValidationUtilsUwU.isValidString(nomeParameter)             &&
                ValidationUtilsUwU.isValidDouble(valorParameter)            &&
                ValidationUtilsUwU.isValidPorcentagem(porcentagemParameter) &&
                ValidationUtilsUwU.isValidString(descricaoParameter)
        )
        {
            UUID codigo        = UUID.fromString(codigoParameter);
            String nome        = nomeParameter;
            Double valor       = Double.parseDouble(valorParameter);
            Double porcentagem = Double.parseDouble(porcentagemParameter);
            String descricao   = descricaoParameter;
            Boost boost = new Boost(codigo, nome, valor, porcentagem,descricao);

            if (boostDAO.atualizarBoost(boost) > 0)
                ValidationUtilsUwU.logSuccessfulUpdate(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Redireciona a requisição e resposta de volta à página de administração
        req.getRequestDispatcher("Crud.jsp").forward(req, resp);
    }

    private void deleteBoost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera o parâmetro da requisão e o amarzena
        String codigoParameter = req.getParameter("uId");

        // Verifica se os parâmetros retornaram valores válidos
        if (ValidationUtilsUwU.isValidUUID(codigoParameter))
        {
            UUID codigo = UUID.fromString(codigoParameter);

            if (boostDAO.removerBoost(codigo) > 0)
                ValidationUtilsUwU.logSuccessfulRemoval(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Redireciona a requisição e resposta de volta à página de administração
        req.getRequestDispatcher("Crud.jsp").forward(req, resp);
    }
}
