package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.Conexao;
import org.example.crud_hestiajdbc_servlet.model.Plano;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlanoDAO extends Conexao {
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int adicionarPlano(Plano plano)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("INSERT INTO Plano (cNome, cTipoUsuario, nValor, cDescricao) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, plano.getcNome());
            pstmt.setString(2, plano.getcTipoUsuario());
            pstmt.setDouble(3, plano.getnValor());
            pstmt.setString(4, plano.getcDescricao());

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
    public ResultSet selecionarTodosPlanos()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuarionValor, cDescricao FROM Plano");

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

    public ResultSet selecionarPlanosPorId(UUID uId)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuarionValor, cDescricao FROM Plano WHERE uId = ?");
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

    public ResultSet selecionarPlanosPorNome(String cNome)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuarionValor, cDescricao FROM Plano WHERE cNome = ?");
            pstmt.setString(1, cNome);

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

    public ResultSet selecionarPlanosPorTipoUsuario(String cTipoUsuario)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuarionValor, cDescricao FROM Plano WHERE cTipoUsuario = ?");
            pstmt.setString(1, cTipoUsuario);

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

    public ResultSet selecionarPlanosPorValorCrescente()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuarionValor, cDescricao FROM Plano ORDER BY nValor");

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

    public ResultSet selecionarPlanosPorValorDecrescente()
    {
        try
        {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT uId, cNome, cTipoUsuarionValor, cDescricao FROM Plano ORDER BY nValor DESC");

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
    public UUID acharIdBoost(String nmPlao)
    {
        UUID uuid = null;
        try {
            conectar();

            // Prepara a instrução SQL
            pstmt = conn.prepareStatement("SELECT FN_Plano_Id(?)");
            pstmt.setString(1, nmPlao);

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
    public int atualizarPlano(Plano plano)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("UPDATE Plano SET cNome = ?, cTipoUsuario = ?, nValor = ?, cDescricao = ? WHERE uId = ?");
            pstmt.setString(1, plano.getcNome());
            pstmt.setString(2, plano.getcTipoUsuario());
            pstmt.setDouble(3, plano.getnValor());
            pstmt.setString(4, plano.getcDescricao());
            pstmt.setObject(5, plano.getuId());

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
    public int removerPlano(UUID uId)
    {
        try
        {
            conectar();

            // Prepara a instrução SQL e define os seus argumentos
            pstmt = conn.prepareStatement("DELETE FROM Plano WHERE uId = ?");
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
