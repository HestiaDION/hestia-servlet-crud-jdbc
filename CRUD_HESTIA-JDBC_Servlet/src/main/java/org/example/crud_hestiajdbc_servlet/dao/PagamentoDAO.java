package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.Conexao;
import org.example.crud_hestiajdbc_servlet.model.Pagamento;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PagamentoDAO extends Conexao {
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int adicionarPagamento(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("INSERT INTO Pagamento (uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setObject(1, pagamento.getuId());
            pstmt.setString(2, pagamento.getcAtivo());
            pstmt.setDate(3, pagamento.getdDtFim());
            pstmt.setDouble(4, pagamento.getnPctDesconto());
            pstmt.setDouble(5, pagamento.getnTotal());
            pstmt.setObject(6, pagamento.getuId_Anunciante());
            pstmt.setObject(7, pagamento.getuId_Plano());
            pstmt.setObject(8, pagamento.getuId_Universitario());

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

    public ResultSet selecionarPagamentosPorId(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE uId = ?");
            pstmt.setObject(1, pagamento.getuId());

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

    public ResultSet selecionarPagamentosPorAtividade(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE cAtivo = ?");
            pstmt.setString(1, pagamento.getcAtivo());

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

    public ResultSet selecionarPagamentosPorDtFim(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE dDtFim = ?");
            pstmt.setObject(1, pagamento.getdDtFim());

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

    public ResultSet selecionarPagamentosPorPctDesconto(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE nPctDesconto = ?");
            pstmt.setDouble(1, pagamento.getnPctDesconto());

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

    public ResultSet selecionarPagamentosPorValor(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE nTotal = ?");
            pstmt.setDouble(1, pagamento.getnTotal());

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

    public ResultSet selecionarPagamentosPorIdAnunciante(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE uId_Anunciante = ?");
            pstmt.setObject(1, pagamento.getuId_Anunciante());

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

    public ResultSet selecionarPagamentosPorIdPlano(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE uId_Plano = ?");
            pstmt.setObject(1, pagamento.getuId_Plano());

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

    public ResultSet selecionarPagamentosPorIdUniversitario(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cAtivo, dDtFim, nPctDesconto, nTotal, uId_Anunciante, uId_Plano, uId_Universitario FROM Pagamento WHERE uId_Universitario = ?");
            pstmt.setObject(1, pagamento.getuId_Universitario());

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

//    DEFINIÇÃO DOS MÉTODOS DE CONSULTA ESPECÍFICA
//    public ResultSet selecionarTodosAnunciantes()
//    {
//    }

//    DEFINIÇÃO DO MÉTODO DE ATUALIZAÇÃO NO BANCO DE DADOS
    public int atualizarPagamento(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("UPDATE Pagamento SET cAtivo = ?, dDtFim = ?, nPctDesconto = ?, nTotal = ?, uId_Anunciante = ?, uId_Plano = ?, uId_Universitario = ? WHERE uId = ?");
            pstmt.setString(1, pagamento.getcAtivo());
            pstmt.setObject(2, pagamento.getdDtFim());
            pstmt.setDouble(3, pagamento.getnPctDesconto());
            pstmt.setDouble(4, pagamento.getnTotal());
            pstmt.setObject(5, pagamento.getuId_Anunciante());
            pstmt.setObject(6, pagamento.getuId_Plano());
            pstmt.setObject(7, pagamento.getuId_Universitario());
            pstmt.setObject(8, pagamento.getuId());

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
    public int removerPagamento(Pagamento pagamento)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("DELETE FROM Pagamento WHERE uId = ?");
            pstmt.setObject(1, pagamento.getuId());

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
