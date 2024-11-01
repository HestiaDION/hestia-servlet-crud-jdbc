import org.example.crud_hestiajdbc_servlet.dao.*;
import org.example.crud_hestiajdbc_servlet.model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {
        //TESTAR OS UPDATES; REVER DEFAULTS;

//        **************************************************************************************************************
//         CLASSE ADMIN - rever update
//        **************************************************************************************************************
//        AdminDAO admin = new AdminDAO();
//
//        // INSERT admin
//        System.out.println("INSERINDO ADMIN");
//        if (admin.adicionarAdmin(new Admin("Lucas", "lucas.laurente@germinare.org", "123abc")) == - 1) {
//            System.out.println("Não foi possível adicionar admin");
//        }
//        else {
//            System.out.println("Admin adicionado com sucesso!");
//        }

//        // UPDATE admin | DESCONSIDERAR
//        // tem que usar function do banco pra pegar ID
//        admin.atualizarAdmin(new Admin(UUID.fromString("8a56e275-31fa-4498-9a2c-caae5858c42c"), "Maria Júlia","maria.dawla@germinare.org.br","456def"));
//
//        // SELECT * admin
//        System.out.println("\nMOSTRANDO TODOS OS ADMINS");
//
//        ResultSet rs = admin.selecionarTodosAdmins();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }
//
//        // SELECT por ID
//        System.out.println("\nFILTRANDO ADMIN POR ID");
//
//        rs = admin.selecionarAdminsPorId("c6bf83f1-9667-4b50-bc4b-70dbd201a7f2"));
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }
//
//        // SELECT por email
//        System.out.println("\nFILTRANDO ADMIN POR E-MAIL");
//
//        rs = admin.selecionarAdminsPorEmail("natalia.santos@germinare.org.br");
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }
//
//        // SELECT por nome
//        System.out.println("\nFILTRANDO ADMIN POR NOME");
//
//        rs = admin.selecionarAdminsPorNome("Natalia");
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }
//
//        // SELECT por senha
//        System.out.println("\nFILTRANDO ADMIN POR TAMANHO DE SENHA");
//
//        rs = admin.selecionarAdminsPorTamanhoSenha(4);
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cemail") + " " + rs.getString("csenha");
//            System.out.println(linha);
//        }
//
//        // DELETE admin
//        System.out.println("\nDELETANDO ADMIN");
//            // usar function pra pegar ID
//        if (admin.removerAdmin(admin.selecionarUid("natalia.santos@germinare.org.br")) == -1) {
//             System.out.println("Não foi possível remover admin");
//        }
//        else {
//            System.out.println("Admin removido com sucesso!");
//        }

