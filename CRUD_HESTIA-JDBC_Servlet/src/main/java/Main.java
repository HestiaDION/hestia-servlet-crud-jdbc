import org.example.crud_hestiajdbc_servlet.dao.*;
import org.example.crud_hestiajdbc_servlet.model.*;

import java.rmi.server.UID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {
        // CLASSE ADMIN
//        AdminDAO admin = new AdminDAO();
//
//        if (admin.adicionarAdmin(new Admin("Lucas", "lucas.laurente@germinare.org", "123abc")) == -1) {
//            System.out.println("Não foi possível adicionar admin");
//        }
//        else {
//            System.out.println("Admin adicionado com sucesso!");
//        }
//
////        admin.atualizarAdmin(new Admin("Maria Júlia","maria.dawla@germinare.org.br","456def")); SOBRECARGA DE CONSTRUTAR
//
//        ResultSet rs = admin.selecionarTodosAdmins();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }
//
////        admin.removerAdmin(new Admin("Lucas", "lucas.laurente@germinare.org", "123abc")); SOBRECARGA DE CONSTRUTOR
//
//        System.out.println("Fim do programa");

        // CLASSE BOOST
//        BoostDAO boost = new BoostDAO();
//
//        if (boost.adicionarBoost(new Boost("Pro",15.7,12,"O Boost de 12% no anúncio!")) == -1) {
//            System.out.println("Não foi possível adicionar boost");
//        }
//        else {
//            System.out.println("Boost adicionado com sucesso!");
//        }
//
//        ResultSet rs = boost.selecionarTodosBoosts();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cNmBoost") +" "+ rs.getDouble("nValor") +" "+ rs.getString("cDescricao");
//            System.out.println(linha);
//        }

//        CLASSE FILTRO
        FiltroDAO filtro = new FiltroDAO();

        if (filtro.adicionarFiltro(new Filtro("Exótico", "Animal")) == -1) {
            System.out.println("Não foi possível adicionar o filtro");
        }
        else {
            System.out.println("Filtro adicionado com sucesso!");
        }

        ResultSet rs = filtro.selecionarTodosFiltros();
        while (rs.next()) {
            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("ccategoria");
            System.out.println(linha);
        }

//        CLASSE PAGAMENTO - DESCONSIDERAR
//        PagamentoDAO pagamento = new PagamentoDAO();
//        Connection conn = null;
//        PreparedStatement pstmt = conn.prepareStatement("SELECT uId FROM anunciante WHERE cUsername = 'julianev'");
//        UUID uid = UUID.class.cast(pstmt.executeQuery());
//
//        PlanoDAO plano = new PlanoDAO();
//
//        pagamento.adicionarPagamento(new Pagamento("A", "12/04/2025", 5, 16, uid, plano.selecionarPlanosPorNome("")));
    }
}