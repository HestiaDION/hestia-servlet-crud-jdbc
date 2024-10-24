package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_hestiajdbc_servlet.dao.FiltroDAO;
import org.example.crud_hestiajdbc_servlet.model.Boost;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        excluirFiltro(req, resp);
        listarFiltros(req,resp);
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
    private void excluirFiltro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uId = request.getParameter("uId");
        if (uId != null && !uId.isEmpty()) {
            try {
                Filtro filtro = new Filtro();
                filtro.setuId(UUID.fromString(uId));

                int isDeleted = filtroDAO.removerFiltro(filtro);
                if (isDeleted == 1) {
                    request.setAttribute("successMessage", "Filtro excluído com sucesso!");
                    request.getRequestDispatcher("page/Filtro.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Erro ao excluir o Filtro.");
                }
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMessage", "ID do Filtro inválido.");
            }
        } else {
            request.setAttribute("errorMessage", "ID do Filtro não fornecido.");
        }
    }


}