//        **************************************************************************************************************
//        // CLASSE BOOST
//        **************************************************************************************************************
//        BoostDAO boost = new BoostDAO();
//
//        // INSERT boost
//        System.out.println("\nINSERINDO BOOST");
//        if (boost.adicionarBoost(new Boost("UP",10,5,"O Boost de 5% no anúncio!")) == -1) {
//            System.out.println("Não foi possível adicionar boost");
//        }
//        else {
//            System.out.println("Boost adicionado com sucesso!");
//        }
//
//        // SELECT * boost
//        System.out.println("\nMOSTRANDO TODOS OS BOOSTS");
//
//        ResultSet rs = boost.selecionarTodosBoosts();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cNmBoost") +" "+ rs.getDouble("nValor") +" "+ rs.getString("cDescricao");
//            System.out.println(linha);
//        }
//
//        // SELECT por ID
//        System.out.println("\nFILTRANDO BOOST POR ID");
//
//        rs = boost.selecionarBoostsPorId(UUID.fromString(UUID.fromString("78b8c4f4-d673-4484-98a3-acf2b5b67fed")));
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cNmBoost") +" "+ rs.getDouble("nValor") +" "+ rs.getString("cDescricao");
//            System.out.println(linha);
//        }
//
//         // SELECT por nome
//        System.out.println("\nFILTRANDO BOOST POR NOME");
//
//        rs = boost.selecionarBoostsPorNome("UP");
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cNmBoost") +" "+ rs.getDouble("nValor") +" "+ rs.getString("cDescricao");
//            System.out.println(linha);
//        }
//
//        // SELECT ordem crescente PctBoost
//        System.out.println("\nORDENANDO BOOST POR PCTBOOST EM ORDEM CRESCENTE");
//
//        rs = boost.selecionarBoostsPorPctBoostCrescente();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cNmBoost") +" "+ rs.getDouble("nValor") +" "+ rs.getString("cDescricao");
//            System.out.println(linha);
//        }
//
//        // SELECT ordem decrescente PctBoost
//        System.out.println("\nORDENANDO BOOST POR PCTBOOST EM ORDEM DECRESCENTE");
//
//        rs = boost.selecionarBoostsPorPctBoostDecrescente();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cNmBoost") +" "+ rs.getDouble("nValor") +" "+ rs.getString("cDescricao");
//            System.out.println(linha);
//        }
//
//        // SELECT ordem crescente Valor
//        System.out.println("ORDENANDO BOOST POR VALOR EM ORDEM CRESCENTE");
//
//        rs = boost.selecionarBoostsPorValorCrescente();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cNmBoost") +" "+ rs.getDouble("nValor") +" "+ rs.getString("cDescricao");
//            System.out.println(linha);
//        }
//
//        // SELECT ordem decrescente Valor
//        System.out.println("ORDENANDO BOOST POR VALOR EM ORDEM DECRESCENTE");
//
//        rs = boost.selecionarBoostsPorValorDecrescente();
//        while(rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cNmBoost") +" "+ rs.getDouble("nValor") +" "+ rs.getString("cDescricao");
//            System.out.println(linha);
//        }
//
//        // DELETE boost
//        System.out.println("\nDELETANDO BOOST");
//        if (boost.removerBoost(boost.selecionarUid()) == -1) {
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
//        System.out.println("\nINSERINDO FILTRO");
//
//        if (filtro.adicionarFiltro(new Filtro("Exótico", "Animal")) == -1) {
//            System.out.println("Não foi possível adicionar o filtro");
//        }
//        else {
//            System.out.println("Filtro adicionado com sucesso!");
//        }
//
//        // SELECT * filtro
//        System.out.println("\nMOSTRANDO TODOS OS FILTROS");
//
//        ResultSet rs = filtro.selecionarTodosFiltros();
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("ccategoria");
//            System.out.println(linha);
//        }
//
//        // SELECT por ID
//        System.out.println("\nFILTRANDO FILTROS POR ID");
//
//        rs = filtro.selecionarFiltrosPorId(UUID.fromString("e61f0f96-713d-4f53-b4f0-639dce9d1743"));
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("ccategoria");
//            System.out.println(linha);
//        }
//
//        // SELECT por categoria
//        System.out.println("\nFILTRANDO FILTROS POR CATEGORIA");
//
//        rs = filtro.selecionarFiltrosPorCategoria("animal");
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("ccategoria");
//            System.out.println(linha);
//        }
//
//        // SELECT por nome
//        System.out.println("\nFILTRANDO FILTROS POR CATEGORIA");
//
//        rs = filtro.selecionarFiltrosPorNome("Gato");
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("ccategoria");
//            System.out.println(linha);
//        }
//
////         DELETE filtro
//        System.out.println("\nDELETANDO FILTRO");
//        if (filtro.removerFiltro(filtro.selecionarUid()) == -1) {
//            System.out.println("Não foi possível remover o filtro");
//        }
//        else {
//            System.out.println("Filtro removido com sucesso!");
//        }

//        **************************************************************************************************************
//        CLASSE PLANO
//        **************************************************************************************************************
//        PlanoDAO plano = new PlanoDAO();

        // INSERT plano
//        if (plano.adicionarPlano(new Plano("Chamas Douradas", "Anunciante", 16,
//                "Plano do anunciante, com descontos e vantagens exclusivas")) == -1) {
//            System.out.println("Não foi possível adicionar o novo plano.");
//        }
//        else {
//            System.out.println("Novo plano adicionado com sucesso!");
//        }

         // SELECT * plano
//        System.out.println("\nMOSTRANDO TODOS OS PLANOS");

//        ResultSet rs = plano.selecionarTodosPlanos();
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+
//                    rs.getString("ctipousuario" +" "+ rs.getString("cdescricao"));
//            System.out.println(linha);
//        }

        // SELECT por ID
//        System.out.println("\nFILTRANDO PLANOS POR ID");

//        rs = plano.selecionarPlanosPorId(UUID.fromString("ceae7210-c6e7-4155-a5ee-11514620893f"));
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cdescricao");
//            System.out.println(linha);
//        }

        // SELECT por nome
//        System.out.println("\nFILTRANDO PLANOS POR NOME");

//        rs = plano.selecionarPlanosPorNome("Chamas Douradas");
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cdescricao");
//            System.out.println(linha);
//        }

        // SELECT ordenando pelo valor (ordem crescente)
