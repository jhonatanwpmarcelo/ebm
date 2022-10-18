/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.bi;

/**
 *
 * @author Emanuel
 */
public class Ligacao {
    private int id;
    private String numero;
    private String data;
    private int duracao;
    private int duracaoReal;
    private int tipo; //Setado automático na inserção do Destino; 1 - Móvel, 2 - Fixo, 3 - 0800 , 4 Publico, 
    
    
    public Ligacao(){
    super();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the duracao
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * @param duracao the duracao to set
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * @return the duracaoReal
     */
    public int getDuracaoReal() {
        return duracaoReal;
    }

    /**
     * @param duracaoReal the duracaoReal to set
     */
    public void setDuracaoReal(int duracaoReal) {
        this.duracaoReal = duracaoReal;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
   
    
    
}
