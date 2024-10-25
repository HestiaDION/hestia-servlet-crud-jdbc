import org.example.crud_hestiajdbc_servlet.dao.*;
import org.example.crud_hestiajdbc_servlet.model.*;

import java.rmi.server.UID;
import java.sql.*;
import java.text.DateFormat;
import java.util.UUID;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        // CLASSE ADMIN
        AdminDAO admin = new AdminDAO();

        if (admin.adicionarAdmin(new Admin("Pietro", "pietro.medico@germinare.org", "123abc")) == - 1) {
            System.out.println("Não foi possível adicionar admin");
        }
        else {
            System.out.println("Admin adicionado com sucesso!");
        }

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
//        FiltroDAO filtro = new FiltroDAO();
//
//        if (filtro.adicionarFiltro(new Filtro("Exótico", "Animal")) == -1) {
//            System.out.println("Não foi possível adicionar o filtro");
//        }
//        else {
//            System.out.println("Filtro adicionado com sucesso!");
//        }
//
//        ResultSet rs = filtro.selecionarTodosFiltros();
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("ccategoria");
//            System.out.println(linha);
//        }

//        CLASSE PAGAMENTO
//        try
//        {
//            PagamentoDAO pagamento = new PagamentoDAO();
//            UUID uidAnunciante =
//
//            PlanoDAO plano = new PlanoDAO();
//            pstmt = conn.prepareStatement("SELECT uId FROM plano WHERE cNome = 'Chamas Douradas'");
//            UUID uidPlano = pstmt.executeQuery()
//                    ;
//
//            pagamento.adicionarPagamento(new Pagamento("A", "02/04/2025", 5, 16, uidAnunciante, uidPlano, null));
//            System.out.println("Pagamento adicionado com sucesso");
//        }
//        catch (IllegalArgumentException iae)
//        {
//            iae.printStackTrace();
//            System.out.println("Não foi possível adicionar o pagamento");
//        }

//        UUID uIdAnunciante = UUID.fromString();
//        UUID uIdPlano = UUID.fromString("8abb0f0a-2531-410b-97a7-cd2aae422f2a");

//        Pagamento pagamento = new Pagamento("1", Date.valueOf("2024-10-22"), 5, 16, uIdAnunciante, uIdPlano, null);

        // Criar método para charmar FN para pegar FKs, mostrar username e selcionar FK
    }
}