package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        req.setAttribute("log", "O registro foi salvo com sucesso.");
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
