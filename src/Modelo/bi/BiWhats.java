/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.bi;

import Modelo.Cliente;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Emanuel
 */
public class BiWhats {

    private String codigoBi;
    private Cliente cliente;
    private Data data;
    
    
    
    private ArrayList<DID> DIDs;
    
    private ArrayList<DID> recebidas;
    private ArrayList<DID> efetuadas;

    private ArrayList<Ligacao> topEfetuadas;
    private ArrayList<Ligacao> topRecebidas;
    
    public BiWhats(){
    super();
    
    this.codigoBi = String.valueOf(UUID.randomUUID());
    this.DIDs = new ArrayList<DID>();
    this.cliente = new Cliente();
    this.data = new Data();
    this.recebidas = new ArrayList<DID>();
    this.efetuadas = new ArrayList<DID>();
    this.topEfetuadas = new ArrayList<Ligacao>();
    this.topRecebidas = new ArrayList<Ligacao>();
    
    }

    /**
     * @return the codigoBi
     */
    public String getCodigoBi() {
        return codigoBi;
    }

    /**
     * @param codigoBi the codigoBi to set
     */
    public void setCodigoBi(String codigoBi) {
        this.codigoBi = codigoBi;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * @return the recebidas
     */
    public ArrayList<DID> getRecebidas() {
        return recebidas;
    }

    /**
     * @param recebidas the recebidas to set
     */
    public void setRecebidas(ArrayList<DID> recebidas) {
        this.recebidas = recebidas;
    }

    /**
     * @return the efetuadas
     */
    public ArrayList<DID> getEfetuadas() {
        return efetuadas;
    }

    /**
     * @param efetuadas the efetuadas to set
     */
    public void setEfetuadas(ArrayList<DID> efetuadas) {
        this.efetuadas = efetuadas;
    }

    /**
     * @return the topEfetuadas
     */
    public ArrayList<Ligacao> getTopEfetuadas() {
        return topEfetuadas;
    }

    /**
     * @param topEfetuadas the topEfetuadas to set
     */
    public void setTopEfetuadas(ArrayList<Ligacao> topEfetuadas) {
        this.topEfetuadas = topEfetuadas;
    }

    /**
     * @return the topRecebidas
     */
    public ArrayList<Ligacao> getTopRecebidas() {
        return topRecebidas;
    }

    /**
     * @param topRecebidas the topRecebidas to set
     */
    public void setTopRecebidas(ArrayList<Ligacao> topRecebidas) {
        this.topRecebidas = topRecebidas;
    }

    /**
     * @return the DIDs
     */
    public ArrayList<DID> getDIDs() {
        return DIDs;
    }

    /**
     * @param DIDs the DIDs to set
     */
    public void setDIDs(ArrayList<DID> DIDs) {
        this.DIDs = DIDs;
    }

}
