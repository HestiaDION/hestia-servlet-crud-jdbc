import org.example.crud_hestiajdbc_servlet.dao.*;
import org.example.crud_hestiajdbc_servlet.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {
//         CLASSE ADMIN
//        AdminDAO admin = new AdminDAO();
//
//        if (admin.adicionarAdmin(new Admin("Lucas", "lucas.laurente@germinare.org", "123abc")) == - 1) {
//            System.out.println("Não foi possível adicionar admin");
//        }
//        else {
//            System.out.println("Admin adicionado com sucesso!");
//        }

////        admin.atualizarAdmin(new Admin("8a56e275-31fa-4498-9a2c-caae5858c42c", Maria Júlia","maria.dawla@germinare.org.br","456def"));
//
//        ResultSet rs = admin.selecionarTodosAdmins();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }
//
//        if (admin.removerAdmin(new Admin(UUID.fromString("efe1c56e-8708-4bf3-9be0-5ee4cbda3c40"), "Lucas", "lucas.laurente@germinare.org", "123abc")) == -1) {
//             System.out.println("Não foi possível remover admin");
//        }
//        else {
//            System.out.println("Admin removido com sucesso!");
//        }
//
//        System.out.println("Fim do programa");

        // CLASSE BOOST
        BoostDAO boost = new BoostDAO();

        // INSERT boost
        if (boost.adicionarBoost(new Boost("Pro",15.7,12,"O Boost de 12% no anúncio!")) == -1) {
            System.out.println("Não foi possível adicionar boost");
        }
        else {
            System.out.println("Boost adicionado com sucesso!");
        }

        // SELECT * boost
//        ResultSet rs = boost.selecionarTodosBoosts();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cNmBoost") +" "+ rs.getDouble("nValor") +" "+ rs.getString("cDescricao");
//            System.out.println(linha);
//        }

        // DELETE boost
        // tem que ser construtor com o ID (usar function do banco)
//        if (boost.removerBoost(new Boost("Pro",15.7,12,"O Boost de 12% no anúncio!")) == -1) {
//            System.out.println("Não foi possível remover boost");
//        }
//        else {
//            System.out.println("Boost removido com sucesso!");
//        }

//        CLASSE FILTRO
        FiltroDAO filtro = new FiltroDAO();
//
         // INSERT filtro
//        if (filtro.adicionarFiltro(new Filtro("Exótico", "Animal")) == -1) {
//            System.out.println("Não foi possível adicionar o filtro");
//        }
//        else {
//            System.out.println("Filtro adicionado com sucesso!");
//        }

        // SELECT * filtro
//        ResultSet rs = filtro.selecionarTodosFiltros();
//
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("ccategoria");
//            System.out.println(linha);
//        }

        // DELETE filtro
            // COM ID - está funcionando; sem o ID, o método tenta pegar o uId do objeto, mas o uId não foi criado prlo construtor
            // tem que ser construtor com o ID (usar function do banco)
//        if (filtro.removerFiltro(new Filtro(UUID.fromString("5c332a3e-6862-4ab8-85ba-2fbd59af4d5e"),"Exótico", "Animal")) == -1) {
//        if (filtro.removerFiltro(new Filtro("Exótico", "Animal")) == -1) {
//            System.out.println("Não foi possível remover o filtro");
//        }
//        else {
//            System.out.println("Filtro removido com sucesso!");
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
//
//        UUID uIdAnunciante = UUID.fromString(""); // SELECT com WHERE que recebe username - function
//        UUID uIdPlano = UUID.fromString(""); // SELECT com WHERE que recebe username - function
//
//        Pagamento pagamento = new Pagamento("1", Date.valueOf("2024-10-22"), 5, 16, uIdAnunciante, uIdPlano, null);

        // Criar método para charmar FN para pegar FKs, mostrar username e selcionar FK - precisamos da view

//        CLASSE PLANO_VANTAGEM
        Plano_vantagemDAO plano_vantagem = new Plano_vantagemDAO();

        UUID uIdPlano = UUID.fromString("8abb0f0a-2531-410b-97a7-cd2aae422f2a"); //FN functionpra pegar o ID

        // INSERT plano_vantagem
//        if (plano_vantagem.adicionarPlanoVantagem(new Plano_vantagem("Desconto no anúncio", '1', uIdPlano)) == -1) {
//            System.out.println("Não foi possível adicionar a vantagem do plano.");
//        }
//        else {
//            System.out.println("Vantagem do plano adicionada com sucesso!");
//        }

        // UPDATE plano_vantagem
        // usar function do banco
//        if (plano_vantagem.atualizarPlanoVantagem(new Plano_vantagem(UUID.fromString("662d0911-a23f-4632-9cad-a320d31b3b3b"),
//                "Desconto na publicação de anúncios",'1',uIdPlano)) == -1) {
//            System.out.println("Não foi possível atualizar a vantagem do plano.");
//        }
//        else {
////            System.out.println("Vantagem do plano atualizada com sucesso!");
////        }

        // DELETE plano_vantagem
        // tem que ser construtor com o ID (usar function do banco)
////        plano_vantagem.removerPlanoVantagem(new Plano_vantagem(UUID.fromString("662d0911-a23f-4632-9cad-a320d31b3b3b"),
////                "Desconto na publicação de anúncios",'1',uIdPlano));
    }
}