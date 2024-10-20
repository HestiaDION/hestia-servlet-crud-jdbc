package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.Conexao;
import org.example.crud_hestiajdbc_servlet.model.Plano_vantagem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Plano_vantagemDAO extends Conexao {
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int adicionarPlanoVantagem(Plano_vantagem planoVantagem)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("INSERT INTO Plano_vantagem (uId, cVantagem, cAtivo, uId_Plano) VALUES (?, ?, ? ,?)");
            pstmt.setObject(1, planoVantagem.getuId());
            pstmt.setString(2, planoVantagem.getcVantagem());
            pstmt.setInt(3, planoVantagem.getcAtivo());
            pstmt.setObject(4, planoVantagem.getuId_Plano());

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
    public ResultSet selecionarTodasVantagensPlano(Plano_vantagem planoVantagem)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cVantagem, cAtivo, uId_Plano FROM Plano_vantagem");

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

    public ResultSet selecionarVantagensPlanoPorId(Plano_vantagem planoVantagem)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cVantagem, cAtivo, uId_Plano FROM Plano_vantagem WHERE uId = ?");
            pstmt.setObject(1, planoVantagem.getuId());

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

    public ResultSet selecionarVantagensPlanoPorVantagem(Plano_vantagem planoVantagem)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cVantagem, cAtivo, uId_Plano FROM Plano_vantagem WHERE cVantagem = ?");
            pstmt.setString(1, planoVantagem.getcVantagem());

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

    public ResultSet selecionarVantagensPlanoPorAtividade(Plano_vantagem planoVantagem)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cVantagem, cAtivo, uId_Plano FROM Plano_vantagem WHERE cAtivo = ?");
            pstmt.setInt(1, planoVantagem.getcAtivo());

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

    public ResultSet selecionarVantagensPlanoPorIdPlano(Plano_vantagem planoVantagem)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cVantagem, cAtivo, uId_Plano FROM Plano_vantagem WHERE uId_Plano = ?");
            pstmt.setObject(1, planoVantagem.getuId_Plano());

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
    public int atualizarPlanoVantagem(Plano_vantagem planoVantagem)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("UPDATE Plano_vantagem SET cVantagem = ?, cAtivo = ?, uId_Plano = ? WHERE uId = ?");
            pstmt.setString(1, planoVantagem.getcVantagem());
            pstmt.setInt(2, planoVantagem.getcAtivo());
            pstmt.setObject(3, planoVantagem.getuId_Plano());
            pstmt.setObject(4, planoVantagem.getuId());

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
    public int removerPlanoVantagem(Plano_vantagem planoVantagem)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("DELETE FROM Plano_vantagem WHERE uId = ?");
            pstmt.setObject(1, planoVantagem.getuId());

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
