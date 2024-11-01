package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.Conexao;
import org.example.crud_hestiajdbc_servlet.model.Pagamento;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class PagamentoDAO extends Conexao {
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int adicionarPagamento(Pagamento pagamento)
    {
        try
        {
            conectar();

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
    public ResultSet selecionarTodosPagamentos()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento");

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

    public ResultSet selecionarPagamentosPorId(UUID uId)
    {
        try
        {
            conectar();

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

            // Atribuí um nulo para indentificação da exceção
            rs = null;
        }
        finally
        {
            desconectar();

            return rs;
        }
    }

    public ResultSet selecionarPagamentosPorAtividade(String cAtivo)
    {
        try
        {
            conectar();

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

            // Atribuí um nulo para indentificação da exceção
            rs = null;
        }
        finally
        {
            desconectar();

            return rs;
        }
    }

    public ResultSet selecionarPagamentosPorDtFimFutura()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE dDtFim > CURRENT_DATE");

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

    public ResultSet selecionarPagamentosPorPctDescontoCrescente()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento ORDER BY nPctDesconto");

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

    public ResultSet selecionarPagamentosPorPctDescontoDescrescente()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento ORDER BY nPctDesconto DESC");

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

    public ResultSet selecionarPagamentosPorValorTotalCrescente()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento ORDER BY nTotal");

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

    public ResultSet selecionarPagamentosPorValorTotalDescrescente()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento ORDER BY nTotal DESC");

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

    public ResultSet selecionarPagamentosPorIdAnunciante(UUID uId_Anunciante)
    {
        try
        {
            conectar();

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

            // Atribuí um nulo para indentificação da exceção
            rs = null;
        }
        finally
        {
            desconectar();

            return rs;
        }
    }

    public ResultSet selecionarPagamentosPorIdPlano(UUID uId_Plano)
    {
        try
        {
            conectar();

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

            // Atribuí um nulo para indentificação da exceção
            rs = null;
        }
        finally
        {
            desconectar();

            return rs;
        }
    }

    public ResultSet selecionarPagamentosPorIdUniversitario(UUID uId_Universitario)
    {
        try
        {
            conectar();

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
    public UUID acharIdPagamento(UUID idUsuario, UUID idPlano, Date dtFim)
    {
        UUID uuid = null;
        try {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT FN_Pagamento_Id(?, ?, ?)");
            pstmt.setObject(1, idUsuario);
            pstmt.setObject(2, idPlano);
            pstmt.setDate(3, dtFim);

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
    public int atualizarPagamento(Pagamento pagamento)
    {
        try
        {
            conectar();

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
    public int removerPagamento(UUID uId)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("DELETE FROM Pagamento WHERE uId = ?");
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
