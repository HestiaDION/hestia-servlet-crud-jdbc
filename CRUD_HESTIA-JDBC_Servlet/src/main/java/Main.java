import org.example.crud_hestiajdbc_servlet.dao.*;
import org.example.crud_hestiajdbc_servlet.model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {
//        **************************************************************************************************************
//         CLASSE ADMIN
//        **************************************************************************************************************
//        AdminDAO admin = new AdminDAO();
//
//        // INSERT admin
//        if (admin.adicionarAdmin(new Admin("Lucas", "lucas.laurente@germinare.org", "123abc")) == - 1) {
//            System.out.println("Não foi possível adicionar admin");
//        }
//        else {
//            System.out.println("Admin adicionado com sucesso!");
//        }
//
//        // UPDATE admin
//        // tem que usar function do banco pra pegar ID
//        admin.atualizarAdmin(new Admin(UUID.fromString("8a56e275-31fa-4498-9a2c-caae5858c42c"), "Maria Júlia","maria.dawla@germinare.org.br","456def"));
//
//        // SELECT * admin
//        ResultSet rs = admin.selecionarTodosAdmins();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }

        // SELECT por ID
//        ResultSet rs = admin.selecionarAdminsPorId(new Admin(UUID.fromString("8a56e275-31fa-4498-9a2c-caae5858c42c"),"Pietro","pietro.medico@germinare.org.com","1234"));
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }

        // SELECT por email
//        ResultSet rs = admin.selecionarAdminsPorEmail(new Admin(UUID.fromString("8a56e275-31fa-4498-9a2c-caae5858c42c"),"Pietro","pietro.medico@germinare.org.com","1234"));
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }

        // SELECT por nome
//        rs = admin.selecionarAdminsPorNome(new Admin(UUID.fromString("8a56e275-31fa-4498-9a2c-caae5858c42c"),"Pietro","pietro.medico@germinare.org.com","1234"));
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }

        // SELECT por senha
//        rs = admin.selecionarAdminsPorSenha(new Admin(UUID.fromString("8a56e275-31fa-4498-9a2c-caae5858c42c"),"Pietro","pietro.medico@germinare.org.com","1234"));
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }

        // DELETE admin
        // usar function do banco pra pegar ID
//        if (admin.removerAdmin(new Admin(UUID.fromString("efe1c56e-8708-4bf3-9be0-5ee4cbda3c40"), "Lucas", "lucas.laurente@germinare.org", "123abc")) == -1) {
//             System.out.println("Não foi possível remover admin");
//        }
//        else {
//            System.out.println("Admin removido com sucesso!");
//        }
//
//        System.out.println("Fim do programa");

//        **************************************************************************************************************
//        // CLASSE BOOST - falta testar os outros selects
//        **************************************************************************************************************
//        BoostDAO boost = new BoostDAO();

        // INSERT boost
//        if (boost.adicionarBoost(new Boost("Pro",15.7,12,"O Boost de 12% no anúncio!")) == -1) {
//            System.out.println("Não foi possível adicionar boost");
//        }
//        else {
//            System.out.println("Boost adicionado com sucesso!");
//        }
//
//        // SELECT * boost
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

//        **************************************************************************************************************
//        CLASSE FILTRO - falta testas os outros selects
//        **************************************************************************************************************
//        FiltroDAO filtro = new FiltroDAO();
////
//         // INSERT filtro
//        if (filtro.adicionarFiltro(new Filtro("Exótico", "Animal")) == -1) {
//            System.out.println("Não foi possível adicionar o filtro");
//        }
//        else {
//            System.out.println("Filtro adicionado com sucesso!");
//        }
//
//        // SELECT * filtro
//        ResultSet rs = filtro.selecionarTodosFiltros();
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("ccategoria");
//            System.out.println(linha);
//        }

        // DELETE filtro
            // COM ID - está funcionando; sem o ID, o método tenta pegar o uId do objeto, mas o uId não foi criado prlo construtor
            // tem que ser construtor com o ID (usar function do banco)
