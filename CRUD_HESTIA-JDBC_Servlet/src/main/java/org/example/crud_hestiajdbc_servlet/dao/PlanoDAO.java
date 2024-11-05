package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.DatabaseConnection;
import org.example.crud_hestiajdbc_servlet.model.Plano;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlanoDAO extends DatabaseConnection
{
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int addPlano(Plano plano)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("INSERT INTO Plano (cNome, cTipoUsuario, nValor, cDescricao) VALUES (?, ?, ?, ?)");
                pstmt.setString(1, plano.getcNome());
                pstmt.setString(2, plano.getcTipoUsuario());
                pstmt.setDouble(3, plano.getnValor());
                pstmt.setString(4, plano.getcDescricao());

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
    public ResultSet getAllPlanos()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuario, nValor, cDescricao FROM Plano");

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

    public ResultSet getPlanoById(UUID uId)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuario, nValor, cDescricao FROM Plano WHERE uId = ?");
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

    public ResultSet getPlanoByNome(String cNome)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuario, nValor, cDescricao FROM Plano WHERE cNome = ?");
                pstmt.setString(1, cNome);

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

    public ResultSet getPlanoByTipoUsuario(String cTipoUsuario)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuario, nValor, cDescricao FROM Plano WHERE cTipoUsuario = ?");
                pstmt.setString(1, cTipoUsuario);

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

    public ResultSet getPlanoByValorAscending()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuario, nValor, cDescricao FROM Plano ORDER BY nValor");

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

    public ResultSet getPlanoByValorDescending()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuario, nValor, cDescricao FROM Plano ORDER BY nValor DESC");

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

//    DEFINIÇÃO DOS MÉTODOS DE FUNCTIONS E PROCEDURES NO BANCO DE DADOS
    public UUID getPlanoId(String nmPlao)
    {
        UUID uuid = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT FN_Plano_Id(?)");
                pstmt.setString(1, nmPlao);

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
    public int updatePlano(Plano plano)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("UPDATE Plano SET cNome = ?, cTipoUsuario = ?, nValor = ?, cDescricao = ? WHERE uId = ?");
                pstmt.setString(1, plano.getcNome());
                pstmt.setString(2, plano.getcTipoUsuario());
                pstmt.setDouble(3, plano.getnValor());
                pstmt.setString(4, plano.getcDescricao());
                pstmt.setObject(5, plano.getuId());

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
    public int removePlano(UUID uId)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("DELETE FROM Plano WHERE uId = ?");
                pstmt.setObject(1, uId);

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
}
