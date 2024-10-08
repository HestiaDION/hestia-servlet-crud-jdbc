package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Boost {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;          // (UUID)
    private String cTipoBoost; // (VARCHAR(50))
    private double nValor;     // (DECIMAL(10,2))
    private double nPctBoost;  // (DECIMAL(10,1))

//    DEFINIÇÃO DO MÉTODO CONSTRUTOR
    public Boost(String cTipoBoost, double nValor, double nPctBoost) {
        this.uId = UUID.randomUUID();
        this.cTipoBoost = cTipoBoost;
        this.nValor = nValor;
        this.nPctBoost = nPctBoost;
    }

//    DEFINIÇÃO DOS MÉTODOS getters
    public UUID getuId() {
        return uId;
    }

    public String getcTipoBoost() {
        return cTipoBoost;
    }

    public double getnValor() {
        return nValor;
    }

    public double getnPctBoost() {
        return nPctBoost;
    }

//    DEFINIÇÃO MÉTODOS setters
    public void setuId(UUID uId) {
        this.uId = uId;
    } // Método Inútil Por Enquanto

    public void setcTipoBoost(String cTipoBoost) {
        this.cTipoBoost = cTipoBoost;
    } // Método Inútil Por Enquanto

    public void setnValor(double nValor) {
        this.nValor = nValor;
    } // Método Inútil Por Enquanto

    public void setnPctBoost(double nPctBoost) {
        this.nPctBoost = nPctBoost;
    } // Método Inútil Por Enquanto

//    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString() {
        return "ID do Boost = " + this.uId + "\nTipo do Boost = " + this.cTipoBoost +
                "\nValor do Boost = " + this.nValor + "\nPorcentagem do Boost = " + this.nPctBoost;
    }
}
