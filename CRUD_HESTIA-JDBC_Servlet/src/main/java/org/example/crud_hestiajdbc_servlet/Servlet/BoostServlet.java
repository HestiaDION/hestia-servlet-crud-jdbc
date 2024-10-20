package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_hestiajdbc_servlet.dao.BoostDAO;
import org.example.crud_hestiajdbc_servlet.model.Boost;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet( name = "BoostServlet", value = "/Boost-Servlet")
public class BoostServlet extends HttpServlet {
    private BoostDAO boostDAO = new BoostDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        listarBoosts(request, response);

    }



    private void listarBoosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultSet rs = null;
        List<Boost> boosts = new ArrayList<>();

        try {
            rs = boostDAO.selecionarTodosBoosts();


            while (rs.next()) {
                Boost boost = new Boost();
                boost.setuId(UUID.fromString(rs.getString("uId")));
                boost.setcTipoBoost(rs.getString("cNmBoost"));
                boost.setnValor(rs.getDouble("nValor"));
                boost.setnPctBoost(rs.getDouble("nPctBoost"));
                boosts.add(boost);
            }


        }
        catch (NullPointerException e){
            request.setAttribute("ListaBoost", boosts);
            request.getRequestDispatcher("page/Boost.jsp").forward(request, response);
            return;
        }
        catch (SQLException e) {
            request.setAttribute("errorMessage", "Erro ao obter dados do banco de dados.");
            request.getRequestDispatcher("erro.html").forward(request, response);
            return;
        }
        request.setAttribute("ListaBoost", boosts);
        request.getRequestDispatcher("page/Boost.jsp").forward(request, response);
    }




}

