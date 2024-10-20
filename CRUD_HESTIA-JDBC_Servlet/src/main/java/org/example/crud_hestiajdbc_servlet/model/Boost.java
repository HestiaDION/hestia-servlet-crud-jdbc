package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Boost {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;          // (UUID)
    private String cNmBoost; // (VARCHAR(50))
    private double nValor;     // (DECIMAL(10,2))
    private double nPctBoost;  // (DECIMAL(10,1))
    private String cDescricao; // (TEXT)

//    DEFINIÇÃO DO MÉTODO CONSTRUTOR
    public Boost(String cNmBoost, double nValor, double nPctBoost, String cDescricao) {
        this.uId = UUID.randomUUID();
        this.cNmBoost = cNmBoost;
        this.nValor = nValor;
        this.nPctBoost = nPctBoost;
        this.cDescricao = cDescricao;
    }
    public Boost() {

    }
    public Boost(UUID uId, String cTipoBoost, double nValor, double nPctBoost) {
        this.uId = uId;
        this.cNmBoost = cTipoBoost;
        this.nValor = nValor;
        this.nPctBoost = nPctBoost;
    }

//    DEFINIÇÃO DOS MÉTODOS getters
    public UUID getuId() {
        return uId;
    }

    public String getcNmBoost() {
        return cNmBoost;
    }

    public double getnValor() {
        return nValor;
    }

    public double getnPctBoost() {
        return nPctBoost;
    }

    public String getcDescricao() {
        return cDescricao;
    }

    //    DEFINIÇÃO MÉTODOS setters
    public void setuId(UUID uId) {
        this.uId = uId;
    } // Método Inútil Por Enquanto

    public void setcTipoBoost(String cTipoBoost) {
        this.cNmBoost = cTipoBoost;
    } // Método Inútil Por Enquanto

    public void setnValor(double nValor) {
        this.nValor = nValor;
    } // Método Inútil Por Enquanto

    public void setnPctBoost(double nPctBoost) {
        this.nPctBoost = nPctBoost;
    } // Método Inútil Por Enquanto

    public void setcDescricao(String cDescricao) {
        this.cDescricao = cDescricao;
    }

    //    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString() {
        return "ID do Boost = " + this.uId + "\nTipo do Boost = " + this.cNmBoost +
                "\nValor do Boost = " + this.nValor + "\nPorcentagem do Boost = " + this.nPctBoost + "\nDescrição do Boost: = " + this.cDescricao;
    }
}
