package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.Conexao;
import org.example.crud_hestiajdbc_servlet.model.Boost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class BoostDAO extends Conexao {
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int adicionarBoost(Boost boost)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("INSERT INTO Boost (cNmBoost, nValor, nPctBoost, cDescricao) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, boost.getcNmBoost());
            pstmt.setDouble(2, boost.getnValor());
            pstmt.setDouble(3, boost.getnPctBoost());
            pstmt.setString(4,boost.getcDescricao());

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

    public ResultSet selecionarBoostsPorId(UUID uId)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost WHERE uId = ?");
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

    public ResultSet selecionarBoostsPorNome(String cNmBoost)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost WHERE cNmBoost = ?");
            pstmt.setString(1, cNmBoost);

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

    public ResultSet selecionarBoostsPorValorCrescente()
    {
        try
        {
            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost ORDER BY nValor");

            // Executa a instrução e guarda as linhas retornadas
            rs = pstmt.executeQuery();

        }
        catch(SQLException sqle)
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

    public ResultSet selecionarBoostsPorValorDecrescente()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost ORDER BY nValor DESC");

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

    public ResultSet selecionarBoostsPorPctBoostCrescente()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost ORDER BY nPctBoost");

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

    public ResultSet selecionarBoostsPorPctBoostDecrescente()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost ORDER BY nPctBoost DESC");

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
    public UUID acharIdBoost(String nmBoost)
    {
        UUID uuid = null;
        try {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT FN_Boost_Id(?)");
            pstmt.setString(1, nmBoost);

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
    public int atualizarBoost(Boost boost)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("UPDATE Boost SET cNmBoost = ?, nValor = ?, nPctBoost = ?, cDescricao = ? WHERE uId = ?");
            pstmt.setString(1, boost.getcNmBoost());
            pstmt.setDouble(2, boost.getnValor());
            pstmt.setDouble(3, boost.getnPctBoost());
            pstmt.setString(4,boost.getcDescricao());
            pstmt.setObject(5, boost.getuId());

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
    public int removerBoost(UUID uId)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("DELETE FROM Boost WHERE uId = ?");
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
