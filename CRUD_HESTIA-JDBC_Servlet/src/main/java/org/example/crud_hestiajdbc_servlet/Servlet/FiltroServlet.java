package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_hestiajdbc_servlet.dao.FiltroDAO;
import org.example.crud_hestiajdbc_servlet.model.Filtro;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "FiltroServlet", value = "/Filtro-Servlet")
public class FiltroServlet extends HttpServlet {
    private FiltroDAO filtroDAO = new FiltroDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


                    listarFiltros(request, response);


    }



    private void listarFiltros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultSet rs = null;
        List<Filtro> filtros = new ArrayList<>();

        try {
            rs = filtroDAO.selecionarTodosFiltros();

            while (rs.next()) {
                Filtro filtro = new Filtro();
                filtro.setuId(UUID.fromString(rs.getString("uId")));
                filtro.setcNome(rs.getString("cNome"));
                filtro.setcCategoria(rs.getString("cCategoria"));
                filtros.add(filtro);
            }

        } catch (SQLException e) {
            if (rs == null){
                request.setAttribute("ListaFiltros", filtros);
                request.getRequestDispatcher("ListaFiltros.jsp").forward(request, response);
            }
            request.setAttribute("errorMessage", "Erro ao obter dados do banco de dados.");
            request.getRequestDispatcher("erro.html").forward(request, response);
            return;
        }
        request.setAttribute("ListaFiltro", filtros);
        request.getRequestDispatcher("page/Filtro.jsp").forward(request, response);
    }





}
