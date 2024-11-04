package org.example.crud_hestiajdbc_servlet.model;

import java.sql.Date;
import java.util.UUID;

public class Pagamento {
//    DEFINIÇÃO DOS ATRIBUTOS DA CLASSE
    private UUID uId;                   // (UUID)
    private String cAtivo;              // (VARCHAR(2))
    private Date dDtFim;                // (DATE)
    private double nPctDesconto;        // (DECIMAL(10,1))
    private double nTotal;              // (DECIMAL(10,2))
    private String cEmailAnunciante;    // (Parâmetro 2 da FN_Anunciante_Id)
    private String cNmPlano;            // (Parâmetro 1 da FN_Plano_Id)
    private String cEmailUniversitario; // (Parâmetro 2 da FN_Universitario_Id)

//    DEFINIÇÃO DOS MÉTODOS CONSTRUTORES
    public Pagamento(UUID uId, String cAtivo, Date dDtFim, double nPctDesconto, double nTotal,
                     String cEmailUsuario, String cNmPlano, int tipoUsuario)
    // tipoUsuario recbe 0 se for anunciante e 1 se for universitario
    {
        if (tipoUsuario == 0){
            this.uId = uId;
            this.cAtivo = cAtivo;
            this.dDtFim = dDtFim;
            this.nPctDesconto = nPctDesconto;
            this.nTotal = nTotal;
            this.cEmailAnunciante = cEmailUsuario;
            this.cNmPlano = cNmPlano;
            this.cEmailUniversitario = null;
        } else if (tipoUsuario == 1) {
            this.uId = uId;
            this.cAtivo = cAtivo;
            this.dDtFim = dDtFim;
            this.nPctDesconto = nPctDesconto;
            this.nTotal = nTotal;
            this.cEmailUniversitario = cEmailUsuario;
            this.cNmPlano = cNmPlano;
            this.cEmailAnunciante = null;
        }
    }

    public Pagamento(String cAtivo, Date dDtFim, double nPctDesconto, double nTotal,
                     String cEmailUsuario, String cNmPlano, int tipoUsuario)
    // tipoUsuario recbe 0 se for anunciante e 1 se for universitario
    {
        if (tipoUsuario == 0){
            this.cAtivo = cAtivo;
            this.dDtFim = dDtFim;
            this.nPctDesconto = nPctDesconto;
            this.nTotal = nTotal;
            this.cEmailAnunciante = cEmailUsuario;
            this.cNmPlano = cNmPlano;
        } else if (tipoUsuario == 1) {
            this.cAtivo = cAtivo;
            this.dDtFim = dDtFim;
            this.nPctDesconto = nPctDesconto;
            this.nTotal = nTotal;
            this.cEmailUniversitario = cEmailUsuario;
            this.cNmPlano = cNmPlano;

        }
    }

    public Pagamento()
    {
    }

//    DEFINIÇÃO DOS MÉTODOS getters
    public UUID getuId()
    {
        return uId;
    }

    public String getcAtivo()
    {
        return cAtivo;
    }

    public Date getdDtFim()
    {
        return dDtFim;
    }

    public double getnPctDesconto()
    {
        return nPctDesconto;
    }

    public double getnTotal()
    {
        return nTotal;
    }

    public String getcEmailAnunciante() {
        return cEmailAnunciante;
    }
    
    public String getcNmPlano() {
        return cNmPlano;
    }

    public String getcEmailUniversitario() {
        return cEmailUniversitario;
    }


    //    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString()
    {
        if (this.cEmailAnunciante != null) {
            return "ID do Pagamento = " + this.uId + "\nAtividade do Pagamento = " + this.cAtivo +
                    "\nData de Validade do Pagamento = " + this.dDtFim +
                    "\nPorcentagem de Desconto do Pagamento = " + this.nPctDesconto +
                    "\nTotal do Pagamento = " + this.nTotal +
                    "\nE-mail do Anunciante do pagamento = "+this.cEmailAnunciante+
                    "\nNome do plano sendo pago = "+ this.cNmPlano;
        }
        else{
            return "ID do Pagamento = " + this.uId + "\nAtividade do Pagamento = " + this.cAtivo +
                    "\nData de Validade do Pagamento = " + this.dDtFim +
                    "\nPorcentagem de Desconto do Pagamento = " + this.nPctDesconto +
                    "\nTotal do Pagamento = " + this.nTotal +
                    "\nE-mail do Universitario do pagamento = "+this.cEmailUniversitario+
                    "\nNome do plano sendo pago = "+ this.cNmPlano;
        }
        
    }
}