//        if (filtro.removerFiltro(new Filtro(UUID.fromString("5c332a3e-6862-4ab8-85ba-2fbd59af4d5e"),"Exótico", "Animal")) == -1) {
////        if (filtro.removerFiltro(new Filtro("Exótico", "Animal")) == -1) {
//            System.out.println("Não foi possível remover o filtro");
//        }
//        else {
//            System.out.println("Filtro removido com sucesso!");
//        }
//
//        **************************************************************************************************************
//        // CLASSE PLANO_VANTAGEM - falta testar os outros selects
//        **************************************************************************************************************
//        Plano_vantagemDAO plano_vantagem = new Plano_vantagemDAO();
//
//        UUID uIdPlano = UUID.fromString("8abb0f0a-2531-410b-97a7-cd2aae422f2a"); //FN functionpra pegar o ID
//
//        // INSERT plano_vantagem
//        if (plano_vantagem.adicionarPlanoVantagem(new Plano_vantagem("Desconto no anúncio", '1', uIdPlano)) == -1) {
//            System.out.println("Não foi possível adicionar a vantagem do plano.");
//        }
//        else {
//            System.out.println("Vantagem do plano adicionada com sucesso!");
//        }
//
//        // UPDATE plano_vantagem
//        // usar function do banco
//        if (plano_vantagem.atualizarPlanoVantagem(new Plano_vantagem(UUID.fromString("662d0911-a23f-4632-9cad-a320d31b3b3b"),
//                "Desconto na publicação de anúncios",'1',uIdPlano)) == -1) {
//            System.out.println("Não foi possível atualizar a vantagem do plano.");
//        }
//        else {
//            System.out.println("Vantagem do plano atualizada com sucesso!");
//        }

        // DELETE plano_vantagem
        // tem que ser construtor com o ID (usar function do banco)
//        plano_vantagem.removerPlanoVantagem(new Plano_vantagem(UUID.fromString("662d0911-a23f-4632-9cad-a320d31b3b3b"),
//                "Desconto na publicação de anúncios",'1',uIdPlano));

//        **************************************************************************************************************
//        CLASSE PAGAMENTO
//        **************************************************************************************************************
        PagamentoDAO pagamento = new PagamentoDAO();

        //INSERT pagamento
//        if (pagamento.adicionarPagamento(new Pagamento("0",Date.valueOf("2024-10-25"),0,16,
//                UUID.fromString("b7f82603-9065-4fd1-a39a-0365036b21f6"),UUID.fromString("8abb0f0a-2531-410b-97a7-cd2aae422f2a"),
//                null)) == -1) {
//            System.out.println("Não foi possível adicionar pagamento");
//        }
//        else {
//            System.out.println("Pagamento adicionado com sucesso!");
//        }

        // SELECT * pagamento - falta testar os outros selects
        ResultSet rs = pagamento.selecionarTodosPagamentos();
        while (rs.next()) {
            String linha = rs.getString("uid") +" "+ rs.getString("cativo") +" "+
                    rs.getDate("ddtfim") +" "+ rs.getDouble("npctdesconto") +" "+
                    rs.getDouble("ntotal") +" "+ rs.getObject("uid_anunciante") +" "+
                    rs.getObject("uid_plano") +" "+ rs.getObject("uid_universitario");
            System.out.println(linha);
        }

        // DELETE pagamento
        pagamento.removerPagamento(new Pagamento(UUID.fromString("43506f49-bb59-4e28-8cfa-af360b3a893e"),"0",Date.valueOf("2024-10-25"),0,16,
                UUID.fromString("b7f82603-9065-4fd1-a39a-0365036b21f6"),UUID.fromString("8abb0f0a-2531-410b-97a7-cd2aae422f2a"),
                null));

        System.out.println("Fim");
    }
}