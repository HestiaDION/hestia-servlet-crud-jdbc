package org.example.crud_hestiajdbc_servlet;

import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;
import org.example.crud_hestiajdbc_servlet.dao.FiltroDAO;
import org.example.crud_hestiajdbc_servlet.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        FiltroDAO filtroDAO = new FiltroDAO();
        AdminDAO adminDAO = new AdminDAO();

         ResultSet rs = filtroDAO.selecionarTodosFiltros();
         try {
             while (rs.next()) {
                String linha = rs.getString("uId")+" "+rs.getString("cNome")+" "+rs.getString("cCategoria");
                 System.out.println(linha);
             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

        ResultSet rs1 = adminDAO.selecionarTodosAdmins();
        try {
            while (rs1.next()) {
                String linha = rs1.getString("uId")+" "+rs1.getString("cNome")+" "+rs1.getString("cEmail");
                System.out.println(linha);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
