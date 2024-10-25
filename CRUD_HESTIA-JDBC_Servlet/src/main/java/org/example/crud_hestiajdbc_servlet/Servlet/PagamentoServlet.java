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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    excluirPagamento(req, resp);
    }

    private void listarPagamentos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultSet rs = null;
        List<Pagamento> pagamentos = new ArrayList<>();

        try {
            rs = pagamentoDAO.selecionarTodosPagamentos();

            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setuId(UUID.fromString(rs.getString("uId")));
                pagamento.setcAtivo(rs.getString("cAtivo"));
                pagamento.setdDtFim(rs.getDate("dDtFim"));
                pagamento.setnPctDesconto(rs.getDouble(("nPctDesconto")));
                pagamento.setnTotal(rs.getDouble(("nTotal")));
                pagamento.setuId_Anunciante(UUID.fromString(rs.getString("uId_Anunciante")));
                pagamento.setuId_Plano(UUID.fromString(rs.getString("uId_Plano")));
                pagamento.setuId_Universitario(UUID.fromString(rs.getString("uId_Universitario")));
                pagamentos.add(pagamento);
            }

        }catch (NullPointerException e){
                request.setAttribute("ListaPagamento", pagamentos);
                request.getRequestDispatcher("page/Pagamento.jsp").forward(request, response);
                return;
            }
        catch (SQLException e) {
                request.setAttribute("errorMessage", "Erro ao obter dados do banco de dados.");
                request.getRequestDispatcher("erro.html").forward(request, response);
                return;
            }

        request.setAttribute("ListaPagamento", pagamentos);
        request.getRequestDispatcher("page/Pagamento.jsp").forward(request, response);
    }
    private void excluirPagamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uId = request.getParameter("uId");
        if (uId != null && !uId.isEmpty()) {
            try {
                Pagamento pagamento = new Pagamento();
                pagamento.setuId(UUID.fromString(uId));

                int isDeleted = pagamentoDAO.removerPagamento(pagamento);
                if (isDeleted == 1) {
                    request.setAttribute("successMessage", "Pagamento excluído com sucesso!");
                    request.getRequestDispatcher("page/Pagamento.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Erro ao excluir o Pagamento.");
                }
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMessage", "ID do Pagamento inválido.");
            }
        } else {
            request.setAttribute("errorMessage", "ID do Pagamento não fornecido.");
        }
    }

}
