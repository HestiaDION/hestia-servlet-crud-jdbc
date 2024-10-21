import org.example.crud_hestiajdbc_servlet.dao.*;
import org.example.crud_hestiajdbc_servlet.model.*;

import java.rmi.server.UID;
import java.sql.*;
import java.util.UUID;

public class Main {
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://pg-aplicativo-hestia24.k.aivencloud.com:23986/hestia", "avnadmin", "AVNS_3URGOb6MG5fTz7u4pnP");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Main() throws SQLException {
    }

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
        PagamentoDAO pagamento = new PagamentoDAO();
        PreparedStatement pstmt = conn.prepareStatement("SELECT uId FROM anunciante WHERE cUsername = 'julianev'");
        UUID uidAnunciante = UUID.fromString(String.valueOf(pstmt.executeQuery()));

        pstmt = conn.prepareStatement("SELECT uId FROM plano WHERE cNome = 'Chamas Douradas'");
        UUID uidPlano = UUID.fromString(String.valueOf(pstmt.executeQuery()));

        pagamento.adicionarPagamento(new Pagamento("A","02/04/2025",5,16,uidAnunciante,uidPlano,null));
    }
}