package org.example.crud_hestiajdbc_servlet.dao;

import org.example.crud_hestiajdbc_servlet.connection.DatabaseConnection;
import org.example.crud_hestiajdbc_servlet.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AdminDAO extends DatabaseConnection
{
//    DEFINIÇÃO DO MÉTODO DE INSERÇÃO NO BANCO DE DADOS
    public int addAdmin(Admin admin)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("INSERT INTO Admin (cNome, cEmail, cSenha, cFoto) VALUES (?, ?, ?, ?)");
                pstmt.setString(1, admin.getcNome());
                pstmt.setString(2, admin.getcEmail());
                pstmt.setString(3, admin.getcSenha());
                pstmt.setString(4, admin.getcFoto());

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
    public ResultSet getAllAdmins()
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha, cFoto FROM Admin");

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

    public ResultSet getAdminById(UUID uId)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha, cFoto FROM Admin WHERE uId ~* ('^'||?)");
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

    public ResultSet getAdminByName(String cNome)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha, cFoto FROM Admin WHERE cNome ~* ('^'||?)");
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

    public ResultSet getAdminByEmail(String cEmail)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha, cFoto FROM Admin WHERE cEmail ~* ('^'||?)");
                pstmt.setString(1, cEmail);

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

    public ResultSet getAdminsByPasswordSize(int tamanhoSenha)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha, cFoto FROM Admin WHERE LENGTH(csenha) < ?");
                pstmt.setInt(1, tamanhoSenha);

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

    public ResultSet getAdminsByActivity(String cLogin)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT uId, cNome, cEmail, cSenha, cFoto FROM Admin WHERE cLogin ~* ('^'||?)");
                pstmt.setString(1, cLogin);

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

    public int getAdminActivity(String cEmail)
    {
        int atividade = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT cLogin FROM Admin WHERE cEmail = ?");
                pstmt.setString(1, cEmail);

                // Executa a instrução e guarda as linhas retornadas
                rs = pstmt.executeQuery();

                //Adiciona 1 se o admin estiver logado e 0 se não
                if(rs.next())
                {
                    if (rs.getString("cLogin").equals('1'))
                    {
                        atividade = 1;
                    }
                    else
                    {
                        atividade = 0;
                    }
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

        // Sempre retorna um inteiro, que pode ser -1 caso não seja possível conectar ou ocorra uma exceção
        return atividade;
    }

    public  ResultSet getAdminForLogin(String cEmail, String cSenha)
    {
        rs = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT cNome, cFoto FROM Admin WHERE cEmail = ? AND cSenha = ?");
                pstmt.setString(1, cEmail);
                pstmt.setString(2, cSenha);

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
    public UUID getAdminId(String email)
    {
        UUID uuid = null;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL
                pstmt = conn.prepareStatement("SELECT FN_Admin_Id(?)");
                pstmt.setString(1, email);

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
    public int updateAdmin(Admin admin)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                if (admin.getcFoto() != null)
                {
                    pstmt = conn.prepareStatement("UPDATE Admin SET cNome = ?, cEmail = ?, cSenha = ?, cFoto = ? WHERE uId = ?");
                    pstmt.setString(1, admin.getcNome());
                    pstmt.setString(2, admin.getcEmail());
                    pstmt.setString(3, admin.getcSenha());
                    pstmt.setString(4, admin.getcFoto());
                    pstmt.setObject(5, admin.getuId());
                }
                else
                {
                    pstmt = conn.prepareStatement("UPDATE Admin SET cNome = ?, cEmail = ?, cSenha = ? WHERE uId = ?");
                    pstmt.setString(1, admin.getcNome());
                    pstmt.setString(2, admin.getcEmail());
                    pstmt.setString(3, admin.getcSenha());
                    pstmt.setString(4, admin.getcFoto());
                }


                // Executa a instrução e guarda as linhas afetadas
                linhasAfetadas = pstmt.executeUpdate();

                return linhasAfetadas;
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

    public int setAdminActive(String cEmail)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("UPDATE Admin SET cLogin = '1' WHERE cEmail = ?");
                pstmt.setString(1, cEmail);

                // Executa a instrução e guarda as linhas afetadas
                linhasAfetadas = pstmt.executeUpdate();

                return linhasAfetadas;
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

    public int setAdminInactive(String cEmail)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("UPDATE Admin SET cLogin = '0' WHERE cEmail = ?");
                pstmt.setString(1, cEmail);

                // Executa a instrução e guarda as linhas afetadas
                linhasAfetadas = pstmt.executeUpdate();

                return linhasAfetadas;
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
    public int removeAdmin(UUID uId)
    {
        int linhasAfetadas = -1;

        if (connect())
        {
            try
            {
                // Prepara a instrução SQL e define os seus argumentos
                pstmt = conn.prepareStatement("DELETE FROM Admin WHERE uId = ?");
                pstmt.setObject(1, uId);

                // Executa a instrução e guarda as linhas afetadas
                linhasAfetadas = pstmt.executeUpdate();

                return linhasAfetadas;
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
