package org.example.crud_hestiajdbc_servlet.connection;

import java.sql.*;

public class DatabaseConnection
{
//    DECLARAÇÃO DOS ATRIBUTOS DA CLASSE
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;

//    DEFINIÇÃO DO MÉTODO CONECTAR
    protected boolean connect()
    {
        try
        {
            // Define qual driver deve ser utilizado para a conexão
            Class.forName("org.postgresql.Driver");

            System.out.println(System.getenv("urlBD"));

            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(System.getenv("urlBD"), System.getenv("userBD"), System.getenv("passwordBD"));

            // Retornar true ao estabelecer a conexão
            return true;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            // Imprime a exceção no console
            e.printStackTrace();

            // Retornar false se não for possível estabelecer a conexão
            return false;
        }
    }

//    DEFINIÇÃO DO MÉTODO DESCONECTAR
    protected boolean disconnect()
    {
        try
        {
            // Fecha a conexão caso ela exista e esteja ativa
            if (conn != null && !conn.isClosed())
            {
                // Finaliza a conexão após verificar que ela está ativa
                conn.close();
            }

            // Retorna true ao fechar a conexão ou se ela já estava fechada antes
            return true;
        }
        catch (SQLException sqle)
        {
            // Imprime a exceção no console
            sqle.printStackTrace();

            // Retorna false se houver um erro na desconexão
            return false;
        }
    }
}
