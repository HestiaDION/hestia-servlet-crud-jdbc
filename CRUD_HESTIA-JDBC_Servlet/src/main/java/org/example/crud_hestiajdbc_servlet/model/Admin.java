package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Admin {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;      // (UUID)
    private String cNome;  // (VARCHAR(100))
    private String cEmail; // (VARCHAR(266))
    private String cFoto;  // (TEXT)
    private String cSenha; // (VARCHAR(100))
    private char cLogin;   // (VARCHAR(1))

//    DEFINIÇÃO DOS MÉTODOS CONSTRUTORES
    public Admin(UUID uId, String cNome, String cEmail, String cFoto, String cSenha)
    {
        this.uId = uId;
        this.cNome = cNome;
        this.cEmail = cEmail;
        this.cFoto = cFoto;
        this.cSenha = cSenha;
    }

    public Admin(String cNome, String cEmail, String cFoto, String cSenha)
    {
        this.cNome = cNome;
        this.cEmail = cEmail;
        this.cFoto = cFoto;
        this.cSenha = cSenha;
    }

    public Admin()
    {
    }

//    DEFINIÇÃO MÉTODOS getters
    public UUID getuId()
    {
        return uId;
    }

    public String getcNome()
    {
        return cNome;
    }

    public String getcEmail()
    {
        return cEmail;
    }

    public String getcFoto()
    {
        return cFoto;
    }

    public String getcSenha()
    {
        return cSenha;
    }

    public char getcLogin() {
        return cLogin;
    }


//    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString()
    {

        return "ID do Admin = " + this.uId +
                "\nNome do Admin = " + this.cNome +
                "\nE-mail do Admin = " + this.cEmail +
                "\nStream da Foto do Admin = " + this.cFoto +
                "\nSenha do Admin = " + this.cSenha +
                "\nLogin do Admin = " + this.cLogin;
    }
}
