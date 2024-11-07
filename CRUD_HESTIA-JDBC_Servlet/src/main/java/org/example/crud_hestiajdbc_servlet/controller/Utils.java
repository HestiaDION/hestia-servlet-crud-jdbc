package org.example.crud_hestiajdbc_servlet.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils
{
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    public static boolean isValidPhoto(Part file)
    {
        try
        {
            // Tenta transformar o arquivo em fluxo de dados
            InputStream fileContent = file.getInputStream();

            // Tenta transformar fluxo em vetor de bytes
            toPhotoByteArray(fileContent);

            // Retorna true se der tudo certo
            return true;
        }
        catch (IOException ioe)
        {
            // Retorna false se não for possível converter
            return false;
        }
    }

//    DEFINIÇÃO DOS MÉTODOS DE CRIPTOGRAFIA
    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance(System.getenv("algoritmo_hash"));
        byte[] mensagemDigest = algorithm.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder hashString = new StringBuilder();
        for (byte b : mensagemDigest) {
            hashString.append(String.format("%02X", 0xFF & b));
        }
        return hashString.toString();
    }

//    public boolean checkPassword(String password, String bankPassword) throws NoSuchAlgorithmException {
//        String passwordComparation = encryptPassword(password);
//        if (passwordComparation.equals(bankPassword)){
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