//        System.out.println("\nORDENANDO PLANOS POR VALOR TOTAL (ORDEM CRESCENTE)");

//        rs = plano.selecionarPlanosPorValorCrescente();
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cdescricao");
//            System.out.println(linha);
//        }

        // SELECT ordenando pelo valor (ordem decrescente)
//        System.out.println("\nORDENANDO PLANOS POR VALOR TOTAL (ORDEM DECRESCENTE)");

//        rs = plano.selecionarPlanosPorValorDecrescente();
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cnome") +" "+ rs.getString("cdescricao");
//            System.out.println(linha);
//        }

//        **************************************************************************************************************
//        CLASSE PLANO_VANTAGEM - falta testar os selects
//        **************************************************************************************************************
        Plano_vantagemDAO plano_vantagem = new Plano_vantagemDAO();

        UUID uIdPlano = UUID.fromString("ceae7210-c6e7-4155-a5ee-11514620893f"); //FN functionpra pegar o ID
//
//        // INSERT plano_vantagem
        if (plano_vantagem.adicionarPlanoVantagem(new Plano_vantagem("Desconto no anúncio", '1', uIdPlano)) == -1) {
            System.out.println("Não foi possível adicionar a vantagem do plano.");
        }
        else {
            System.out.println("Vantagem do plano adicionada com sucesso!");
        }

        // SELECT * plano_vantagem
//        System.out.println("\nMOSTRANDO TODAS AS VANTAGENS DOS PLANOS");
//
        ResultSet rs = plano_vantagem.selecionarTodasVantagensPlano();
        while (rs.next()) {
            String linha = rs.getString("uid") +" "+ rs.getString("cvantagem") +" "+
                    rs.getString("cativo") +" "+ rs.getString("uid_plano");
            System.out.println(linha);
        }

        // SELECT por ID
        System.out.println("\nFILTRANDO AS VANTAGENS DOS PLANOS POR ID");

        rs = plano_vantagem.selecionarVantagensPlanoPorId(UUID.fromString("a3e4b572-6069-4ee5-8b0f-0a1a930df057"));
        while (rs.next()) {
            String linha = rs.getString("uid") +" "+ rs.getString("cvantagem") +" "+
                    rs.getString("cativo") +" "+ rs.getString("uid_plano");
            System.out.println(linha);
        }

        // SELECT por id_plano
        System.out.println("\nFILTRANDO AS VANTAGENS DOS PLANOS POR PLANO");

        rs = plano_vantagem.selecionarVantagensPlanoPorIdPlano(UUID.fromString("ceae7210-c6e7-4155-a5ee-11514620893f"));
        while (rs.next()) {
            String linha = rs.getString("uid") +" "+ rs.getString("cvantagem") +" "+
                    rs.getString("cativo") +" "+ rs.getString("uid_plano");
            System.out.println(linha);
        }

        // SELECT por cativo
        System.out.println("\nFILTRANDO AS VANTAGENS DOS PLANOS POR ATIVIDADE");

        rs = plano_vantagem.selecionarVantagensPlanoPorAtividade("1");
        while (rs.next()) {
            String linha = rs.getString("uid") +" "+ rs.getString("cvantagem") +" "+
                    rs.getString("cativo") +" "+ rs.getString("uid_plano");
            System.out.println(linha);
        }

            // DELETE plano_vantagem
        // tem que ser construtor com o ID (usar function do banco)
//        plano_vantagem.removerPlanoVantagem(new Plano_vantagem(UUID.fromString("662d0911-a23f-4632-9cad-a320d31b3b3b"),
//                "Desconto na publicação de anúncios",'1',uIdPlano));

//        **************************************************************************************************************
//        CLASSE PAGAMENTO
//        **************************************************************************************************************
//        PagamentoDAO pagamento = new PagamentoDAO();

        // INSERT pagamento
//        System.out.println("\nINSERINDO PAGAMENTO");
//
//        if (pagamento.adicionarPagamento(new Pagamento("0",,0,16,
//                UUID.fromString("b7f82603-9065-4fd1-a39a-0365036b21f6"),UUID.fromString("8abb0f0a-2531-410b-97a7-cd2aae422f2a"),
//                null)) == -1) {
//            System.out.println("Não foi possível adicionar pagamento");
//        }
//        else {
//            System.out.println("Pagamento adicionado com sucesso!");
//        }

        // SELECT * pagamento - falta testar os outros selects
//        System.out.println("\nMOSTRANDO TODOS OS PAGAMENTOS");

