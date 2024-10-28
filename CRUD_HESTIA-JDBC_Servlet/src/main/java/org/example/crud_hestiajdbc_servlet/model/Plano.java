package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Plano {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;          // (UUID)
    private String cNome;      // (VARCHAR(100))
    private double nValor;     // (DECIMAL(10,2))
    private String cDescricao; // (VARCHAR(MAX))

//    DEFINIÇÃO DOS MÉTODOS CONSTRUTORES
    public Plano(UUID uId, String cNome, double nValor, String cDescricao)
    {
        this.uId = uId;
        this.cNome = cNome;
        this.nValor = nValor;
        this.cDescricao = cDescricao;
    }

    public Plano(String cNome, double nValor, String cDescricao)
    {
        this.cNome = cNome;
        this.nValor = nValor;
        this.cDescricao = cDescricao;
    }

    public Plano()
    {
    }

//    DEFINIÇÃO DOS MÉTODOS getters
    public UUID getuId()
    {
        return uId;
    }

    public String getcNome()
    {
        return cNome;
    }

    public double getnValor()
    {
        return nValor;
    }

    public String getcDescricao()
    {
        return cDescricao;
    }

//    DEFINIÇÃO DOS MÉTODOS setters
    public void setuId(UUID uId)
    {
        this.uId = uId;
    } // Método Inútil Por Enquanto

    public void setcNome(String cNome)
    {
        this.cNome = cNome;
    } // Método Inútil Por Enquanto

    public void setnValor(double nValor)
    {
        this.nValor = nValor;
    } // Método Inútil Por Enquanto

    public void setcDescricao(String cDescricao)
    {
        this.cDescricao = cDescricao;
    } // Método Inútil Por Enquanto

//    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString()
    {
        return "ID do Plano = " + this.uId + "\nNome do Plano = " + this.cNome +
                "\nValor do Plano = " + this.nValor + "\nDescrição do Plano = " + this.cDescricao;
    }
}