//    DEFINIÇÃO DOS MÉTODOS DE CONVERSÃO
    public static LocalDate toLocalDate(String value) throws DateTimeException
    {
        // Declaração de formatador com o formato brasileiro
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Retorna a data como objeto
        return LocalDate.parse(value, formatter);
    }

    public static byte[] toPhotoByteArray(InputStream value) throws IOException
    {
        // Instancia uma classe que permite gravar dados em um lista de bytes
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Cria um vetor que tem capacidade de 1024 bytes
        byte[] buffer = new byte[1024];

        // Vai armazernar quantos bytes foram lidos, em cada execução
        int bytesRead;

        // Lê os bytes do fluxo até que não tenha mais informações para serem lidas
        while ((bytesRead = value.read(buffer)) != -1)
        {
            // Grava os dados na lista de bytes, partindo do zero e indo até os bytes lidos
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        // Transoforma essa lista em um vetor de bytes
        return byteArrayOutputStream.toByteArray();
    }

    public static List<String[]> toAdminStringList(ResultSet resultSet)
    {
        // Criamos uma lista para guardar cada registro da tabela
        List<String[]> listAdmin = new ArrayList<>();

        // Recupera os valores do ResultSet e reconstroi o objeto como um vetor de Strings
        try
        {
            while (resultSet.next())
            {
                String[] registerAdmin = new String[4];

                // Usamos o método da classe string para pegar o valor do objeto e transformar em String, até se algum for null/
                registerAdmin[0] = String.valueOf(resultSet.getObject("uId"));
                registerAdmin[1] = String.valueOf(resultSet.getString("cNome"));
                registerAdmin[2] = String.valueOf(resultSet.getString("cEmail"));
                registerAdmin[3] = String.valueOf(resultSet.getString("cSenha"));
//                registerAdmin[3] = "*****";

                // Adiciona o vetor de Strings na lista
                listAdmin.add(registerAdmin);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        // Retornamos a lista
        return listAdmin;
    }

    public static List<String[]> toBoostStringList(ResultSet resultSet)
    {
        // Criamos uma lista para guardar cada registro da tabela
        List<String[]> boostList = new ArrayList<>();

        // Recupera os valores do ResultSet e reconstroi o objeto como um vetor de Strings
        try
        {
            while (resultSet.next())
            {
                String[] boostRegister = new String[5];

                // Usamos o método da classe string para pegar o valor do objeto e transformar em String, até se algum for null/
                boostRegister[0] = String.valueOf(resultSet.getObject("uId"));
                boostRegister[1] = String.valueOf(resultSet.getString("cNmBoost"));
                boostRegister[2] = String.valueOf(resultSet.getDouble("nValor"));
                boostRegister[3] = String.valueOf(resultSet.getDouble("nPctBoost"));
                boostRegister[4] = String.valueOf(resultSet.getString("cDescricao"));

                // Adiciona o vetor de Strings na lista
                boostList.add(boostRegister);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        // Retornamos a lista
        return boostList;
    }

    public static List<String[]> toFiltroStringList(ResultSet resultSet)
    {
        // Criamos uma lista para guardar cada registro da tabela
        List<String[]> filtroList = new ArrayList<>();

        // Recupera os valores do ResultSet e reconstroi o objeto como um vetor de Strings
        try
        {
            while (resultSet.next())
            {
                String[] filtroRegister = new String[3];

                // Usamos o método da classe string para pegar o valor do objeto e transformar em String, até se algum for null
                filtroRegister[0] = String.valueOf(resultSet.getObject("uId")) ;
                filtroRegister[1] = String.valueOf(resultSet.getString("cNome"));
                filtroRegister[2] = String.valueOf(resultSet.getString("cCategoria"));

                // Adiciona o vetor de Strings na lista
                filtroList.add(filtroRegister);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        // Retornamos a lista
        return filtroList;
    }

    public static List<String[]> toPagamentoStringList(ResultSet resultSet)
    {
        // Criamos uma lista para
        // guardar cada registro da tabela
        List<String[]> pagamentoList = new ArrayList<>();

        // Recupera os valores do ResultSet e reconstroi o objeto como um vetor de Strings
        try
        {
            while (resultSet.next())
            {
                String[] pagamentoRegister = new String[8];

                // Usamos o método da classe string para pegar o valor do objeto e transformar em String, até se algum for null
                pagamentoRegister[0] = String.valueOf(resultSet.getObject("uId")) ;
                pagamentoRegister[1] = String.valueOf(resultSet.getString("cAtivo"));
                pagamentoRegister[2] = String.valueOf(resultSet.getDate("dDtFim"));
                pagamentoRegister[3] = String.valueOf(resultSet.getDouble("nPctDesconto"));
                pagamentoRegister[4] = String.valueOf(resultSet.getDouble("nTotal"));
                pagamentoRegister[5] = String.valueOf(resultSet.getString("cNmPlano"));
                if (String.valueOf(resultSet.getString("cEmailUniversitario")).equals("null")){
                    pagamentoRegister[6] = String.valueOf(resultSet.getString("cEmailAnunciante"));
                    pagamentoRegister[7] = "Anunciante";
                }
                else if(String.valueOf(resultSet.getString("cEmailAnunciante")).equals("null"))
                {
                    pagamentoRegister[6] = String.valueOf(resultSet.getString("cEmailUniversitario"));
                    pagamentoRegister[7] = "Universitario";
                }

                // Adiciona o vetor de Strings na lista
                pagamentoList.add(pagamentoRegister);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        // Retornamos a lista
        return pagamentoList;
    }

    public static List<String[]> toPlanoStringList(ResultSet resultSet)
    {
        // Criamos uma lista para guardar cada registro da tabela
        List<String[]> planoList = new ArrayList<>();

        // Recupera os valores do ResultSet e reconstroi o objeto como um vetor de Strings
        try
        {
            while (resultSet.next())
            {
                String[] planoRegister = new String[5];

                // Usamos o método da classe string para pegar o valor do objeto e transformar em String, até se algum for null
                planoRegister[0] = String.valueOf(resultSet.getObject("uId"));
                planoRegister[1] = String.valueOf(resultSet.getString("cNome"));
                planoRegister[2] = String.valueOf(resultSet.getString("cTipoUsuario"));
                planoRegister[3] = String.valueOf(resultSet.getDouble("nValor"));
                planoRegister[4] = String.valueOf(resultSet.getString("cDescricao"));

                // Adiciona o vetor de Strings na lista
                planoList.add(planoRegister);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        // Retornamos a lista
        return planoList;
    }

    public static List<String[]> toPlano_vantagemStringList(ResultSet resultSet)
    {
        // Criamos uma lista para guardar cada registro da tabela
        List<String[]> planoVantagemList = new ArrayList<>();

        // Recupera os valores do ResultSet e reconstroi o objeto como um vetor de Strings
        try
        {
            while (resultSet.next())
            {
                String[] planoVantagemRegister = new String[4];

                // Usamos o método da classe string para pegar o valor do objeto e transformar em String, até se algum for null
                planoVantagemRegister[0] = String.valueOf(resultSet.getObject("uId"));
                planoVantagemRegister[1] = String.valueOf(resultSet.getString("cVantagem"));
                planoVantagemRegister[2] = String.valueOf(resultSet.getString("cAtivo"));
                planoVantagemRegister[3] = String.valueOf(resultSet.getString("cNmPlano"));

                // Adiciona o vetor de Strings na lista
                planoVantagemList.add(planoVantagemRegister);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        // Retornamos a lista
        return planoVantagemList;
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

//    MÉTODOS DE LOGGING PARA O FRONT
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
        req.setAttribute("log", "O registro foi removido com sucesso.");
    }

    public static void logSuccessfulLogin(HttpServletRequest req)
    {
        req.setAttribute("success", true);
        req.setAttribute("log", "O login foi realizado com sucesso.");
    }

    public static void logUserNotFound(HttpServletRequest req)
    {
        req.setAttribute("success", false);
        req.setAttribute("log", "Essas credenciais são inválidas.");
    }
}
