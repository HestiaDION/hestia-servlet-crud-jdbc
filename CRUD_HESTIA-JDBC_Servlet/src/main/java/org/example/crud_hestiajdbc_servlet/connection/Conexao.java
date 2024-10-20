package org.example.crud_hestiajdbc_servlet.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
//    DECLARAÇÃO DOS ATRIBUTOS DA CLASSE
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;

//    DEFINIÇÃO DO MÉTODO CONECTAR
    protected boolean conectar()
    {
        try
        {
            // Define qual driver deve ser utilizado para a conexão
            Class.forName("org.postgresql.Driver");

            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:postgresql://pg-aplicativo-hestia24.k.aivencloud.com:23986/hestia", "avnadmin", "AVNS_3URGOb6MG5fTz7u4pnP");

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
    protected boolean desconectar()
    {
        try
        {
            // Fecha a conexão caso ela exista e esteja ativa
            if (!conn.isClosed() && conn != null)
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
