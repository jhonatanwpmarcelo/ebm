/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Emanuel
 */
public class WhatsApp {

    private String nome;
    private String numero;
    private boolean financeiro;
    private boolean tecnico;
    private boolean bi;

    public WhatsApp() {

        super();
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the financeiro
     */
    public boolean isFinanceiro() {
        return financeiro;
    }

    /**
     * @param financeiro the financeiro to set
     */
    public void setFinanceiro(int financeiro) {

        this.financeiro = false;
        if (financeiro == 1) {
            this.financeiro = true;
        }

    }

    /**
     * @return the tecnico
     */
    public boolean isTecnico() {
        return tecnico;
    }

    /**
     * @param tecnico the tecnico to set
     */
    public void setTecnico(int tecnico) {
        this.tecnico = false;
    
        if(tecnico == 1){
        
        this.tecnico = true;
        }
    
    }
    
    

    /**
     * @return the bi
     */
    public boolean isBi() {
        return bi;
    }

    /**
     * @param bi the bi to set
     */
    public void setBi(int bi) {
        this.bi = false;
        
        if(bi == 1){
        this.bi = true;
        }
   }

}
