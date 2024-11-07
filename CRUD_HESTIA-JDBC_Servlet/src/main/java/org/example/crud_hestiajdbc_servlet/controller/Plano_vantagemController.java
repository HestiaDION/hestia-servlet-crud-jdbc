package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

import org.example.crud_hestiajdbc_servlet.dao.Plano_vantagemDAO;
import org.example.crud_hestiajdbc_servlet.model.Plano_vantagem;

@WebServlet(name = "plano_vantagem", value = "/plano_vantagem")
public class Plano_vantagemController extends HttpServlet
{
//    DECLARAÇÃO E INSTANCIAÇÃO DE OBJETO ESTÁTICO PARA MEDIAR A INTERAÇÃO COM O BANCO DE DADOS
    Plano_vantagemDAO planoVantagemDAO = new Plano_vantagemDAO();

//    DEFINIÇÃO DOS MÉTODOS GET E POST PARA GERENCIAR AS AÇÕES DENTRO DO BANCO DE DADOS
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        // Recebe a ação que deve ser ralizada como atributo da requisição
        String action = (String) req.getParameter("action");

        // Espefica com a classe do objeto que está sendo enviado
        req.setAttribute("table-identifier", "plano_vantagem");

        if (Utils.isValidString(action))
        {
            if (action.equals("read"))
            {
                readPlano_vantagem(req, resp);
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
        req.setAttribute("table-identifier", "plano_vantagem");

        // Faz a validação do atributo
        if (Utils.isValidString(action))
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
    private void createPlano_vantagem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes
        String vantagemParameter  = req.getParameter("cVantagem");
        String ativoParameter     = req.getParameter("cAtivo");
        String nomePlanoParameter = req.getParameter("cNmPlano");
    
        // Verifica se os parâmetros retornaram valores válidos
        if
        (
                Utils.isValidString(vantagemParameter)  &&
                Utils.isValidCharAtivo(ativoParameter)  &&
                Utils.isValidString(nomePlanoParameter)
        )
        {
            String vantagem = vantagemParameter;
            char ativo      = ativoParameter.charAt(0);
            String nomePlano  = nomePlanoParameter;
            Plano_vantagem planoVantagem = new Plano_vantagem(vantagem, ativo, nomePlano);

            if (planoVantagemDAO.addPlanoVantagem(planoVantagem) > 0)
            {
                Utils.logSuccessfulCreation(req);

                // Chama o método de leitura, que obtém os registros do banco e responde a requisição com o parâmetro
                readPlano_vantagem(req, resp, nomePlano);

                // Retorna para não chamar o dispacher novamente
                return;
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

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readPlano_vantagem(req, resp);
    }

    private void readPlano_vantagem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
//        // Recupera parâmetro que pode conter ou não filtro para a pesquisa
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
                        list = planoVantagemDAO.getPlanoVantagemById(codigo);

                        if (list != null)
                        {
                            req.setAttribute("list", Utils.toPlano_vantagemStringList(list));
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

                case "cAtivo":
                    String ativoParameter = req.getParameter("cAtivo");

                    if (Utils.isValidCharAtivo(ativoParameter))
                    {
                        String ativo = ativoParameter;
                        list = planoVantagemDAO.getPlanoVantagensByAtivo(ativo);

                        if (list != null)
                        {
                            req.setAttribute("list", Utils.toPlano_vantagemStringList(list));
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

                case "cNmPlano":
                    String nomePlanoParameter = req.getParameter("cNmPlano");

                    if (Utils.isValidString(nomePlanoParameter))
                    {
                        String nomePlano = nomePlanoParameter;
                        list = planoVantagemDAO.getPlanoVantagensByNmPlano(nomePlano);

                        if (list != null)
                        {
                            req.setAttribute("parent-table-name", nomePlano);

                            req.setAttribute("list", Utils.toPlano_vantagemStringList(list));
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

            list = planoVantagemDAO.getAllPlanoVantagens();

            if (list != null && action.equals("read"))
            {
                // Quando o método é chamado de forma primária, action é "read"
                req.setAttribute("list", Utils.toPlano_vantagemStringList(list));
                Utils.logSuccessfulReading(req);
            }
            else if (list != null && !action.equals("read"))
            {
                // Quando o método é chamado por outro método para obter os registros do banco
                req.setAttribute("list", Utils.toPlano_vantagemStringList(list));
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

    // Sobrecarga que retorna as vantagens de mesmo plano
    private void readPlano_vantagem(HttpServletRequest req, HttpServletResponse resp, String nomePlanoParameter) throws ServletException, IOException
    {
        // Verifica se o parâmetro do nome é válido
        if (Utils.isValidString(nomePlanoParameter))
        {
            String nomePlano = nomePlanoParameter;
            ResultSet list = planoVantagemDAO.getPlanoVantagensByNmPlano(nomePlano);

            if (list != null)
            {
                req.setAttribute("parent-table-name", nomePlano);

                req.setAttribute("list", Utils.toPlano_vantagemStringList(list));
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

        // Redireciona a requisição e resposta de volta à página de administração
        req.getRequestDispatcher("Crud.jsp").forward(req, resp);
    }

    private void updatePlano_vantagem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena nas variáveis correspondentes

        String codigoParameter      = req.getParameter("uId");
        String vantagemParameter    = req.getParameter("cVantagem");
        String ativoParameter       = req.getParameter("cAtivo");
        String nomePlanoParameter   = req.getParameter("cNmPlano");

        // Verifica se os parâmetros têm valores válidos
        if
        (
                Utils.isValidUUID(codigoParameter)      &&
                Utils.isValidString(vantagemParameter)  &&
                Utils.isValidCharAtivo(ativoParameter)  &&
                Utils.isValidString(nomePlanoParameter)
        )
        {
            UUID codigo     = UUID.fromString(codigoParameter);
            String vantagem = vantagemParameter;
            char ativo      = ativoParameter.charAt(0);
            String nomePlano = nomePlanoParameter;
            Plano_vantagem planoVantagem = new Plano_vantagem(codigo, vantagem, ativo, nomePlano);

            if (planoVantagemDAO.updatePlanoVantagem(planoVantagem) > 0)
            {
                Utils.logSuccessfulUpdate(req);

                // Chama o método de leitura, que obtém os registros do banco e responde a requisição com o parâmetro
                readPlano_vantagem(req, resp, nomePlano);

                // Retorna para não chamar o dispacher novamente
                return;
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

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readPlano_vantagem(req, resp);
    }

    private void deletePlano_vantagem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Recupera parâmetros da requisão e os amarzena
        String codigoParameter = req.getParameter("uId");
        String nomePlanoParameter = req.getParameter("cNmPlano");

        // Verifica se os parâmetros retornaram valores válidos
        if
        (
                Utils.isValidUUID(codigoParameter)      &&
                Utils.isValidString(nomePlanoParameter)
        )
        {
            UUID codigo = UUID.fromString(codigoParameter);
            String nomePlano = nomePlanoParameter;

            if (planoVantagemDAO.removePlanoVantagem(codigo) > 0)
            {
                Utils.logSuccessfulRemoval(req);

                // Chama o método de leitura, que obtém os registros do banco e responde a requisição com o parâmetro
                readPlano_vantagem(req, resp, nomePlano);

                // Retorna para não chamar o dispacher novamente
                return;
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

        // Chama o método de leitura, que obtém os registros do banco e responde a requisição
        readPlano_vantagem(req, resp);
    }
}
