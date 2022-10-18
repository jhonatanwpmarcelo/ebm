/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Emanuel
 */
public class Cliente {

    private int idInterno;
    private int idMagnus;

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private boolean PJ;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    private ArrayList<WhatsApp> whatsapp;

    private boolean multa;
    private String diaVencimento;

    public Cliente() {
        
        super();

        this.whatsapp = new ArrayList<WhatsApp>();
    }

    /**
     * @return the idMagnus
     */
    public int getIdMagnus() {
        return idMagnus;
    }

    /**
     * @param idMagnus the idMagnus to set
     */
    public void setIdMagnus(int idMagnus) {
        this.idMagnus = idMagnus;
    }

    /**
     * @return the idInterno
     */
    public int getIdInterno() {
        return idInterno;
    }

    /**
     * @param idInterno the idInterno to set
     */
    public void setIdInterno(int idInterno) {
        this.idInterno = idInterno;
    }

    /**
     * @return the nomeFantasia
     */
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    /**
     * @param nomeFantasia the nomeFantasia to set
     */
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    /**
     * @return the razaoSocial
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @param razaoSocial the razaoSocial to set
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the whatsapp
     */
    public ArrayList<WhatsApp> getWhatsapp() {
        return whatsapp;
    }

    /**
     * @param whatsapp the whatsapp to set
     */
    public void setWhatsapp(ArrayList<WhatsApp> whatsapp) {
        this.whatsapp = whatsapp;
    }

    /**
     * @return the multa
     */
    public boolean isMulta() {
        return multa;
    }

    /**
     * @param multa the multa to set
     */
    public void setMulta(boolean multa) {
        this.multa = multa;
    }

    /**
     * @return the diaVencimento
     */
    public String getDiaVencimento() {
        return diaVencimento;
    }

    /**
     * @param diaVencimento the diaVencimento to set
     */
    public void setDiaVencimento(String diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    /**
     * @return the PJ
     */
    public boolean isPJ() {
        return PJ;
    }

    /**
     * @param PJ the PJ to set
     */
    public void setPJ(boolean PJ) {
        this.PJ = PJ;
    }

}
