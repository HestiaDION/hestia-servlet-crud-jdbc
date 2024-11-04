package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.DatabaseConnection;
import org.example.crud_hestiajdbc_servlet.model.Pagamento;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PagamentoDAO extends DatabaseConnection
{
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int addPagamento(Pagamento pagamento)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("INSERT INTO Pagamento (cAtivo, dDtFim, nPctDesconto, nTotal, " +
                        "uId_Anunciante, uId_Plano, uId_Universitario) VALUES (?, DEFAULT, ?, ?, FN_Anunciante_Id(?, ?), FN_Plano_Id(?), Fn_Universitario_Id(?, ?, ?))");
                pstmt.setString(1, pagamento.getcAtivo());
                pstmt.setDouble(2, pagamento.getnPctDesconto());
                pstmt.setDouble(3, pagamento.getnTotal());
                pstmt.setString(4, pagamento.getcUserAnunciante());
                pstmt.setString(5, pagamento.getcEmailAnunciante());
                pstmt.setString(6, pagamento.getcNmPlano());
                pstmt.setString(7, pagamento.getcUserUniversitario());
                pstmt.setString(8, pagamento.getcEmailUniversitario());
                pstmt.setString(9, pagamento.getcDNEUniversitario());

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
    public ResultSet getAllPagamentos()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento");

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

    public ResultSet getPagamentoById(UUID uId)
    {
        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE uId = ?");
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

    public ResultSet getPagamentoByAtividade(String cAtivo)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE cAtivo = ?");
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

    public ResultSet getPagamentoByFutureDtFim()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE dDtFim > CURRENT_DATE");

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

    public ResultSet getPagamentosByPctDescontoAscending()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento ORDER BY nPctDesconto");

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

    public ResultSet getPagamentosByPctDescontoDescending()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento ORDER BY nPctDesconto DESC");

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

    public ResultSet getPagamentosByTotalAscending()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento ORDER BY nTotal");

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

    public ResultSet getPagamentosByTotalDescending()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento ORDER BY nTotal DESC");

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

    public ResultSet getPagamentosByIdAnunciante(UUID uId_Anunciante)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE uId_Anunciante = ?");
                pstmt.setObject(1, uId_Anunciante);

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

    public ResultSet getPagamentoByIdPlano(UUID uId_Plano)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE uId_Plano = ?");
                pstmt.setObject(1, uId_Plano);

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

    public ResultSet getPagamentoByIdUniversitario(UUID uId_Universitario)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE uId_Universitario = ?");
                pstmt.setObject(1, uId_Universitario);

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
    public UUID getPagamentoId(UUID idUsuario, UUID idPlano, Date dtFim)
    {
        UUID uuid = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT FN_Pagamento_Id(?, ?, ?)");
                pstmt.setObject(1, idUsuario);
                pstmt.setObject(2, idPlano);
                pstmt.setDate(3, dtFim);

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
    public int updatePagamento(Pagamento pagamento)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("UPDATE Pagamento SET cAtivo = ?, dDtFim = ?, nPctDesconto = ?, nTotal = ?, uId_Anunciante = FN_Anunciante_Id(?, ?), uId_Plano = FN_Plano_Id(?), uId_Universitario = Fn_Universitario_Id(?, ?, ?) WHERE uId = ?");
                pstmt.setString(1, pagamento.getcAtivo());
                pstmt.setObject(2, pagamento.getdDtFim());
                pstmt.setDouble(3, pagamento.getnPctDesconto());
                pstmt.setDouble(4, pagamento.getnTotal());
                pstmt.setString(5, pagamento.getcUserAnunciante());
                pstmt.setString(6, pagamento.getcEmailAnunciante());
                pstmt.setString(7, pagamento.getcNmPlano());
                pstmt.setString(8, pagamento.getcUserUniversitario());
                pstmt.setString(9, pagamento.getcEmailUniversitario());
                pstmt.setString(10, pagamento.getcDNEUniversitario());
                pstmt.setObject(11, pagamento.getuId());

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
    public int removePagamento(UUID uId)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("DELETE FROM Pagamento WHERE uId = ?");
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
