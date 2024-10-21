package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Admin {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;      // (UUID)
    private String cNome;  // (VARCHAR(100))
    private String cEmail; // (VARCHAR(266))
    private String cSenha; // (VARCHAR(100))

//    DEFINIÇÃO DO MÉTODO CONSTRUTOR
    public Admin(String cNome, String cEmail, String cSenha) {
        this.uId = UUID.randomUUID();
        this.cNome = cNome;
        this.cEmail = cEmail;
        this.cSenha = cSenha;
    }
//    DEFINIÇÃO DO MÉTODO CONSTRUTOR VAZIO
    public Admin() {

    }
    public Admin( UUID uId, String cNome, String cEmail, String cSenha) {
        this.uId = UUID.randomUUID();
        this.cNome = cNome;
        this.cEmail = cEmail;
        this.cSenha = cSenha;
    }

//    DEFINIÇÃO MÉTODOS getters
    public UUID getuId() {
        return uId;
    }

    public String getcNome() {
        return cNome;
    }

    public String getcEmail() {
        return cEmail;
    }

    public String getcSenha() {
        return cSenha;
    }

//    DEFINIÇÃO DOS MÉTODOS setters
    public void setuId(UUID uId) {
        this.uId = uId;
    } // Método Inútil Por Enquanto

    public void setcNome(String cNome) {
        this.cNome = cNome;
    } // Método Inútil Por Enquanto

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    } // Método Inútil Por Enquanto

    public void setcSenha(String cSenha) {
        this.cSenha = cSenha;
    } // Método Inútil Por Enquanto

//    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString() {
        return "ID do Admin = " + this.uId + "\nNome do Admin = " + this.cNome +
                "\nE-mail do Admin = " + this.cEmail + "\nSenha do Admin = " + this.cSenha;
    }
}
