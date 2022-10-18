/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import util.Funcoes;
import java.sql.Blob;

/**
 *
 * @author Emanuel
 */
public class Fatura extends Mensagem{
  
    
    private String nomeCliente;
    private String valor;
    private int diasVencidos;
    private String mesFaturado;
    private String ano;
    private String dataVencimento;
    private String codigoBarras;
    private String fatura;
    private String boleto;
    private String notaFiscal;
    private String numeroNota;
    private Cliente c;
  
    

    public Fatura(String codigoFatura){
    
    
    super();
    this.fatura = codigoFatura;
    this.c = new Cliente();
    }
    
    
    /**
     * @return the nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @param nomeCliente the nomeCliente to set
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

   

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return the dataVencimento
     */
    public String getDataVencimento() {
        return dataVencimento;
    }

    
    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

     /**
     * @return the fatura
     */
    public String getFatura() {
        return fatura;
    }

    /**
     * @param fatura the fatura to set
     */
    public void setFatura(String fatura) {
        this.fatura = fatura;
    }
    
    /**
     * @return the codigoBarras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * @param codigoBarras the codigoBarras to set
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    /**
     * @return the boleto
     */
    public String getBoleto() {
        return boleto;
    }

    /**
     * @param boleto the boleto to set
     */
    public void setBoleto(String boleto) throws Exception {
        this.boleto = boleto;
    }

    /**
     * @return the notaFiscal
     */
    public String getNotaFiscal() {
        return notaFiscal;
    }

    /**
     * @param notaFiscal the notaFiscal to set
     */
    public void setNotaFiscal(String notaFiscal) throws Exception {
        this.notaFiscal =  notaFiscal;
    }

    /**
     * @return the mesFaturado
     */
    public String getMesFaturado() {
        return mesFaturado;
    }

    /**
     * @param mesFaturado the mesFaturado to set
     */
    public void setMesFaturado(String mesFaturado) {
        this.mesFaturado = new Funcoes().Maiuscula(mesFaturado);
    }

    /**
     * @return the numeroNota
     */
    public String getNumeroNota() {
        return numeroNota;
    }

    /**
     * @param numeroNota the numeroNota to set
     */
    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }

    /**
     * @return the c
     */
    public Cliente getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(Cliente c) {
        this.c = c;
    }

    /**
     * @return the diasVencidos
     */
    public int getDiasVencidos() {
        return diasVencidos;
    }

    /**
     * @param diasVencidos the diasVencidos to set
     */
    public void setDiasVencidos(int diasVencidos) {
        this.diasVencidos = diasVencidos;
    }

 

    
}
