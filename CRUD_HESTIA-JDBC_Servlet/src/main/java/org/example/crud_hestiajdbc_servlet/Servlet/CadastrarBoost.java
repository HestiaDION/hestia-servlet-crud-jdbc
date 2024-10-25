package org.example.crud_hestiajdbc_servlet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_hestiajdbc_servlet.dao.BoostDAO;
import org.example.crud_hestiajdbc_servlet.dao.FiltroDAO;
import org.example.crud_hestiajdbc_servlet.model.Boost;
import org.example.crud_hestiajdbc_servlet.model.Filtro;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "Inserir-Boost", value = "/Inserir-Boost-Servlet")
public class CadastrarBoost extends HttpServlet {
    BoostDAO boostDAO = new BoostDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String cNmBoost = req.getParameter("cNmBoost");
            String nValorStr = (req.getParameter("nValor"));
            String nPctBoostStr = (req.getParameter("nPctBoost"));
            String cDescricao = req.getParameter("cDescricao");
            UUID uId = UUID.randomUUID();
            if (cNmBoost == null || nValorStr == null || nPctBoostStr == null || cDescricao == null) {
                req.setAttribute("errorMessage", "Todos os campos são obrigatórios.");
                req.getRequestDispatcher("page/InserirBoost.jsp").forward(req, resp);
                return;
            }
            double nValor = Double.parseDouble(nValorStr);
            double nPctBoost = Double.parseDouble(nPctBoostStr);

            boostDAO.adicionarBoost(new Boost(uId, cNmBoost, nValor, nPctBoost, cDescricao));

            req.getRequestDispatcher("page/InserirBoost").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            // Tratar erro, se necessário
        }
    }
}