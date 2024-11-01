package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.UUID;

import org.example.crud_hestiajdbc_servlet.dao.Plano_vantagemDAO;
import org.example.crud_hestiajdbc_servlet.model.Plano_vantagem;

@WebServlet(name = "plano_vantagem", value = "/plano_vantagem")
public class Plano_vantagemControllerUwU extends HttpServlet
{
//    DECLARAÇÃO E INSTANCIAÇÃO DE OBJETO ESTÁTICO PARA MEDIAR A INTERAÇÃO COM O BANCO DE DADOS
    Plano_vantagemDAO planoVantagemDAO = new Plano_vantagemDAO();

//    DEFINIÇÃO DOS MÉTODOS GET E POST PARA GERENCIAR AS AÇÕES DENTRO DO BANCO DE DADOS
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Espefica com a classe do objeto que está sendo enviado, que é um agregado de plano
        req.setAttribute("aggregate-table-identifier", "plano-vantagem");

        if (ValidationUtilsUwU.isValidString(action))
        {
            if (action.equals("read"))
            {
                readPlano_vantagem(req, resp);
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

        // Espefica com a classe do objeto que está sendo enviado, que é um agregado de plano
        req.setAttribute("aggregate-table-identifier", "plano-vantagem");

        // Faz a validação do atributo
        if (ValidationUtilsUwU.isValidString(action))
        {
            switch (action)
            {
                case "create":
                    createPlano_vantagem(req, resp);
                    break;

                case "update":
                    updatePlano_vantagem(req, resp);
                    break;

                case "delete":
                    deletePlano_vantagem(req, resp);
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
    private void createPlano_vantagem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String vantagemParameter    = req.getParameter("cVantagem");
        String ativoParameter       = req.getParameter("cAtivo");
        String codigoPlanoParameter = req.getParameter("uId_Plano");
    
        // Verifica se os parâmetros retornaram valores válidos
        if
        (
                ValidationUtilsUwU.isValidString(vantagemParameter) &&
                ValidationUtilsUwU.isValidCharAtivo(ativoParameter) &&
                ValidationUtilsUwU.isValidUUID(codigoPlanoParameter)
        )
        {
            String vantagem  = vantagemParameter;
            char ativo       = ativoParameter.charAt(0);
            UUID codigoPlano = UUID.fromString(codigoPlanoParameter);
            Plano_vantagem planoVantagem = new Plano_vantagem(vantagem, ativo, codigoPlano);

            if (planoVantagemDAO.adicionarPlanoVantagem(planoVantagem) > 0)
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

    private void readPlano_vantagem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
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
                        list = planoVantagemDAO.selecionarVantagensPlanoPorId(codigo);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPlano_vantagemStringList(list));
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

                case "cAtivo":
                    String ativoParameter = req.getParameter("cAtivo");

                    if (ValidationUtilsUwU.isValidCharAtivo(ativoParameter))
                    {
                        String ativo = ativoParameter;
                        list = planoVantagemDAO.selecionarVantagensPlanoPorAtividade(ativo);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPlano_vantagemStringList(list));
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

                case "uId_Plano":
                    String codigoPlanoParameter = req.getParameter("uId_Plano");

                    if (ValidationUtilsUwU.isValidUUID(codigoPlanoParameter))
                    {
                        UUID codigoPlano = UUID.fromString(codigoPlanoParameter);
                        list = planoVantagemDAO.selecionarVantagensPlanoPorIdPlano(codigoPlano);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPlano_vantagemStringList(list));
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
            list = planoVantagemDAO.selecionarTodasVantagensPlano();

            if (list != null)
            {
                req.setAttribute("list", ValidationUtilsUwU.toPlano_vantagemStringList(list));
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

    private void updatePlano_vantagem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes

        String codigoParameter      = req.getParameter("uId");
        String vantagemParameter    = req.getParameter("cVantagem");
        String ativoParameter       = req.getParameter("cAtivo");
        String codigoPlanoParameter = req.getParameter("uId_Plano");

        // Verifica se os parâmetros têm valores válidos
        if
        (
                ValidationUtilsUwU.isValidUUID(codigoParameter)      &&
                ValidationUtilsUwU.isValidString(vantagemParameter)  &&
                ValidationUtilsUwU.isValidCharAtivo(ativoParameter)  &&
                ValidationUtilsUwU.isValidUUID(codigoPlanoParameter)
        )
        {
            UUID codigo      = UUID.fromString(codigoParameter);
            String vantagem  = vantagemParameter;
            char ativo       = ativoParameter.charAt(0);
            UUID codigoPlano = UUID.fromString(codigoPlanoParameter);
            Plano_vantagem planoVantagem = new Plano_vantagem(codigo, vantagem, ativo, codigoPlano);

            if (planoVantagemDAO.atualizarPlanoVantagem(planoVantagem) > 0)
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

    private void deletePlano_vantagem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera o parâmetro da requisão e o amarzena
        String codigoParameter = req.getParameter("uId");

        // Verifica se os parâmetros retornaram valores válidos
        if (ValidationUtilsUwU.isValidUUID(codigoParameter))
        {
            UUID codigo = UUID.fromString(codigoParameter);

            if (planoVantagemDAO.removerPlanoVantagem(codigo) > 0)
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
