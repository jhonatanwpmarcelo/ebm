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
public class Mensagem {

    private String webhook;
    private String token;
    private String fatura;
    private String session;
    private String number;
    private String acao;
    private String text;

    public Mensagem() {

        super();

        this.webhook = "https://json.ebmtecnologias.com.br/EBM/Tecnologias/interno/hookwhatsapp";
        this.number = "558007775162-1619995449";

    }

    public Mensagem(String mensagem, int tipo) {

        super();

        this.number = "558007775162-1619995449";
        this.text = mensagem;
        getSession(tipo);

    }
    
     public Mensagem(String mensagem, int tipo,  String numero) {

        super();

        this.number = numero;
        this.text = mensagem;
        getSession(tipo);

    }

    /**
     * @return the hook
     */
    public String getWebHook() {
        return webhook;
    }

    /**
     * @param hook the hook to set
     */
    public void setWebHook(String webhook) {
        this.webhook = webhook;
    }

    /**
     * @return the session
     */
    public String getSession(int tipo) {

        if (tipo == 1) {

            this.session = "sessaodo0800mano";
        } else {

            this.session = "novaSessaoDoFixo";
        }
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * @return the acao
     */
    public String getAcao() {
        return acao;
    }

    /**
     * @param acao the acao to set
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

   

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
