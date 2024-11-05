package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Plano_vantagem {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;         // (UUID)
    private String cVantagem; // (VARCHAR(100))
    private char cAtivo;      // (VARCHAR(1))
    private String cNmPlano;  // (Parâmetro 1 do FN_Plano_Id)

//    DEFINIÇÃO DOS MÉTODOS CONSTRUTORES
    public Plano_vantagem(UUID uId, String cVantagem, char cAtivo, String cNmPlano)
    {
        this.uId = uId;
        this.cVantagem = cVantagem;
        this.cAtivo = cAtivo;
        this.cNmPlano = cNmPlano;
    }

    public Plano_vantagem(String cVantagem, char cAtivo, String cNmPlano)
    {
        this.cVantagem = cVantagem;
        this.cAtivo = cAtivo;
        this.cNmPlano = cNmPlano;
    }

    public Plano_vantagem()
    {
    }

//    DEFINIÇÃO DOS MÉTODOS getters
    public UUID getuId()
    {
        return uId;
    }

    public String getcVantagem()
    {
        return cVantagem;
    }

    public char getcAtivo()
    {
        return cAtivo;
    }

    public String getcNmPlano()
    {
        return cNmPlano;
    }


//    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString()
    {
        return "ID da Vantagem do Plano = " + this.uId +
                "\nDefinição da Vantagem do Plano = " + this.cVantagem +
                "\nAtividade da Vantagem do Plano = " + this.cAtivo +
                "\nID do Plano da Vantagem = " + this.cNmPlano;
    }
}
