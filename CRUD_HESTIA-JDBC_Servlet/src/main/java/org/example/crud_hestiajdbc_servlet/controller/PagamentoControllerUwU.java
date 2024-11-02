package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.UUID;

import org.example.crud_hestiajdbc_servlet.dao.PagamentoDAO;
import org.example.crud_hestiajdbc_servlet.model.Pagamento;

@WebServlet(name = "pagamento", value = "/pagamento")
public class PagamentoControllerUwU extends HttpServlet {
//    DECLARAÇÃO E INSTANCIAÇÃO DE OBJETO ESTÁTICO PARA MEDIAR A INTERAÇÃO COM O BANCO DE DADOS
    PagamentoDAO pagamentoDAO = new PagamentoDAO();

//    DEFINIÇÃO DOS MÉTODOS GET E POST PARA GERENCIAR AS AÇÕES DENTRO DO BANCO DE DADOS
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Espefica com a classe do objeto que está sendo enviado
        req.setAttribute("table-identifier", "pagamento");

        if (ValidationUtilsUwU.isValidString(action))
        {
            if (action.equals("read"))
            {
                readPagamento(req, resp);
            }
            else
            {
                ValidationUtilsUwU.logActionManagerSetback(req);
                req.getRequestDispatcher("pages/PagamentoUwU.jsp").forward(req, resp);
            }
        }
        else
        {
            ValidationUtilsUwU.logServerIssue(req);
            req.getRequestDispatcher("pages/PagamentoUwU.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Espefica com a classe do objeto que está sendo enviado
        req.setAttribute("table-identifier", "pagamento");

        // Faz a validação do atributo
        if (ValidationUtilsUwU.isValidString(action))
        {
            switch (action)
            {
                case "create":
                    createPagamento(req, resp);
                    break;

                case "update":
                    updatePagamento(req, resp);
                    break;

                case "delete":
                    deletePagamento(req, resp);
                    break;

                default:
                    ValidationUtilsUwU.logActionManagerSetback(req);
                    req.getRequestDispatcher("pages/PagamentoUwU.jsp").forward(req, resp);
            }
        }
        else
        {
            ValidationUtilsUwU.logServerIssue(req);
            req.getRequestDispatcher("pages/PagamentoUwU.jsp").forward(req, resp);
        }
    }

//    DEFINIÇÃO DOS MÉTODOS DE INTERAÇÃO COM O BANCO DE DADOS
    private void createPagamento(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String ativoParameter              = req.getParameter("cAtivo");
        String dataParameter               = req.getParameter("dDtFim");
        String porcentagemParameter        = req.getParameter("nPctDesconto");
        String totalParameter              = req.getParameter("nTotal");
        String userAnuncianteParameter     = req.getParameter("cUserAnunciante");
        String emailAnuncianteParameter    = req.getParameter("cEmailAnunciante");
        String nomePlanoParameter          = req.getParameter("cNmPlano");
        String userUniversitarioParameter  = req.getParameter("cUserUniversitario");
        String emailUniversitarioParameter = req.getParameter("cEmailUniversitario");
        String dneUniversitarioParameter   = req.getParameter("cDNEUniversitario");


        // Verifica se os parâmetros retornaram valores válidos
        if
        (
                ValidationUtilsUwU.isValidStringAtivo(ativoParameter)         &&
                ValidationUtilsUwU.isValidDate(dataParameter)                 &&
                ValidationUtilsUwU.isValidPorcentagem(porcentagemParameter)   &&
                ValidationUtilsUwU.isValidDouble(totalParameter)              &&
                ValidationUtilsUwU.isValidString(userAnuncianteParameter)     &&
                ValidationUtilsUwU.isValidString(emailAnuncianteParameter)    &&
                ValidationUtilsUwU.isValidString(nomePlanoParameter)          &&
                ValidationUtilsUwU.isValidString(userUniversitarioParameter)  &&
                ValidationUtilsUwU.isValidString(emailUniversitarioParameter) &&
                ValidationUtilsUwU.isValidString(dneUniversitarioParameter)
        )
        {
            String ativo              = ativoParameter;
            Date data                 = Date.valueOf(ValidationUtilsUwU.toLocalDate(dataParameter));
            Double porcentagem        = Double.parseDouble(porcentagemParameter);
            Double total              = Double.parseDouble(totalParameter);
            String userAnunciante     = userAnuncianteParameter;
            String emailAnunciante    = emailAnuncianteParameter;
            String dnePlano           = nomePlanoParameter;
            String userUniversitario  = userUniversitarioParameter;
            String emailUniversitario = emailUniversitarioParameter;
            String dneUniversitario   = dneUniversitarioParameter;


            Pagamento pagamento = new Pagamento(ativo, data, porcentagem, total, userAnunciante, emailAnunciante, dnePlano, userUniversitario, emailUniversitario, dneUniversitario);

            if (pagamentoDAO.adicionarPagamento(pagamento) > 0)
                ValidationUtilsUwU.logSuccessfulCreation(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readPagamento(req, resp);
    }

    private void readPagamento(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
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
                        list = pagamentoDAO.selecionarPagamentosPorId(codigo);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPagamentoStringList(list));
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

                    if (ValidationUtilsUwU.isValidStringAtivo(ativoParameter))
                    {
                        String ativo = ativoParameter;
                        list = pagamentoDAO.selecionarPagamentosPorAtividade(ativo);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPagamentoStringList(list));
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

                case "dDtFim":
                    // Não recebe ordenação, pois sempre mostra os pagamentos com data de fim futura
                    list = pagamentoDAO.selecionarPagamentosPorDtFimFutura();

                    if (list != null)
                    {
                        req.setAttribute("list", ValidationUtilsUwU.toPagamentoStringList(list));
                        ValidationUtilsUwU.logSuccessfulReading(req);
                    }
                    else
                    {
                        ValidationUtilsUwU.logDatabaseIssue(req);
                    }
                    break;

                case "nPctDesconto":
                    // Não recebe um valor, e sim um char ('>' para crescente ou '<' para decrescente)
                    String ordenacaoPctDescontoParameter = req.getParameter("sort");

                    if
                    (
                            ValidationUtilsUwU.isValidChar(ordenacaoPctDescontoParameter) &&
                            ordenacaoPctDescontoParameter.charAt(0) == '>'                ||
                            ordenacaoPctDescontoParameter.charAt(0) == '<'
                    )
                    {
                        char ordenacaoPctDesconto = ordenacaoPctDescontoParameter.charAt(0);

                        if (ordenacaoPctDesconto == '>')
                            list = pagamentoDAO.selecionarPagamentosPorPctDescontoCrescente();
                        else
                            list = pagamentoDAO.selecionarPagamentosPorPctDescontoDescrescente();

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPagamentoStringList(list));
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

                case "nTotal":
                    // Não recebe um valor, e sim um char ('>' para crescente ou '<' para decrescente)
                    String ordenacaoTotalParameter = req.getParameter("sort");

                    if
                    (
                            ValidationUtilsUwU.isValidChar(ordenacaoTotalParameter) &&
                            ordenacaoTotalParameter.charAt(0) == '>'                ||
                            ordenacaoTotalParameter.charAt(0) == '<'
                    )
                    {
                        char ordenacaoTotal = ordenacaoTotalParameter.charAt(0);

                        if (ordenacaoTotal == '>')
                            list = pagamentoDAO.selecionarPagamentosPorValorTotalCrescente();
                        else
                            list = pagamentoDAO.selecionarPagamentosPorValorTotalDescrescente();

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPagamentoStringList(list));
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

                case "uId_Anunciante":
                    String codigoAnuncianteParameter = req.getParameter("uId_Anunciante");

                    if (ValidationUtilsUwU.isValidUUID(codigoAnuncianteParameter))
                    {
                        UUID codigoAnunciante = UUID.fromString(codigoAnuncianteParameter);
                        list = pagamentoDAO.selecionarPagamentosPorIdAnunciante(codigoAnunciante);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPagamentoStringList(list));
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
                        list = pagamentoDAO.selecionarPagamentosPorIdPlano(codigoPlano);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPagamentoStringList(list));
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

                case "uId_Universitario":
                    String codigoUniversitarioParameter = req.getParameter("uId_Universitario");

                    if (ValidationUtilsUwU.isValidUUID(codigoUniversitarioParameter))
                    {
                        UUID codigoUniversitario = UUID.fromString(codigoUniversitarioParameter);
                        list = pagamentoDAO.selecionarPagamentosPorIdUniversitario(codigoUniversitario);

                        if (list != null)
                        {
                            req.setAttribute("list", ValidationUtilsUwU.toPagamentoStringList(list));
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

            list = pagamentoDAO.selecionarTodosPagamentos();

            if (list != null && action.equals("read"))
            {
                // Quando o método é chamado de forma primária, action é "read"
                req.setAttribute("list", ValidationUtilsUwU.toPagamentoStringList(list));
                ValidationUtilsUwU.logSuccessfulReading(req);
            }
            else if (list != null && !action.equals("read"))
            {
                // Quando o método é chamado por outro método para obter os registros do banco
                req.setAttribute("list", ValidationUtilsUwU.toPagamentoStringList(list));
            }
            else
            {
                // Quando ocorre erro no banco de dados
                ValidationUtilsUwU.logDatabaseIssue(req);
            }
        }

        // Redireciona a requisição e resposta de volta à página de administração
        req.getRequestDispatcher("pages/PagamentoUwU.jsp").forward(req, resp);
    }

    private void updatePagamento (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String codigoParametro              = req.getParameter("uId");
        String ativoParameter               = req.getParameter("cAtivo");
        String dataParameter                = req.getParameter("dDtFim");
        String porcentagemParameter         = req.getParameter("nPctDesconto");
        String totalParameter               = req.getParameter("nTotal");
        String userAnuncianteParameter      = req.getParameter("cUserAnunciante");
        String emailAnuncianteParameter     = req.getParameter("cEmailAnunciante");
        String nomePlanoParameter           = req.getParameter("cNmPlano");
        String userUniversitarioParameter   = req.getParameter("cUserUniversitario");
        String emailUniversitarioParameter  = req.getParameter("cEmailUniversitario");
        String dneUniversitarioParameter    = req.getParameter("cDNEUniversitario");

        // Verifica se os parâmetros têm valores válidos
        if
        (
                ValidationUtilsUwU.isValidUUID(codigoParametro)               &&
                ValidationUtilsUwU.isValidStringAtivo(ativoParameter)         &&
                ValidationUtilsUwU.isValidDate(dataParameter)                 &&
                ValidationUtilsUwU.isValidPorcentagem(porcentagemParameter)   &&
                ValidationUtilsUwU.isValidDouble(totalParameter)              &&
                ValidationUtilsUwU.isValidString(userAnuncianteParameter)     &&
                ValidationUtilsUwU.isValidString(emailAnuncianteParameter)    &&
                ValidationUtilsUwU.isValidString(nomePlanoParameter)          &&
                ValidationUtilsUwU.isValidString(userUniversitarioParameter)  &&
                ValidationUtilsUwU.isValidString(emailUniversitarioParameter) &&
                ValidationUtilsUwU.isValidString(dneUniversitarioParameter)
        )
        {
            UUID codigo              = UUID.fromString(codigoParametro);
            String ativo              = ativoParameter;
            Date data                 = Date.valueOf(ValidationUtilsUwU.toLocalDate(dataParameter));
            Double porcentagem        = Double.parseDouble(porcentagemParameter);
            Double total              = Double.parseDouble(totalParameter);
            String userAnunciante     = userAnuncianteParameter;
            String emailAnunciante    = emailAnuncianteParameter;
            String dnePlano           = nomePlanoParameter;
            String userUniversitario  = userUniversitarioParameter;
            String emailUniversitario = emailUniversitarioParameter;
            String dneUniversitario   = dneUniversitarioParameter;


            Pagamento pagamento = new Pagamento(codigo, ativo, data, porcentagem, total, userAnunciante, emailAnunciante, dnePlano, userUniversitario, emailUniversitario, dneUniversitario);

            if (pagamentoDAO.atualizarPagamento(pagamento) > 0)
                ValidationUtilsUwU.logSuccessfulUpdate(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readPagamento(req, resp);
    }

    private void deletePagamento (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera o parâmetro da requisão e o amarzena
        String codigoParameter = req.getParameter("uId");

        // Verifica se os parâmetros retornaram valores válidos
        if (ValidationUtilsUwU.isValidUUID(codigoParameter))
        {
            UUID codigo = UUID.fromString(codigoParameter);

            if (pagamentoDAO.removerPagamento(codigo) > 0)
                ValidationUtilsUwU.logSuccessfulRemoval(req);
            else
                ValidationUtilsUwU.logDatabaseIssue(req);
        }
        else
        {
            ValidationUtilsUwU.logInputSetback(req);
        }

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readPagamento(req, resp);
    }
}