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
    private String cUserAnunciante;     // (Parâmetro 1 da FN_Anunciante_Id)
    private String cEmailAnunciante;    // (Parâmetro 2 da FN_Anunciante_Id)
    private String cNmPlano;            // (Parâmetro 1 da FN_Plano_Id)
    private String cUserUniversitario;  // (Parâmetro 1 da FN_Universitario_Id)
    private String cEmailUniversitario; // (Parâmetro 2 da FN_Universitario_Id)
    private String cDNEUniversitario;   // (Parâmetro 3 da FN_Universitario_Id)

//    DEFINIÇÃO DOS MÉTODOS CONSTRUTORES
    public Pagamento(UUID uId, String cAtivo, Date dDtFim, double nPctDesconto, double nTotal,
                     String cUserAnunciante, String cEmailAnunciante, String cNmPlano, String cUserUniversitario, String cEmailUniversitario, String cDNEUniversitario)
    {
        this.uId = uId;
        this.cAtivo = cAtivo;
        this.dDtFim = dDtFim;
        this.nPctDesconto = nPctDesconto;
        this.nTotal = nTotal;
        this.cUserAnunciante = cUserAnunciante;
        this.cEmailAnunciante = cEmailAnunciante;
        this.cNmPlano = cNmPlano;
        this.cUserUniversitario = cUserUniversitario;
        this.cEmailUniversitario = cEmailUniversitario;
        this.cDNEUniversitario = cDNEUniversitario;
        
    }

    public Pagamento(String cAtivo, Date dDtFim, double nPctDesconto, double nTotal,
                     String cUserAnunciante, String cEmailAnunciante, String cNmPlano, String cUserUniversitario, String cEmailUniversitario, String cDNEUniversitario)
    {
        this.cAtivo = cAtivo;
        this.dDtFim = dDtFim;
        this.nPctDesconto = nPctDesconto;
        this.cUserAnunciante = cUserAnunciante;
        this.cEmailAnunciante = cEmailAnunciante;
        this.cNmPlano = cNmPlano;
        this.cUserUniversitario = cUserUniversitario;
        this.cEmailUniversitario = cEmailUniversitario;
        this.cDNEUniversitario = cDNEUniversitario;
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

    public String getcUserAnunciante() {
        return cUserAnunciante;
    }
    
    public String getcEmailAnunciante() {
        return cEmailAnunciante;
    }
    
    public String getcNmPlano() {
        return cNmPlano;
    }
    
    public String getcUserUniversitario() {
        return cUserUniversitario;
    }
    
    public String getcEmailUniversitario() {
        return cEmailUniversitario;
    }
    
    public String getcDNEUniversitario() {
        return cDNEUniversitario;
    }

//    DEFINIÇÃO DOS MÉTODOS setters
    public void setuId(UUID uId)
    {
        this.uId = uId;
    } 

    public void setcAtivo(String cAtivo)
    {
        this.cAtivo = cAtivo;
    } 

    public void setdDtFim(Date dDtFim)
    {
        this.dDtFim = dDtFim;
    } 

    public void setnPctDesconto(double nPctDesconto)
    {
        this.nPctDesconto = nPctDesconto;
    } 

    public void setnTotal(double nTotal)
    {
        this.nTotal = nTotal;
    }

    public void setcUserAnunciante(String cUserAnunciante) {
        this.cUserAnunciante = cUserAnunciante;
    }

    public void setcEmailAnunciante(String cEmailAnunciante) {
        this.cEmailAnunciante = cEmailAnunciante;
    }

    public void setcNmPlano(String cNmPlano) {
        this.cNmPlano = cNmPlano;
    }

    public void setcUserUniversitario(String cUserUniversitario) {
        this.cUserUniversitario = cUserUniversitario;
    }

    public void setcEmailUniversitario(String cEmailUniversitario) {
        this.cEmailUniversitario = cEmailUniversitario;
    }

    public void setcDNEUniversitario(String cDNEUniversitario) {
        this.cDNEUniversitario = cDNEUniversitario;
    }

    //    DEFINIÇÃO DO MÉTODO toString
    @Override
    public String toString()
    {
        if (this.cEmailAnunciante != null || this.cUserAnunciante != null) {
            return "ID do Pagamento = " + this.uId + "\nAtividade do Pagamento = " + this.cAtivo +
                    "\nData de Validade do Pagamento = " + this.dDtFim +
                    "\nPorcentagem de Desconto do Pagamento = " + this.nPctDesconto +
                    "\nTotal do Pagamento = " + this.nTotal +
                    "\nUsername do Anunciante do pagamento = " + this.cUserAnunciante+
                    "\nE-mail do Anunciante do pagamento = "+this.cEmailAnunciante+
                    "\nNome do plano sendo pago = "+ this.cNmPlano;
        }
        else{
            return "ID do Pagamento = " + this.uId + "\nAtividade do Pagamento = " + this.cAtivo +
                    "\nData de Validade do Pagamento = " + this.dDtFim +
                    "\nPorcentagem de Desconto do Pagamento = " + this.nPctDesconto +
                    "\nTotal do Pagamento = " + this.nTotal +
                    "\nUsername do Universitario do pagamento = " + this.cUserUniversitario+
                    "\nE-mail do Universitario do pagamento = "+this.cEmailUniversitario+
                    "\nDNE do Universitario do pagamento = "+this.cDNEUniversitario+
                    "\nNome do plano sendo pago = "+ this.cNmPlano;
        }
        
    }
}
