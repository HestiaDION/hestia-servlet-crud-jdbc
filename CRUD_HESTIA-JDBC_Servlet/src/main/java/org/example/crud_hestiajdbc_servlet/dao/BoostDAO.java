package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.DatabaseConnection;
import org.example.crud_hestiajdbc_servlet.model.Boost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BoostDAO extends DatabaseConnection
{
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int addBoost(Boost boost)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("INSERT INTO Boost (cNmBoost, nValor, nPctBoost, cDescricao) VALUES (?, ?, ?, ?)");
                pstmt.setString(1, boost.getcNmBoost());
                pstmt.setDouble(2, boost.getnValor());
                pstmt.setDouble(3, boost.getnPctBoost());
                pstmt.setString(4,boost.getcDescricao());

                // Executa a instrução e guarda as linhas afetadas
                linhasAfetadas = pstmt.executeUpdate();
            }
            catch (SQLException sqle)
            {
                // Imprime a exceção no console
                sqle.printStackTrace();
            }
            finally
            {
                disconnect();
            }
        }

        // Sempre retorna um inteiro, que pode ser -1 caso não seja possível conectar ou ocorra uma exceção
        return linhasAfetadas;
    }

//    DEFINIÇÃO DOS MÉTODOS DE CONSULTA NO BANCO DE DADOS
    public ResultSet getAllBoosts()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost");

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();
            }
            catch (SQLException sqle)
            {
                // Imprime a exceção no console
                sqle.printStackTrace();
            }
            finally
            {
                disconnect();
            }
        }

        // Sempre retorna um ResultSet, que pode ser null caso não seja possível conectar ou ocorra uma exceção
        return rs;
    }

    public ResultSet getBoostById(UUID uId)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost WHERE uId ~* ('^'||?)");
                pstmt.setObject(1, uId);

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();
            }
            catch (SQLException sqle)
            {
                // Imprime a exceção no console
                sqle.printStackTrace();
            }
            finally
            {
                disconnect();
            }
        }

        // Sempre retorna um ResultSet, que pode ser null caso não seja possível conectar ou ocorra uma exceção
        return rs;
    }

    public ResultSet getBoostByName(String cNmBoost)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost WHERE cNmBoost ~* ('^'||?)");
                pstmt.setString(1, cNmBoost);

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();
            }
            catch (SQLException sqle)
            {
                // Imprime a exceção no console
                sqle.printStackTrace();
            }
            finally
            {
                disconnect();
            }
        }

        // Sempre retorna um ResultSet, que pode ser null caso não seja possível conectar ou ocorra uma exceção
        return rs;
    }

    public ResultSet getBoostByValor(String nTotal)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost WHERE nValor::text ~* ('^'||?)");
                pstmt.setString(1, nTotal);

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();

            }
            catch(SQLException sqle)
            {
                // Imprime a exceção no console
                sqle.printStackTrace();
            }
            finally
            {
                disconnect();
            }
        }

        // Sempre retorna um ResultSet, que pode ser null caso não seja possível conectar ou ocorra uma exceção
        return rs;
    }

//    public ResultSet getBoostsByValorDescending()
//    {
//        rs = null;
//
//        if (connect())
//        {
//            try
//            {
//                // Prepara a instrução SQL
//                pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost ORDER BY nValor DESC");
//
//                // Executa a instrução e guarda as linhas retornadas
//                rs = pstmt.executeQuery();
//            }
//            catch (SQLException sqle)
//            {
//                // Imprime a exceção no console
//                sqle.printStackTrace();
//            }
//            finally
//            {
//                disconnect();
//            }
//        }
//
//        // Sempre retorna um ResultSet, que pode ser null caso não seja possível conectar ou ocorra uma exceção
//        return rs;
//    }

    public ResultSet getBoostsByPctBoost(String nPctDesconto)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost WHERE nPctBoost::text ~* ('^'||?)");
                pstmt.setString(1, nPctDesconto);

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();
            }
            catch (SQLException sqle)
            {
                // Imprime a exceção no console
                sqle.printStackTrace();
            }
            finally
            {
                disconnect();
            }
        }

        // Sempre retorna um ResultSet, que pode ser null caso não seja possível conectar ou ocorra uma exceção
        return rs;
    }

//    public ResultSet getBoostByPctBoostDescending()
//    {
//        rs = null;
//
//        if (connect())
//        {
//            try
//            {
//                // Prepara a instrução SQL
//                pstmt = conn.prepareStatement("SELECT uId, cNmBoost, nValor, nPctBoost, cDescricao FROM Boost ORDER BY nPctBoost DESC");
//
//                // Executa a instrução e guarda as linhas retornadas
//                rs = pstmt.executeQuery();
//            }
//            catch (SQLException sqle)
//            {
//                // Imprime a exceção no console
//                sqle.printStackTrace();
//            }
//            finally
//            {
//                disconnect();
//            }
//        }
//
//        // Sempre retorna um ResultSet, que pode ser null caso não seja possível conectar ou ocorra uma exceção
//        return rs;
//    }

//    DEFINIÇÃO DOS MÉTODOS DE FUNCTIONS E PROCEDURES NO BANCO DE DADOS
    public UUID getBoostId(String nmBoost)
    {
        UUID uuid = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT FN_Boost_Id(?)");
                pstmt.setString(1, nmBoost);

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();

                // Extrai o UUID da primeira linha, se existir
                if (rs.next())
                {
                    uuid = (UUID) rs.getObject(1);
                }
            }
            catch (SQLException sqle)
            {
                // Imprime a exceção no console
                sqle.printStackTrace();
            }
            finally
            {
                disconnect();
            }
        }

        // Sempre retorna um UUID, que pode ser null caso não seja possível conectar ou ocorra uma exceção
        return uuid;
    }

//    DEFINIÇÃO DO MÉTODO DE ATUALIZAÇÃO NO BANCO DE DADOS
    public int updateBoost(Boost boost)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("UPDATE Boost SET cNmBoost = ?, nValor = ?, nPctBoost = ?, cDescricao = ? WHERE uId = ?");
                pstmt.setString(1, boost.getcNmBoost());
                pstmt.setDouble(2, boost.getnValor());
                pstmt.setDouble(3, boost.getnPctBoost());
                pstmt.setString(4,boost.getcDescricao());
                pstmt.setObject(5, boost.getuId());

                // Executa a instrução e guarda as linhas afetadas
                linhasAfetadas = pstmt.executeUpdate();
            }
            catch (SQLException sqle)
            {
                // Imprime a exceção no console
                sqle.printStackTrace();
            }
            finally
            {
                disconnect();
            }
        }

        // Sempre retorna um inteiro, que pode ser -1 caso não seja possível conectar ou ocorra uma exceção
        return linhasAfetadas;
    }

//    DEFINIÇÃO DO MÉTODO DE REMOÇÃO NO BANCO DE DADOS
    public int removeBoost(UUID uId)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("SELECT * FROM SP_ExcluirBoost(?)");
                pstmt.setObject(1, uId);

                // Executa a instrução e guarda as linhas afetadas
                linhasAfetadas = pstmt.executeQuery().findColumn("deleted_count");
            }
            catch (SQLException sqle)
            {
                // Imprime a exceção no console
                sqle.printStackTrace();
            }
            finally
            {
                disconnect();
            }
        }

        // Sempre retorna um inteiro, que pode ser -1 caso não seja possível conectar ou ocorra uma exceção
        return linhasAfetadas;
    }
}
