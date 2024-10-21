package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_hestiajdbc_servlet.dao.PagamentoDAO;
import org.example.crud_hestiajdbc_servlet.model.Filtro;
import org.example.crud_hestiajdbc_servlet.model.Pagamento;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "PagamentoServlet", value = "/Pagamento-Servlet")
public class PagamentoServlet extends HttpServlet {
    PagamentoDAO pagamentoDAO = new PagamentoDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        listarPagamentos(request, response);


    }



    private void listarPagamentos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultSet rs = null;
        List<Pagamento> pagamentos = new ArrayList<>();

        try {
            rs = pagamentoDAO.selecionarTodosPagamentos();

            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setuId(UUID.fromString(rs.getString("uId")));
                pagamento.
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
