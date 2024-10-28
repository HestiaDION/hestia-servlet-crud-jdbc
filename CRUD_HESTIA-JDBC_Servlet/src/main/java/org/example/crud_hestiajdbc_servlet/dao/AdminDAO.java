package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.Conexao;
import org.example.crud_hestiajdbc_servlet.model.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AdminDAO extends Conexao {
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int adicionarAdmin(Admin admin)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("INSERT INTO Admin (cNome, cEmail, cSenha) VALUES (?, ?, ?)");
            pstmt.setString(1, admin.getcNome());
            pstmt.setString(2, admin.getcEmail());
            pstmt.setString(3, admin.getcSenha());

            // Executa a instrução e guarda as linhas afetadas
            int linhasAfetadas = pstmt.executeUpdate();

            return linhasAfetadas;
        }
        catch (SQLException sqle)
        {
            // Imprime a exceção no console
            sqle.printStackTrace();

            // Retorna -1 se ocorrer algum erro
            return -1;
        }
        finally
        {
            desconectar();
        }
    }

//    DEFINIÇÃO DOS MÉTODOS DE CONSULTA NO BANCO DE DADOS
    public ResultSet selecionarTodosAdmins()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha FROM Admin");

            // Executa a instrução e guarda as linhas retornadas
            rs = pstmt.executeQuery();
        }
        catch (SQLException sqle)
        {
            // Imprime a exceção no console
            sqle.printStackTrace();

            // Atribuí um nulo para indentificação da exceção
            rs = null;
        }
        finally
        {
            desconectar();

            return rs;
        }
    }

    public ResultSet selecionarAdminsPorId(UUID uId)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha FROM Admin WHERE uId = ?");
            pstmt.setObject(1, uId);

            // Executa a instrução e guarda as linhas retornadas
            rs = pstmt.executeQuery();
        }
        catch (SQLException sqle)
        {
            // Imprime a exceção no console
            sqle.printStackTrace();

            // Atribuí um nulo para indentificação da exceção
            rs = null;
        }
        finally
        {
            desconectar();

            return rs;
        }
    }

    public ResultSet selecionarAdminsPorNome(String cNome)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha FROM Admin WHERE cNome = ?");
            pstmt.setString(1, cNome);

            // Executa a instrução e guarda as linhas retornadas
            rs = pstmt.executeQuery();
        }
        catch (SQLException sqle)
        {
            // Imprime a exceção no console
            sqle.printStackTrace();

            // Atribuí um nulo para indentificação da exceção
            rs = null;
        }
        finally
        {
            desconectar();

            return rs;
        }
    }

    public ResultSet selecionarAdminsPorEmail(String cEmail)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha FROM Admin WHERE cEmail = ?");
            pstmt.setString(1, cEmail);

            // Executa a instrução e guarda as linhas retornadas
            rs = pstmt.executeQuery();
        }
        catch (SQLException sqle)
        {
            // Imprime a exceção no console
            sqle.printStackTrace();

            // Atribuí um nulo para indentificação da exceção
            rs = null;
        }
        finally
        {
            desconectar();

            return rs;
        }
    }

    public ResultSet selecionarAdminsPorTamanhoSenha(int tamanhoSenha)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha FROM Admin WHERE LENGTH(csenha) < ?");
            pstmt.setInt(1, tamanhoSenha);

            // Executa a instrução e guarda as linhas retornadas
            rs = pstmt.executeQuery();
        }
        catch (SQLException sqle)
        {
            // Imprime a exceção no console
            sqle.printStackTrace();

            // Atribuí um nulo para indentificação da exceção
            rs = null;
        }
        finally
        {
            desconectar();

            return rs;
        }
    }

    public  ResultSet selecionarAdminsParaLogin(String cEmail, String cSenha)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha FROM Admin WHERE cEmail = ? AND cSenha = ?");
            pstmt.setString(1, cEmail);
            pstmt.setString(2, cSenha);

            // Executa a instrução e guarda as linhas retornadas
            rs = pstmt.executeQuery();
        }
        catch (SQLException sqle)
        {
            // Imprime a exceção no console
            sqle.printStackTrace();

            // Atribuí um nulo para indentificação da exceção
            rs = null;
        }
        finally
        {
            desconectar();

            return rs;
        }
    }

//    DEFINIÇÃO DOS MÉTODOS DE FUNCTIONS E PROCEDURES NO BANCO DE DADOS
    public UUID acharIdAdmin(String email)
    {
    UUID uuid = null;
    try {
        conectar();

        // Prepara a instrução SQL
        pstmt = conn.prepareStatement("SELECT FN_Admin_Id(?)");
        pstmt.setString(1, email);

        // Executa a instrução e guarda as linhas retornadas
        rs = pstmt.executeQuery();

        // Extrai o UUID da primeira linha, se existir
        if (rs.next()) {
            uuid = (UUID) rs.getObject(1);
        }
    } catch (SQLException sqle) {
        // Imprime a exceção no console
        sqle.printStackTrace();
    } finally {
        desconectar();
    }
    return uuid;
}

//    DEFINIÇÃO DO MÉTODO DE ATUALIZAÇÃO NO BANCO DE DADOS
    public int atualizarAdmin(Admin admin)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("UPDATE Admin SET cNome = ?, cEmail = ?, cSenha = ? WHERE uId = ?");
            pstmt.setString(1, admin.getcNome());
            pstmt.setString(2, admin.getcEmail());
            pstmt.setString(3, admin.getcSenha());
            pstmt.setObject(4, admin.getuId());

            // Executa a instrução e guarda as linhas afetadas
            int linhasAfetadas = pstmt.executeUpdate();

            return linhasAfetadas;
        }
        catch (SQLException sqle)
        {
            // Imprime a exceção no console
            sqle.printStackTrace();

            // Retorna -1 se ocorrer algum erro
            return -1;
        }
        finally
        {
            desconectar();
        }
    }

//    DEFINIÇÃO DO MÉTODO DE REMOÇÃO NO BANCO DE DADOS
    public int removerAdmin(UUID uId)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("DELETE FROM Admin WHERE uId = ?");
            pstmt.setObject(1, uId);

            // Executa a instrução e guarda as linhas afetadas
            int linhasAfetadas = pstmt.executeUpdate();

            return linhasAfetadas;
        }
        catch (SQLException sqle)
        {
            // Imprime a exceção no console
            sqle.printStackTrace();

            // Retorna -1 se ocorrer algum erro
            return -1;
        }
        finally
        {
            desconectar();
        }
    }
}
