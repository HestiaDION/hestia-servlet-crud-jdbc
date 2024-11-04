package org.example.crud_hestiajdbc_servlet.model;

import java.util.UUID;

public class Filtro {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;          // (UUID)
    private String cNome;      // (VARCHAR(100))
    private String cCategoria; // (VARCHAR(100))

//    DEFINIÇÃO DOS MÉTODOS CONSTRUTORES
    public Filtro(UUID uId, String cNome, String cCategoria)
    {
        this.uId = uId;
        this.cNome = cNome;
        this.cCategoria = cCategoria;
    }

    public Filtro(String cNome, String cCategoria)
    {
        this.cNome = cNome;
        this.cCategoria = cCategoria;
    }

    public Filtro()
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

    public String getcCategoria()
    {
        return cCategoria;
    }


//    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString()
    {
        return "ID do Filtro = " + this.uId + "\nNome do Filtro = " + this.cNome +
                "\nCategoria do Filtro = " + this.cCategoria;
    }
}
