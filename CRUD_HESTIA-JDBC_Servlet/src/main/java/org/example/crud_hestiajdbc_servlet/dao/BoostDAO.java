package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.Conexao;
import org.example.crud_hestiajdbc_servlet.model.Boost;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoostDAO extends Conexao {
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int adicionarBoost(Boost boost)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("INSERT INTO Boost (uId, cNmBoost, nValor, nPctBoost, cDescricao) VALUES (?, ?, ?, ?, ?)");
            pstmt.setObject(1, boost.getuId());
            pstmt.setString(2, boost.getcNmBoost());
            pstmt.setDouble(3, boost.getnValor());
            pstmt.setDouble(4, boost.getnPctBoost());
            pstmt.setString(5,boost.getcDescricao());

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
    public ResultSet selecionarTodosBoosts()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost");

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

    public ResultSet selecionarBoostsPorId(Boost boost)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost WHERE uId = ?");
            pstmt.setObject(1, boost.getuId());

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

    public ResultSet selecionarBoostsPorTipoBoost(Boost boost)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost WHERE cNmBoost = ?");
            pstmt.setString(1, boost.getcNmBoost());

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

    public ResultSet selecionarBoostsPorValor(Boost boost)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost WHERE nValor = ?");
            pstmt.setDouble(1, boost.getnValor());

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

    public ResultSet selecionarBoostsPorPctBoost(Boost boost)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost WHERE nPctBoost = ?");
            pstmt.setDouble(1, boost.getnPctBoost());

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

//    DEFINIÇÃO DO MÉTODO DE ATUALIZAÇÃO NO BANCO DE DADOS
    public int atualizarBoost(Boost boost)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("UPDATE Boost SET cNmBoost = ?, nValor = ?, nPctBoost = ? WHERE uId = ?");
            pstmt.setString(1, boost.getcNmBoost());
            pstmt.setDouble(2, boost.getnValor());
            pstmt.setDouble(3, boost.getnPctBoost());
            pstmt.setObject(4, boost.getuId());

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
    public int removerBoost(Boost boost)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("DELETE FROM Boost WHERE uId = ?");
            pstmt.setObject(1, boost.getuId());

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
