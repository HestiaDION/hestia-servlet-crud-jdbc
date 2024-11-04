package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Plano {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;            // (UUID)
    private String cNome;        // (VARCHAR(100))
    private String cTipoUsuario; // ()
    private double nValor;       // (DECIMAL(10,2))
    private String cDescricao;   // (VARCHAR(MAX))

//    DEFINIÇÃO DOS MÉTODOS CONSTRUTORES
    public Plano(UUID uId, String cNome, String cTipoUsuario, double nValor, String cDescricao)
    {
        this.uId = uId;
        this.cNome = cNome;
        this.cTipoUsuario = cTipoUsuario;
        this.nValor = nValor;
        this.cDescricao = cDescricao;
    }

    public Plano(String cNome, String cTipoUsuario, double nValor, String cDescricao)
    {
        this.cNome = cNome;
        this.cTipoUsuario = cTipoUsuario;
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

    public String getcTipoUsuario() {
        return cTipoUsuario;
    }

    public double getnValor()
    {
        return nValor;
    }

    public String getcDescricao()
    {
        return cDescricao;
    }

//    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString()
    {
        return "ID do Plano = " + this.uId +
                "\nNome do Plano = " + this.cNome +
                "\nTipo de usuário do plano = " + this.cTipoUsuario +
                "\nValor do Plano = " + this.nValor +
                "\nDescrição do Plano = " + this.cDescricao;
    }
}
