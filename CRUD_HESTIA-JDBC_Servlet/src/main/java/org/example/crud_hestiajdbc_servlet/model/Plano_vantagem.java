package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Plano_vantagem {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;         // (UUID)
    private String cVantagem; // (VARCHAR(100))
    private char cAtivo;      // (CHAR(1))
    private UUID uId_Plano;   // (UUID)

//    DEFINIÇÃO DOS MÉTODOS CONSTRUTORES
    public Plano_vantagem(UUID uId, String cVantagem, char cAtivo, UUID uId_Plano) {
        this.uId = uId;
        this.cVantagem = cVantagem;
        this.cAtivo = cAtivo;
        this.uId_Plano = uId_Plano;
    }

    public Plano_vantagem(String cVantagem, char cAtivo, UUID uId_Plano) {
        this.cVantagem = cVantagem;
        this.cAtivo = cAtivo;
        this.uId_Plano = uId_Plano;
    }

    public Plano_vantagem()
    {

    }

//    DEFINIÇÃO DOS MÉTODOS getters
    public UUID getuId() {
        return uId;
    }

    public String getcVantagem() {
        return cVantagem;
    }

    public char getcAtivo() {
        return cAtivo;
    }

    public UUID getuId_Plano() {
        return uId_Plano;
    }

//    DEFINIÇÃO DOS MÉTODOS setters
    public void setuId(UUID uId) {
        this.uId = uId;
    } // Método Inútil Por Enquanto

    public void setcVantagem(String cVantagem) {
        this.cVantagem = cVantagem;
    } // Método Inútil Por Enquanto

    public void setcAtivo(char cAtivo) {
        this.cAtivo = cAtivo;
    } // Método Inútil Por Enquanto

    public void setuId_Plano(UUID uId_Plano) {
        this.uId_Plano = uId_Plano;
    } // Método Inútil Por Enquanto

//    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString() {
        return "ID da Vantagem do Plano = " + this.uId +
                "\nDefinição da Vantagem do Plano = " + this.cVantagem +
                "\nAtividade da Vantagem do Plano = " + this.cAtivo +
                "\nID do Plano da Vantagem = " + this.uId_Plano;
    }
}
