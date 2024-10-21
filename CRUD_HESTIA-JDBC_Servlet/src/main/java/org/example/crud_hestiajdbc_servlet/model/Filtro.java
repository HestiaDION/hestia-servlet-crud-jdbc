package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Filtro {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;          // (UUID)
    private String cNome;      // (VARCHAR(100))
    private String cCategoria; // (VARCHAR(100))

//    DEFINIÇÃO DO MÉTODO CONSTRUTOR
    public Filtro(String cNome, String cCategoria) {
        this.uId = UUID.randomUUID();
        this.cNome = cNome;
        this.cCategoria = cCategoria;
    }
    public Filtro(){

    }
    public Filtro(UUID uId, String cNome, String cCategoria) {
        this.uId = uId;
        this.cNome = cNome;
        this.cCategoria = cCategoria;
    }

//    DEFINIÇÃO DOS MÉTODOS getters
    public UUID getuId() {
        return uId;
    }

    public String getcNome() {
        return cNome;
    }

    public String getcCategoria() {
        return cCategoria;
    }

//    DEFINIÇÃO DOS MÉTODOS setters
    public void setuId(UUID uId) {
        this.uId = uId;
    } // Método Inútil Por Enquanto

    public void setcNome(String cNome) {
        this.cNome = cNome;
    } // Método Inútil Por Enquanto

    public void setcCategoria(String cCategoria) {
        this.cCategoria = cCategoria;
    } // Método Inútil Por Enquanto

//    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString() {
        return "ID do Filtro = " + this.uId + "\nNome do Filtro = " + this.cNome +
                "\nCategoria do Filtro = " + this.cCategoria;
    }
}
