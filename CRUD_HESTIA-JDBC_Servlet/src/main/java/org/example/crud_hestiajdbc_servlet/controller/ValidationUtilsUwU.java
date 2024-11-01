package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.crud_hestiajdbc_servlet.model.*;

import javax.xml.transform.Result;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ValidationUtilsUwU
{
    public static void main(String[] args) {
        if (isValidUUID("kfasjaçksdfçakfg"))
        {
            System.out.println("EA");
        }
        else
        {
            System.out.println("dfskpfspkdmfpsamd´f");
        }
    }

//    MÉTODOS DE VALIDAÇÃO E TRATAMENTO DE TIPO DE DADOS
    public static boolean isValidString(String string)
    {
        return !(string == null || string.trim().isEmpty());
    }

    public static boolean isValidChar(String string)
    {
        return isValidInteger(string) && string.length() == 1;
    }

    public static boolean isValidInteger(String value)
    {
        // Retorna falso se a String for inválida
        if (!isValidString(value))
        {
            return false;
        }

        // Retorna quanto ao sucesso da conversão
        try
        {
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
    }

    public static boolean isValidDouble(String value)
    {
        // Retorna falso se a String for inválida
        if (!isValidString(value))
        {
            return false;
        }

        // Retorna quanto ao sucesso da conversão
        try
        {
            Double.parseDouble(value);
            return true;
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
    }

    public static boolean isValidUUID(String value)
    {
        // Retorna falso se a String for inválida
        if (!isValidString(value))
        {
            return false;
        }

        // Retorna quanto ao sucesso da conversão
        try
        {
            UUID.fromString(value);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean isValidDate(String value)
    {
        // Declaração de formatador com o formato brasileiro
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Retorna falso se a String for inválida
        if (!isValidString(value))
        {
            return false;
        }

        // Retorna quanto o sucesso da conversão
        try
        {
            LocalDate.parse(value, formatter);
            return true;
        }
        catch (DateTimeException dte)
        {
            return false;
        }
    }

    public static LocalDate toLocalDate(String value) throws DateTimeException
    {
        // Declaração de formatador com o formato brasileiro
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Retorna a data como objeto
        return LocalDate.parse(value, formatter);
    }

    public static List toAdminList(ResultSet resultSet)
    {
        List<Admin> adminList = new ArrayList<>();

        // Cria um novo objeto Admin e define os campos com base nas colunas do ResultSet
        try
        {
            while (resultSet.next())
            {
                Admin admin = new Admin();
                admin.setuId   ((UUID) resultSet.getObject("uId"));
                admin.setcNome (resultSet.getString("cNome"));
                admin.setcEmail(resultSet.getString("cEmail"));
                admin.setcSenha(resultSet.getString("cSenha"));

                // Adiciona o objeto na lista
                adminList.add(admin);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return adminList;
    }

    public static List toBoostList(ResultSet resultSet)
    {
        List<Boost> boostList = new ArrayList<>();

        // Cria um novo objeto Admin e define os campos com base nas colunas do ResultSet
        try
        {
            while (resultSet.next())
            {
                Boost boost = new Boost();
                boost.setuId       ((UUID) resultSet.getObject("uId"));
                boost.setcNmBoost  (resultSet.getString("cNmBoost"));
                boost.setnValor    (resultSet.getDouble("nValor"));
                boost.setnPctBoost (resultSet.getDouble("nPctBoost"));
                boost.setcDescricao(resultSet.getString("cDescricao"));

                // Adiciona o objeto na lista
                boostList.add(boost);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return boostList;
    }

    public static List toFiltroList(ResultSet resultSet)
    {
        List<Filtro> filtroList = new ArrayList<>();

        // Cria um novo objeto Admin e define os campos com base nas colunas do ResultSet
        try
        {
            while (resultSet.next())
            {
                Filtro filtro = new Filtro();
                filtro.setuId       ((UUID) resultSet.getObject("uId"));
                filtro.setcNome     (resultSet.getString("cNome"));
                filtro.setcCategoria(resultSet.getString("cCategoria"));

                // Adiciona o objeto na lista
                filtroList.add(filtro);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return filtroList;
    }

    public static List toPagamentoList(ResultSet resultSet)
    {
        List<Pagamento> pagamentoList = new ArrayList<>();

        // Cria um novo objeto Admin e define os campos com base nas colunas do ResultSet
        try
        {
            while (resultSet.next())
            {
                Pagamento pagamento = new Pagamento();
                pagamento.setuId                ((UUID) resultSet.getObject("uId"));
                pagamento.setcAtivo             (resultSet.getString("cAtivo"));
                pagamento.setdDtFim             (resultSet.getDate("dDtFim"));
                pagamento.setnPctDesconto       (resultSet.getDouble("nPctDesconto"));
                pagamento.setnTotal             (resultSet.getDouble("nTotal"));
                pagamento.setcUserAnunciante    (resultSet.getString("cUserAnunciante"));
                pagamento.setcEmailAnunciante   (resultSet.getString("cEmailAnunciante"));
                pagamento.setcNmPlano           (resultSet.getString("cNmPlano"));
                pagamento.setcUserUniversitario (resultSet.getString("cUserUniversitario"));
                pagamento.setcEmailAnunciante   (resultSet.getString("cEmailAnunciante"));
                pagamento.setcDNEUniversitario  (resultSet.getString("cDNEUniversitario"));

                // Adiciona o objeto na lista
                pagamentoList.add(pagamento);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return pagamentoList;
    }

    public static List toPlanoList(ResultSet resultSet)
    {
        List<Plano> planoList = new ArrayList<>();

        // Cria um novo objeto Admin e define os campos com base nas colunas do ResultSet
        try
        {
            while (resultSet.next())
            {
                Plano plano = new Plano();
                plano.setuId((UUID) resultSet.getObject("uId"));
                plano.setcNome(resultSet.getString("cNome"));
                plano.setnValor(resultSet.getDouble("nValor"));
                plano.setcDescricao(resultSet.getString("cDescricao"));

                // Adiciona o objeto na lista
                planoList.add(plano);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return planoList;
    }

    public static List toPlano_vantagemList(ResultSet resultSet)
    {
        List<Plano_vantagem> plano_vantagemList = new ArrayList<>();

        // Cria um novo objeto Admin e define os campos com base nas colunas do ResultSet
        try
        {
            while (resultSet.next())
            {
                Plano_vantagem plano_vantagem = new Plano_vantagem();
                plano_vantagem.setuId      ((UUID) resultSet.getObject("uId"));
                plano_vantagem.setcVantagem(resultSet.getString("cVantagem"));
                plano_vantagem.setcAtivo   (resultSet.getString("cAtivo").charAt(0));
                plano_vantagem.setuId_Plano((UUID) resultSet.getObject("uId_Plano"));

                // Adiciona o objeto na lista
                plano_vantagemList.add(plano_vantagem);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return plano_vantagemList;
    }

    //    MÉTODOS DE VALIDAÇÃO E TRATAMENTO DE DADOS DO NEGÓCIO
    public static boolean isValidPorcentagem(String value)
    {
        return isValidDouble(value) && Double.parseDouble(value) <= 100 && 0 <= Double.parseDouble(value);
    }

    public static boolean isValidStringAtivo(String string)
    {
        return isValidString(string) && string.equals("1") || string.equals("0") || string.equals("-1");
    }

    public static boolean isValidCharAtivo(String string)
    {
        return isValidString(string) && string.equals("1") || string.equals("0");
    }

    /*
    * Admin
        private String cNome;  // (VARCHAR(100))
        private String cEmail; // (VARCHAR(266))
        private String cSenha; // (VARCHAR(100))
    *
    * Boost
        private String cNmBoost;   // (VARCHAR(50))
        private double nValor;     // (DECIMAL(10,2))
        private double nPctBoost;  // (DECIMAL(10,1))
        private String cDescricao; // (TEXT) 65.535
    *
    * Filtro
        private String cNome;      // (VARCHAR(100))
        private String cCategoria; // (VARCHAR(100))
    *
    * Pagamento
        private String cAtivo;          // (CHAR(2))
        private Date dDtFim;            // (DATE)
        private double nPctDesconto;    // (DECIMAL(10,1))
        private double nTotal;          // (DECIMAL(10,2))
        private UUID uId_Anunciante;    // (UUID)
        private UUID uId_Plano;         // (UUID)
        private UUID uId_Universitario; // (UUID)
    *
    * Plano
        private String cNome;      // (VARCHAR(100))
        private double nValor;     // (DECIMAL(10,2))
        private String cDescricao; // (VARCHAR(MAX)) 65.535
    *
    * Plano_vantagem
        private String cVantagem; // (VARCHAR(100))
        private char cAtivo;      // (CHAR(1))
        private UUID uId_Plano;   // (UUID)
    *
    * */

//    MÉTODOS DE LOGGING
    public static void logServerIssue(HttpServletRequest req)
    {
        req.setAttribute("success", false);
        req.setAttribute("log", "Ocorreu um erro no servidor.");
    }

    public static void logActionManagerSetback(HttpServletRequest req)
    {
        req.setAttribute("success", false);
        req.setAttribute("log", "Houve um erro interno no gerenciamento de ações.");
    }

    public static void logDatabaseIssue(HttpServletRequest req)
    {
        req.setAttribute("success", false);
        req.setAttribute("log", "Ocorreu um erro no banco de dados.");
    }

    public static void logInputSetback(HttpServletRequest req)
    {
        req.setAttribute("success", false);
        req.setAttribute("log", "Os valores passados são inválidos.");
    }

    public static void logSuccessfulCreation(HttpServletRequest req)
    {
        req.setAttribute("success", true);
        req.setAttribute("log", "O registro foi salvo com sucesso.");
    }

    public static void logSuccessfulReading(HttpServletRequest req)
    {
        req.setAttribute("success", true);
        req.setAttribute("log", "A busca foi realizada com sucesso.");
    }

    public static void logSuccessfulUpdate(HttpServletRequest req)
    {
        req.setAttribute("success", true);
        req.setAttribute("log", "O registro foi atulizado com sucesso.");
    }

    public static void logSuccessfulRemoval(HttpServletRequest req)
    {
        req.setAttribute("success", true);
        req.setAttribute("log", "O registro foi removido com sucesso");
    }
}
