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
public class DID {
    
    private String numero;
    private int ligacoes;
    private int lFixo;
    private int duracaoSegundosF;
    private int duracaoMSegundosF;
    private String duracaoTotalF;
    private String duracaoMediaF;
    private int fNumerosDiferentes;
    private int lMovel;
    private int duracaoSegundosM;
    private int duracaoMSegundosM;
    private String duracaoTotalM;
    private String duracaoMediaM;
    private int mNumerosDiferentes;
    
    private double porcentagemUso;
    
    
    public DID(){
    super();
    
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
     * @return the ligacoes
     */
    public int getLigacoes() {
        
        return lFixo + lMovel;
//        return ligacoes;
    }

    /**
     * @param ligacoes the ligacoes to set
     */
    public void setLigacoes(int ligacoes) {
        this.ligacoes = ligacoes;
    }

    /**
     * @return the lFixo
     */
    public int getlFixo() {
        return lFixo;
    }

    /**
     * @param lFixo the lFixo to set
     */
    public void setlFixo(int lFixo) {
        this.lFixo = lFixo;
    }

    /**
     * @return the duracaoTotalF
     */
    public String getDuracaoTotalF() {
        return duracaoTotalF;
    }

    /**
     * @param duracaoTotalF the duracaoTotalF to set
     */
    public void setDuracaoTotalF(String duracaoTotalF) {
        this.duracaoTotalF = duracaoTotalF;
    }

    /**
     * @return the duracaoMediaF
     */
    public String getDuracaoMediaF() {
        return duracaoMediaF;
    }

    /**
     * @param duracaoMediaF the duracaoMediaF to set
     */
    public void setDuracaoMediaF(String duracaoMediaF) {
        this.duracaoMediaF = duracaoMediaF;
    }

    /**
     * @return the lMovel
     */
    public int getlMovel() {
        return lMovel;
    }

    /**
     * @param lMovel the lMovel to set
     */
    public void setlMovel(int lMovel) {
        this.lMovel = lMovel;
    }

    /**
     * @return the duracaoTotalM
     */
    public String getDuracaoTotalM() {
        return duracaoTotalM;
    }

    /**
     * @param duracaoTotalM the duracaoTotalM to set
     */
    public void setDuracaoTotalM(String duracaoTotalM) {
        this.duracaoTotalM = duracaoTotalM;
    }

    /**
     * @return the duracaoMediaM
     */
    public String getDuracaoMediaM() {
        return duracaoMediaM;
    }

    /**
     * @param duracaoMediaM the duracaoMediaM to set
     */
    public void setDuracaoMediaM(String duracaoMediaM) {
        this.duracaoMediaM = duracaoMediaM;
    }

    /**
     * @param fNumerosDiferentes the fNumerosDiferentes to set
     */
    public void setfNumerosDiferentes(int fNumerosDiferentes) {
        this.fNumerosDiferentes = fNumerosDiferentes;
    }

    /**
     * @return the mNumerosDiferentes
     */
    public int getmNumerosDiferentes() {
        return mNumerosDiferentes;
    }

    /**
     * @param mNumerosDiferentes the mNumerosDiferentes to set
     */
    public void setmNumerosDiferentes(int mNumerosDiferentes) {
        this.mNumerosDiferentes = mNumerosDiferentes;
    }

    /**
     * @return the fNumerosDiferentes
     */
    public int getfNumerosDiferentes() {
        return fNumerosDiferentes;
    }

    /**
     * @return the duracaoSegundosF
     */
    public int getDuracaoSegundosF() {
        return duracaoSegundosF;
    }

    /**
     * @param duracaoSegundosF the duracaoSegundosF to set
     */
    public void setDuracaoSegundosF(int duracaoSegundosF) {
        this.duracaoSegundosF = duracaoSegundosF;
    }

    /**
     * @return the duracaoMSegundosF
     */
    public int getDuracaoMSegundosF() {
        return duracaoMSegundosF;
    }

    /**
     * @param duracaoMSegundosF the duracaoMSegundosF to set
     */
    public void setDuracaoMSegundosF(int duracaoMSegundosF) {
        this.duracaoMSegundosF = duracaoMSegundosF;
    }

    /**
     * @return the duracaoSegundosM
     */
    public int getDuracaoSegundosM() {
        return duracaoSegundosM;
    }

    /**
     * @param duracaoSegundosM the duracaoSegundosM to set
     */
    public void setDuracaoSegundosM(int duracaoSegundosM) {
        this.duracaoSegundosM = duracaoSegundosM;
    }

    /**
     * @return the duracaoMSegundosM
     */
    public int getDuracaoMSegundosM() {
        return duracaoMSegundosM;
    }

    /**
     * @param duracaoMSegundosM the duracaoMSegundosM to set
     */
    public void setDuracaoMSegundosM(int duracaoMSegundosM) {
        this.duracaoMSegundosM = duracaoMSegundosM;
    }

    /**
     * @return the porcentagemUso
     */
    public double getPorcentagemUso() {
        return porcentagemUso;
    }

    /**
     * @param porcentagemUso the porcentagemUso to set
     */
    public void setPorcentagemUso(double porcentagemUso) {
        this.porcentagemUso = porcentagemUso;
    }


    
    
}
