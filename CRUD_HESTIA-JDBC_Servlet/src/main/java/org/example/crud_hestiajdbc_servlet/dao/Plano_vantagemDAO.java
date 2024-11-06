package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.DatabaseConnection;
import org.example.crud_hestiajdbc_servlet.model.Plano_vantagem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Plano_vantagemDAO extends DatabaseConnection
{
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int addPlanoVantagem(Plano_vantagem planoVantagem)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("INSERT INTO Plano_vantagem (cVantagem, cAtivo, uId_Plano) VALUES (?, ? ,FN_Plano_Id(?))");
                pstmt.setString(1, planoVantagem.getcVantagem());
                pstmt.setObject(2, planoVantagem.getcAtivo());
                pstmt.setObject(3, planoVantagem.getcNmPlano());

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
    public ResultSet getAllPlanoVantagens()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT Vw_Plano_vantagem.uId, Vw_Plano_vantagem.cVantagem, Vw_Plano_vantagem.cAtivo, Vw_Plano_vantagem.cNmPlano FROM Vw_Plano_vantagem");

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

    public ResultSet getPlanoVantagemById(UUID uId)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT Vw_Plano_vantagem.uId, Vw_Plano_vantagem.cVantagem, Vw_Plano_vantagem.cAtivo, Vw_Plano_vantagem.cNmPlano FROM Vw_Plano_vantagem WHERE Vw_Plano_vantagem.uId ~* ('^'||?)");
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

    public ResultSet getPlanoVantagensByAtivo(String cAtivo)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT Vw_Plano_vantagem.uId, Vw_Plano_vantagem.cVantagem, Vw_Plano_vantagem.cAtivo, Vw_Plano_vantagem.cNmPlano FROM Vw_Plano_vantagem WHERE Vw_Plano_vantagem.cAtivo ~* ('^'||?)");
                pstmt.setString(1, cAtivo);

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

    public ResultSet getPlanoVantagensByNmPlano(String cNmPlano)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT Vw_Plano_vantagem.uId, Vw_Plano_vantagem.cVantagem, Vw_Plano_vantagem.cAtivo, Vw_Plano_vantagem.cNmPlano FROM Vw_Plano_vantagem WHERE Vw_Plano_vantagem.cNmPlano ~* ('^'||?)");
                pstmt.setObject(1, cNmPlano);

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
    public UUID getPlanoVantagemId(String vantagem, UUID id_Plano)
    {
        UUID uuid = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT FN_Plano_vantagem_Id(?, ?)");
                pstmt.setString(1, vantagem);
                pstmt.setObject(2, id_Plano);

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
    public int updatePlanoVantagem(Plano_vantagem planoVantagem)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("UPDATE Plano_vantagem SET cVantagem = ?, cAtivo = ?, uId_Plano = FN_Plano_Id(?) WHERE uId = ?");
                pstmt.setString(1, planoVantagem.getcVantagem());
                pstmt.setObject(2, planoVantagem.getcAtivo());
                pstmt.setObject(3, planoVantagem.getcNmPlano());
                pstmt.setObject(4, planoVantagem.getuId());

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
    public int removePlanoVantagem(UUID uId)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("DELETE FROM Plano_vantagem WHERE uId = ?");
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
