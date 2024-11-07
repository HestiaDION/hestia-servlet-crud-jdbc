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
                if (pagamento.getcEmailUniversitario() == null)
                {
                    // Prepara a instrução SQL e define os seus argumentos
                    pstmt = conn.prepareStatement("INSERT INTO Pagamento (cAtivo, dDtFim, nPctDesconto, nTotal, " +
                            "uId_Anunciante, uId_Plano, uId_Universitario) VALUES (?, ?, ?, ?, FN_Anunciante_Id(NULL, ?), FN_Plano_Id(?), NULL)");
                    pstmt.setString(1, pagamento.getcAtivo());
                    pstmt.setDate(2, pagamento.getdDtFim());
                    pstmt.setDouble(3, pagamento.getnPctDesconto());
                    pstmt.setDouble(4, pagamento.getnTotal());
                    pstmt.setString(5, pagamento.getcEmailAnunciante());
                    pstmt.setString(6, pagamento.getcNmPlano());
                }
                else
                {
                    // Prepara a instrução SQL e define os seus argumentos
                    pstmt = conn.prepareStatement("INSERT INTO Pagamento (cAtivo, dDtFim, nPctDesconto, nTotal, " +
                            "uId_Anunciante, uId_Plano, uId_Universitario) VALUES (?, ?, ?, ?, NULL, FN_Plano_Id(?), Fn_Universitario_Id(NULL, ?, NULL))");
                    pstmt.setString(1, pagamento.getcAtivo());
                    pstmt.setDate(2, pagamento.getdDtFim());
                    pstmt.setDouble(3, pagamento.getnPctDesconto());
                    pstmt.setDouble(4, pagamento.getnTotal());
                    pstmt.setString(5, pagamento.getcNmPlano());
                    pstmt.setString(6, pagamento.getcEmailUniversitario());
                }


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
                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal" +
                        ", Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento");

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
                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal\" +\n" +
                        "                        \", Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento WHERE Vw_Pagamento.uId ~* ('^'||?)");
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
                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal\" +\n" +
                        "                        \", Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento WHERE Vw_Pagamento.cAtivo ~* ('^'||?)");
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
                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal\" +\n" +
                        "                        \", Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento WHERE Vw_Pagamento.dDtFim > CURRENT_DATE");

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

    public ResultSet getPagamentosByPctDesconto(String nPctDesconto)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal\" +\n" +
                        "                        \", Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento WHERE Vw_Pagamento.nPctDesconto::text ~* ('^'||?)");
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

//    public ResultSet getPagamentosByPctDescontoDescending()
//    {
//        rs = null;
//
//        if (connect())
//        {
//            try
//            {
//                // Prepara a instrução SQL
//                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal\" +\n" +
//                        "                        \", Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento ORDER BY Vw_Pagamento.nPctDesconto DESC");
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

    public ResultSet getPagamentosByTotal(String nTotal)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal, Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento WHERE Vw_Pagamento.nTotal::text ~* ('^'||?)");
                pstmt.setString(1, nTotal);

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

//    public ResultSet getPagamentosByTotalDescending()
//    {
//        rs = null;
//
//        if (connect())
//        {
//            try
//            {
//                // Prepara a instrução SQL
//                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal\" +\n" +
//                        "                        \", Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento ORDER BY Vw_Pagamento.nTotal DESC");
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

//    public ResultSet getPagamentosByEmailAnunciante(UUID uId_Anunciante)
//    {
//        rs = null;
//
//        if (connect())
//        {
//            try
//            {
//                // Prepara a instrução SQL
//                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal\" +\n" +
//                        "                        \", Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento WHERE Vw_Pagamento.cEmailAnunciante ~* ('^'||?)");
//                pstmt.setObject(1, uId_Anunciante);
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

    public ResultSet getPagamentoByNmPlano(String cNmPlano)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal\" +\n" +
                        "                        \", Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento WHERE Vw_Pagamento.cNmPlano ~* ('^'||?)");
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

//    public ResultSet getPagamentoByEmailUniversitario(UUID uId_Universitario)
//    {
//        rs = null;
//
//        if (connect())
//        {
//            try
//            {
//                // Prepara a instrução SQL
//                pstmt = conn.prepareStatement("SELECT Vw_Pagamento.uId, Vw_Pagamento.cAtivo, Vw_Pagamento.dDtFim, Vw_Pagamento.nPctDesconto, Vw_Pagamento.nTotal\" +\n" +
//                        "                        \", Vw_Pagamento.cEmailAnunciante, Vw_Pagamento.cNmPlano, Vw_Pagamento.cEmailUniversitario FROM Vw_Pagamento WHERE Vw_Pagamento.cEmailUniversitario ~* ('^'||?)");
//                pstmt.setObject(1, uId_Universitario);
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
                if (pagamento.getcEmailUniversitario() == null)
                {
                    // Prepara a instrução SQL e define os seus argumentos
                    pstmt = conn.prepareStatement("UPDATE Pagamento SET cAtivo = ?, dDtFim = ?, nPctDesconto = ?, nTotal = ?, uId_Anunciante = FN_Anunciante_Id(NULL, ?), uId_Plano = FN_Plano_Id(?) WHERE uId = ?");
                    pstmt.setString(1, pagamento.getcAtivo());
                    pstmt.setObject(2, pagamento.getdDtFim());
                    pstmt.setDouble(3, pagamento.getnPctDesconto());
                    pstmt.setDouble(4, pagamento.getnTotal());
                    pstmt.setString(5, pagamento.getcEmailAnunciante());
                    pstmt.setString(6, pagamento.getcNmPlano());
                    pstmt.setObject(7, pagamento.getuId());
                }
                else
                {
                    // Prepara a instrução SQL e define os seus argumentos
                    pstmt = conn.prepareStatement("UPDATE Pagamento SET cAtivo = ?, dDtFim = ?, nPctDesconto = ?, nTotal = ?, uId_Plano = FN_Plano_Id(?), uId_Universitario = FN_Universitario_Id(NULL, ?, NULL) WHERE uId = ?");
                    pstmt.setString(1, pagamento.getcAtivo());
                    pstmt.setObject(2, pagamento.getdDtFim());
                    pstmt.setDouble(3, pagamento.getnPctDesconto());
                    pstmt.setDouble(4, pagamento.getnTotal());
                    pstmt.setString(5, pagamento.getcEmailUniversitario());
                    pstmt.setString(6, pagamento.getcNmPlano());
                    pstmt.setObject(7, pagamento.getuId());
                }

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
