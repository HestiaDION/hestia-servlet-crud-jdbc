package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.UUID;

import org.example.crud_hestiajdbc_servlet.dao.PlanoDAO;
import org.example.crud_hestiajdbc_servlet.model.Plano;

@WebServlet(name = "plano", value = "/plano")
public class PlanoControllerUwU extends HttpServlet
{
//    DECLARAÇÃO E INSTANCIAÇÃO DE OBJETO ESTÁTICO PARA MEDIAR A INTERAÇÃO COM O BANCO DE DADOS
    PlanoDAO planoDAO = new PlanoDAO();

//    DEFINIÇÃO DOS MÉTODOS GET E POST PARA GERENCIAR AS AÇÕES DENTRO DO BANCO DE DADOS
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Espefica com a classe do objeto que está sendo enviado
        req.setAttribute("table-identifier", "plano");

        if (ValidationUtilsUwU.isValidString(action))
        {
            if (action.equals("read"))
            {
                readPlano(req, resp);
            }
            else
            {
                ValidationUtilsUwU.logActionManagerSetback(req);
                req.getRequestDispatcher("pages/PlanoUwU.jsp").forward(req, resp);
            }
        }
        else
        {
            ValidationUtilsUwU.logServerIssue(req);
            req.getRequestDispatcher("pages/PlanoUwU.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Espefica com a classe do objeto que está sendo enviado
        req.setAttribute("table-identifier", "plano");

        // Faz a validação do atributo
        if (ValidationUtilsUwU.isValidString(action))
        {
            switch (action)
            {
                case "create":
                    createPlano(req, resp);
                    break;

                case "update":
                    updatePlano(req, resp);
                    break;

                case "delete":
                    deletePlano(req, resp);
                    break;

                default:
                    ValidationUtilsUwU.logActionManagerSetback(req);
                    req.getRequestDispatcher("pages/PlanoUwU.jsp").forward(req, resp);
            }
        }
        else
        {
            ValidationUtilsUwU.logServerIssue(req);
            req.getRequestDispatcher("pages/PlanoUwU.jsp").forward(req, resp);
        }
    }

//    DEFINIÇÃO DOS MÉTODOS DE INTERAÇÃO COM O BANCO DE DADOS
    private void createPlano(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String nomeParameter        = req.getParameter("cNome");
        String tipoUsuarioParameter = req.getParameter("cTipoUsuario");
        String valorParameter       = req.getParameter("nValor");
        String descricaoParameter   = req.getParameter("cDescricao");
    
        // Verifica se os parâmetros retornaram valores válidos
        if
        (
                ValidationUtilsUwU.isValidString(nomeParameter)        &&
                ValidationUtilsUwU.isValidString(tipoUsuarioParameter) &&
                ValidationUtilsUwU.isValidDouble(valorParameter)       &&
                ValidationUtilsUwU.isValidString(descricaoParameter)
        )
        {
            String nome        = nomeParameter;
            String tipoUsuario = tipoUsuarioParameter;
            Double valor       = Double.parseDouble(valorParameter);
            String descricao   = descricaoParameter;
            Plano plano = new Plano(nome, tipoUsuario, valor, descricao);

            if (planoDAO.adicionarPlano(plano) > 0)
                ValidationUtilsUwU.logSuccessfulCreation(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readPlano(req, resp);
    }
    
    private void readPlano(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetro que pode conter ou não filtro para a pesquisa
        String predicate = (String) req.getParameter("predicate");
        
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
                        list = planoDAO.selecionarPlanosPorId(codigo);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPlanoStringList(list));
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
                        list = planoDAO.selecionarPlanosPorNome(nome);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPlanoStringList(list));
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

                    if
                    (
                            ValidationUtilsUwU.isValidChar(ordenacaoValorParameter) &&
                            ordenacaoValorParameter.charAt(0) == '>' ||
                            ordenacaoValorParameter.charAt(0) == '<'
                    )
                    {
                        char ordenacaoValor = ordenacaoValorParameter.charAt(0);

                        if (ordenacaoValor == '>')
                            list = planoDAO.selecionarPlanosPorValorCrescente();
                        else
                            list = planoDAO.selecionarPlanosPorValorDecrescente();

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPlanoStringList(list));
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

            list = planoDAO.selecionarTodosPlanos();
    
            if (list != null)
            {
                // Quando o método é chamado de forma primária, action é "read"
                req.setAttribute("list", ValidationUtilsUwU.toPlanoStringList(list));
                ValidationUtilsUwU.logSuccessfulReading(req);
            }
            else if (list != null && !action.equals("read"))
            {
                // Quando o método é chamado por outro método para obter os registros do banco
                req.setAttribute("list", ValidationUtilsUwU.toPlanoStringList(list));
            }
            else
            {
                // Quando ocorre erro no banco de dados
                ValidationUtilsUwU.logDatabaseIssue(req);
            }
        }
        
        // Redireciona a requisição e resposta de volta à página de administração
        req.getRequestDispatcher("pages/PlanoUwU.jsp").forward(req, resp);
    }
    
    private void updatePlano(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String codigoParameter      = req.getParameter("uId");
        String nomeParameter        = req.getParameter("cNome");
        String tipoUsuarioParameter = req.getParameter("cTipoUsuario");
        String valorParameter       = req.getParameter("nValor");
        String descricaoParameter   = req.getParameter("cDescricao");

        // Verifica se os parâmetros retornaram valores válidos
        if
        (
                ValidationUtilsUwU.isValidUUID(codigoParameter)        &&
                ValidationUtilsUwU.isValidString(nomeParameter)        &&
                ValidationUtilsUwU.isValidString(tipoUsuarioParameter) &&
                ValidationUtilsUwU.isValidDouble(valorParameter)       &&
                ValidationUtilsUwU.isValidString(descricaoParameter)
        )
        {
        UUID   codigo      = UUID.fromString(codigoParameter);
        String nome        = nomeParameter;
        String tipoUsuario = tipoUsuarioParameter;
        Double valor       = Double.parseDouble(valorParameter);
        String descricao   = descricaoParameter;
        Plano plano = new Plano(codigo, nome, tipoUsuario, valor, descricao);

        if (planoDAO.atualizarPlano(plano) > 0)
            ValidationUtilsUwU.logSuccessfulUpdate(req);
        else
            ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readPlano(req, resp);
    }
    
    private void deletePlano(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera o parâmetro da requisão e o amarzena
        String codigoParameter = req.getParameter("uId");
    
        // Verifica se os parâmetros retornaram valores válidos
        if (ValidationUtilsUwU.isValidUUID(codigoParameter))
        {
            UUID codigo = UUID.fromString(codigoParameter);
    
            if (planoDAO.removerPlano(codigo) > 0)
                ValidationUtilsUwU.logSuccessfulRemoval(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readPlano(req, resp);
    }
}
