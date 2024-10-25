package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_hestiajdbc_servlet.dao.AdminDAO;
import org.example.crud_hestiajdbc_servlet.dao.FiltroDAO;
import org.example.crud_hestiajdbc_servlet.dao.PagamentoDAO;
import org.example.crud_hestiajdbc_servlet.model.Admin;
import org.example.crud_hestiajdbc_servlet.model.Filtro;
import org.example.crud_hestiajdbc_servlet.model.Pagamento;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet(name = "Inserir-Pagamento", value = "/Inserir-Pagamento-Servlet")
public class CadastroPagamento extends HttpServlet {
    PagamentoDAO pagamentoDAO = new PagamentoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String cAtivo = req.getParameter("cAtivo");
            String dDtFimstr = (req.getParameter("dDtFim")); // Converta conforme necessário
            String nPctDescontostr = (req.getParameter("nPctDesconto"));
            String nTotalstr = (req.getParameter("nTotal"));
            UUID uId_Anunciante = UUID.fromString(req.getParameter("uId_Anunciante"));
            UUID uId_Plano = UUID.fromString(req.getParameter("uId_Plano"));
            UUID uId_Universitario = UUID.fromString(req.getParameter("uId_Universitario"));
            UUID uId = UUID.randomUUID();
            if (cAtivo == null || dDtFimstr == null|| nPctDescontostr == null|| nTotalstr == null || uId_Anunciante == null|| uId_Plano == null || uId_Universitario == null || uId == null ) {
                req.setAttribute("errorMessage", "Todos os campos são obrigatórios.");
                req.getRequestDispatcher("page/InserirPagamento.jsp").forward(req, resp);
                return;
            }
            double nTotal = Double.parseDouble(nTotalstr);
            double nPctDesconto = Double.parseDouble(nPctDescontostr);
            Date dDtFim = new Date(dDtFimstr);

            pagamentoDAO.adicionarPagamento(new Pagamento(uId,cAtivo, (java.sql.Date) dDtFim,nPctDesconto,nTotal,uId_Anunciante,uId_Plano,uId_Universitario));

            // Redirecionar para uma página de sucesso ou confirmação
            req.getRequestDispatcher("page/InserirPagamento.jsp").forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher("page/InserirPagamento.jsp").forward(req, resp);

            e.printStackTrace();
            // Tratar o erro e redirecionar para uma página de erro, se necessário
        }
    }
}