//        ResultSet rs = pagamento.selecionarTodosPagamentos();
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cativo") +" "+
//                    rs.getDate("ddtfim") +" "+ rs.getDouble("npctdesconto") +" "+
//                    rs.getDouble("ntotal") +" "+ rs.getObject("uid_anunciante") +" "+
//                    rs.getObject("uid_plano") +" "+ rs.getObject("uid_universitario");
//            System.out.println(linha);
//        }

        // SELECT por ID
//        System.out.println("\nFILTRANDO PAGAMENTOS POR ID");

//        rs = pagamento.selecionarPagamentosPorId();
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cativo") +" "+
//                    rs.getDate("ddtfim") +" "+ rs.getDouble("npctdesconto") +" "+
//                    rs.getDouble("ntotal") +" "+ rs.getObject("uid_anunciante") +" "+
//                    rs.getObject("uid_plano") +" "+ rs.getObject("uid_universitario");
//            System.out.println(linha);
//        }

        // SELECT por cativo
//        System.out.println("\nFILTRANDO PAGAMENTOS POR ATIVIDADE");

//        rs = pagamento.selecionarPagamentosPorAtividade("0");
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cativo") +" "+
//                    rs.getDate("ddtfim") +" "+ rs.getDouble("npctdesconto") +" "+
//                    rs.getDouble("ntotal") +" "+ rs.getObject("uid_anunciante") +" "+
//                    rs.getObject("uid_plano") +" "+ rs.getObject("uid_universitario");
//            System.out.println(linha);
//        }

        // SELECT por uid_plano
//        System.out.println("\nFILTRANDO PAGAMENTOS PELO PLANO");
//
//        rs = pagamento.selecionarPagamentosPorIdPlano(UUID.fromString("ceae7210-c6e7-4155-a5ee-11514620893f"));
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cativo") +" "+
//                    rs.getDate("ddtfim") +" "+ rs.getDouble("npctdesconto") +" "+
//                    rs.getDouble("ntotal") +" "+ rs.getObject("uid_anunciante") +" "+
//                    rs.getObject("uid_plano") +" "+ rs.getObject("uid_universitario");
//            System.out.println(linha);
//        }

        // SELECT por uid_anunciante
//        System.out.println("\nFILTRANDO PAGAMENTOS PELO ANUNCIANTE");
//
//        rs = pagamento.selecionarPagamentosPorIdAnunciante(UUID.fromString("9ea77ba5-459e-4c54-b905-77538ca7de85"));
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cativo") +" "+
//                    rs.getDate("ddtfim") +" "+ rs.getDouble("npctdesconto") +" "+
//                    rs.getDouble("ntotal") +" "+ rs.getObject("uid_anunciante") +" "+
//                    rs.getObject("uid_plano") +" "+ rs.getObject("uid_universitario");
//            System.out.println(linha);
//        }

        // SELECT por uid_universitario
//        System.out.println("\nFILTRANDO PAGAMENTOS PELO UNIVERSITÁRIO");
//
//        rs = pagamento.selecionarPagamentosPorIdUniversitario(UUID.fromString("e41213f7-825d-44f6-913e-68db5a7b772d"));
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cativo") +" "+
//                    rs.getDate("ddtfim") +" "+ rs.getDouble("npctdesconto") +" "+
//                    rs.getDouble("ntotal") +" "+ rs.getObject("uid_anunciante") +" "+
//                    rs.getObject("uid_plano") +" "+ rs.getObject("uid_universitario");
//            System.out.println(linha);
//        }

        // SELECT por ddt_fim
//        System.out.println("\nMOSTRANDO PAGAMENTOS QUE TÊM A DATA DE FIM DEPOIS DE HOJE");
//
//        rs = pagamento.selecionarPagamentosPorDtFimFutura();
//        while (rs.next()) {
//            String linha = rs.getString("uid") +" "+ rs.getString("cativo") +" "+
//                    rs.getDate("ddtfim") +" "+ rs.getDouble("npctdesconto") +" "+
//                    rs.getDouble("ntotal") +" "+ rs.getObject("uid_anunciante") +" "+
//                    rs.getObject("uid_plano") +" "+ rs.getObject("uid_universitario");
//            System.out.println(linha);
//        }

        // DELETE pagamento
        if (pagamento.removerPagamento(pagamento.selecionarUid()) == -1){
            System.out.println("Não foi possível remover o pagamento");
        }
        else {
            System.out.println("Pagamento removido com sucesso!");
        }

        System.out.println("Fim");
    }
}
