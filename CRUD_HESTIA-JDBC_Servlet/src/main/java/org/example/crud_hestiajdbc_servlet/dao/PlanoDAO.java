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
            if (conectar())
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("INSERT INTO Plano (cNome, nValor, cDescricao) VALUES (?, ?, ?)");
                pstmt.setString(1, plano.getcNome());
                pstmt.setDouble(2, plano.getnValor());
                pstmt.setString(3, plano.getcDescricao());

                // Executa a instrução e guarda as linhas afetadas
                int linhasAfetadas = pstmt.executeUpdate();

                return linhasAfetadas;
            }
            else
            {
                return -1;
            }
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
            if (conectar())
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, nValor, cDescricao FROM Plano");

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();
            }
            else
            {
                rs = null;
            }
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
            if (conectar())
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, nValor, cDescricao FROM Plano WHERE uId = ?");
                pstmt.setObject(1, uId);

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();
            }
            else
            {
                rs = null;
            }
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
            if (conectar())
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, nValor, cDescricao FROM Plano WHERE cNome = ?");
                pstmt.setString(1, cNome);

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();
            }
            else
            {
                rs = null;
            }
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
            if (conectar())
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, nValor, cDescricao FROM Plano ORDER BY nValor");

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();
            }
            else
            {
                rs = null;
            }
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
            if (conectar())
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, nValor, cDescricao FROM Plano ORDER BY nValor DESC");

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();
            }
            else
            {
                rs = null;
            }
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
    public int atualizarPlano(Plano plano)
    {
        try
        {
            if (conectar())
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("UPDATE Plano SET cNome = ?, nValor = ?, cDescricao = ? WHERE uId = ?");
                pstmt.setString(1, plano.getcNome());
                pstmt.setDouble(2, plano.getnValor());
                pstmt.setString(3, plano.getcDescricao());
                pstmt.setObject(4, plano.getuId());

                // Executa a instrução e guarda as linhas afetadas
                int linhasAfetadas = pstmt.executeUpdate();

                return linhasAfetadas;
            }
            else
            {
                return -1;
            }
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
            if (conectar())
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("DELETE FROM Plano WHERE uId = ?");
                pstmt.setObject(1, uId);

                // Executa a instrução e guarda as linhas afetadas
                int linhasAfetadas = pstmt.executeUpdate();

                return linhasAfetadas;
            }
            else
            {
                return -1;
            }
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
