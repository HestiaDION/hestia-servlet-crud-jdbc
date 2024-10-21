package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Pagamento {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;               // (UUID)
    private String cAtivo;          // (CHAR(2))
    private String dDtFim;          // (VARCHAR(10))
    private double nPctDesconto;    // (DECIMAL(10,1))
    private double nValor;          // (DECIMAL(10,2))
    private UUID uId_Anunciante;    // (UUID)
    private UUID uId_Plano;         // (UUID)
    private UUID uId_Universitario; // (UUID)

//    DEFINIÇÃO DO MÉTODO CONSTRUTOR
    public Pagamento(String cAtivo, String dDtFim, double nPctDesconto, double nValor,
                     UUID uId_Anunciante, UUID uId_Plano, UUID uId_Universitario) {
        this.uId = UUID.randomUUID();
        this.cAtivo = cAtivo;
        this.dDtFim = dDtFim;
        this.nPctDesconto = nPctDesconto;
        this.nValor = nValor;
        this.uId_Anunciante = uId_Anunciante;
        this.uId_Plano = uId_Plano;
        this.uId_Universitario = uId_Universitario;
    }
    public Pagamento(){}

//    DEFINIÇÃO DOS MÉTODOS getters
    public UUID getuId() {
        return uId;
    }

    public String getcAtivo() {
        return cAtivo;
    }

    public String getdDtFim() {
        return dDtFim;
    }

    public double getnPctDesconto() {
        return nPctDesconto;
    }

    public double getnValor() {
        return nValor;
    }

    public UUID getuId_Anunciante() {
        return uId_Anunciante;
    }

    public UUID getuId_Plano() {
        return uId_Plano;
    }

    public UUID getuId_Universitario() {
        return uId_Universitario;
    }

//    DEFINIÇÃO DOS MÉTODOS setters
    public void setuId(UUID uId) {
        this.uId = uId;
    } // Método Inútil Por Enquanto

    public void setcAtivo(String cAtivo) {
        this.cAtivo = cAtivo;
    } // Método Inútil Por Enquanto

    public void setdDtFim(String dDtFim) {
        this.dDtFim = dDtFim;
    } // Método Inútil Por Enquanto

    public void setnPctDesconto(double nPctDesconto) {
        this.nPctDesconto = nPctDesconto;
    } // Método Inútil Por Enquanto

    public void setnValor(double nValor) {
        this.nValor = nValor;
    } // Método Inútil Por Enquanto

    public void setuId_Anunciante(UUID uId_Anunciante) {
        this.uId_Anunciante = uId_Anunciante;
    } // Método Inútil Por Enquanto

    public void setuId_Plano(UUID uId_Plano) {
        this.uId_Plano = uId_Plano;
    } // Método Inútil Por Enquanto

    public void setuId_Universitario(UUID uId_Universitario) {
        this.uId_Universitario = uId_Universitario;
    } // Método Inútil Por Enquanto

//    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString() {
        return "ID do Pagamento = " + this.uId + "\nAtividade do Pagamento = " + this.cAtivo +
                "\nData de Validade do Pagamento = " + this.dDtFim +
                "\nPorcentagem de Desconto do Pagamento = " + this.nPctDesconto +
                "\nValor do Pagamento = " + this.nValor +
                "\nID do Anunciante do Pagamento = " + this.uId_Anunciante +
                "\nID do Plano do Pagamento = " + this.uId_Plano +
                "\nID do Universitário do Pagamento = " + this.uId_Universitario;
    }
}
