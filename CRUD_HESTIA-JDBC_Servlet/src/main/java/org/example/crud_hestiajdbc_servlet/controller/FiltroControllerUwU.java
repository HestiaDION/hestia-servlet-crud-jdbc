package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.UUID;

import org.example.crud_hestiajdbc_servlet.dao.FiltroDAO;
import org.example.crud_hestiajdbc_servlet.model.Filtro;

@WebServlet(name = "filtro", value = "/filtro")
public class FiltroControllerUwU extends HttpServlet
{
//    DECLARAÇÃO E INSTANCIAÇÃO DE OBJETO ESTÁTICO PARA MEDIAR A INTERAÇÃO COM O BANCO DE DADOS
    FiltroDAO filtroDAO = new FiltroDAO();

//    DEFINIÇÃO DOS MÉTODOS GET E POST PARA GERENCIAR AS AÇÕES DENTRO DO BANCO DE DADOS
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getAttribute("action");

        if (ValidationUtilsUwU.isValidString(action))
        {
            if (action.equals("read"))
            {
                readFiltro(req, resp);
            }
            else
            {
                ValidationUtilsUwU.logActionManagerSetback(req);
                req.getRequestDispatcher("pages/FiltroUwU.jsp").forward(req, resp);
            }
        }
        else
        {
            ValidationUtilsUwU.logServerIssue(req);
            req.getRequestDispatcher("pages/FiltroUwU.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getAttribute("action");

        // Faz a validação do atributo
        if (ValidationUtilsUwU.isValidString(action))
        {
            switch (action)
            {
                case "create":
                    createFiltro(req, resp);
                    break;

                case "update":
                    updateFiltro(req, resp);
                    break;

                case "delete":
                    deleteFiltro(req, resp);
                    break;

                default:
                    ValidationUtilsUwU.logActionManagerSetback(req);
                    req.getRequestDispatcher("pages/FiltroUwU.jsp").forward(req, resp);
            }
        }
        else
        {
            ValidationUtilsUwU.logServerIssue(req);
            req.getRequestDispatcher("pages/FiltroUwU.jsp").forward(req, resp);
        }
    }

//    DEFINIÇÃO DOS MÉTODOS DE INTERAÇÃO COM O BANCO DE DADOS
    private void createFiltro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String nomeParameter      = req.getParameter("cNome");
        String categoriaParameter = req.getParameter("cCategoria");

        // Verifica se os parâmetros retornaram valores válidos
        if
        (
                ValidationUtilsUwU.isValidString(nomeParameter)   &&
                ValidationUtilsUwU.isValidString(categoriaParameter)
        )
        {
            String nome = nomeParameter;
            String categoria = categoriaParameter;
            Filtro filtro = new Filtro(nome, categoria);

            if (filtroDAO.adicionarFiltro(filtro) > 0)
                ValidationUtilsUwU.logSuccessfulCreation(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Redireciona a requisição e resposta de volta à página de administração
        req.getRequestDispatcher("pages/FiltroUwU.jsp").forward(req, resp);
    }

    private void readFiltro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetro que pode conter ou não filtro para a pesquisa
        String predicate = (String) req.getAttribute("predicate");

        // Declaração de objeto para guardar os registro retornados
        ResultSet list;

        // Verifica se o filtro tem valor ou não
        if (ValidationUtilsUwU.isValidString(predicate)) {
            switch (predicate) {
                case "uId":
                    String codigoParametro = req.getParameter("uId");

                    if (ValidationUtilsUwU.isValidUUID(codigoParametro)) {
                        UUID codigo = UUID.fromString(codigoParametro);
                        list = filtroDAO.selecionarFiltrosPorId(codigo);

                        if (list != null) {
                            req.setAttribute("list", list);
                            ValidationUtilsUwU.logSuccessfulReading(req);
                        } else {
                            ValidationUtilsUwU.logDatabaseIssue(req);
                        }
                    } else {
                        ValidationUtilsUwU.logInputSetback(req);
                    }
                    break;

                case "cNome":
                    String nomeParameter = req.getParameter("cNome");

                    if (ValidationUtilsUwU.isValidString(nomeParameter)) {
                        String nome = nomeParameter;
                        list = filtroDAO.selecionarFiltrosPorNome(nome);

                        if (list != null) {
                            req.setAttribute("list", list);
                            ValidationUtilsUwU.logSuccessfulReading(req);
                        } else {
                            ValidationUtilsUwU.logDatabaseIssue(req);
                        }
                    } else {
                        ValidationUtilsUwU.logInputSetback(req);
                    }
                    break;

                case "cCategoria":
                    String categoriaParameter = req.getParameter("cCategoria");

                    if (ValidationUtilsUwU.isValidString(categoriaParameter)) {
                        String categoria = categoriaParameter;
                        list = filtroDAO.selecionarFiltrosPorCategoria(categoria);

                        if (list != null) {
                            req.setAttribute("list", list);
                            ValidationUtilsUwU.logSuccessfulReading(req);
                        } else {
                            ValidationUtilsUwU.logDatabaseIssue(req);
                        }
                    } else {
                        ValidationUtilsUwU.logInputSetback(req);
                    }
                    break;

                default:
                    ValidationUtilsUwU.logActionManagerSetback(req);
                    break;
            }
        }
        else
        {
            list = filtroDAO.selecionarTodosFiltros();

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
        req.getRequestDispatcher("pages/FiltroUwU.jsp").forward(req, resp);
    }

    private void updateFiltro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String codigoParameter    = req.getParameter("uId");
        String nomeParameter      = req.getParameter("cNome");
        String categoriaParameter = req.getParameter("cCategoria");

        // Verifica se os parâmetros têm valores válidos
        if
        (
                ValidationUtilsUwU.isValidUUID(codigoParameter)   &&
                ValidationUtilsUwU.isValidString(nomeParameter)   &&
                ValidationUtilsUwU.isValidString(categoriaParameter)
        )
        {
            UUID codigo      = UUID.fromString(codigoParameter);
            String nome      = nomeParameter;
            String categoria = categoriaParameter;
            Filtro filtro = new Filtro(codigo, nome, categoria);

            if (filtroDAO.atualizarFiltro(filtro) > 0)
                ValidationUtilsUwU.logSuccessfulUpdate(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Redireciona a requisição e resposta de volta à página de administração
        req.getRequestDispatcher("pages/FiltroUwU.jsp").forward(req, resp);
    }

    private void deleteFiltro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera o parâmetro da requisão e o amarzena
        String codigoParameter = req.getParameter("uId");

        // Verifica se os parâmetros retornaram valores válidos
        if (ValidationUtilsUwU.isValidUUID(codigoParameter))
        {
            UUID codigo = UUID.fromString(codigoParameter);

            if (filtroDAO.removerFiltro(codigo) > 0)
                ValidationUtilsUwU.logSuccessfulRemoval(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Redireciona a requisição e resposta de volta à página de administração
        req.getRequestDispatcher("pages/FiltroUwU.jsp").forward(req, resp);
    }
}