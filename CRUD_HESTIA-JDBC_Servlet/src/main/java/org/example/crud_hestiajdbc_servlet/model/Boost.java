package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Boost {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;          // (UUID)
    private String cNmBoost;   // (VARCHAR(50))
    private double nValor;     // (DECIMAL(10,2))
    private double nPctBoost;  // (DECIMAL(10,1))
    private String cDescricao; // (TEXT)

//    DEFINIÇÃO DOS MÉTODOS CONSTRUTORES
    public Boost(UUID uId, String cNmBoost, double nValor, double nPctBoost, String cDescricao) {
        this.uId = uId;
        this.cNmBoost = cNmBoost;
        this.nValor = nValor;
        this.nPctBoost = nPctBoost;
        this.cDescricao = cDescricao;
    }

    public Boost(String cNmBoost, double nValor, double nPctBoost, String cDescricao) {
        this.cNmBoost = cNmBoost;
        this.nValor = nValor;
        this.nPctBoost = nPctBoost;
        this.cDescricao = cDescricao;
    }

    public Boost()
    {
    }

//    DEFINIÇÃO DOS MÉTODOS getters
    public UUID getuId()
    {
        return uId;
    }

    public String getcNmBoost()
    {
        return cNmBoost;
    }

    public double getnValor()
    {
        return nValor;
    }

    public double getnPctBoost()
    {
        return nPctBoost;
    }

    public String getcDescricao()
    {
        return cDescricao;
    }


    //    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString()
    {
        return "ID do Boost = " + this.uId + "\nTipo do Boost = " + this.cNmBoost +
                "\nValor do Boost = " + this.nValor + "\nPorcentagem do Boost = " + this.nPctBoost + "\nDescrição do Boost: = " + this.cDescricao;
    }
}
