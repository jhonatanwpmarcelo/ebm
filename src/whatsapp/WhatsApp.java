/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsapp;

import DAO.BiDAO;
import DAO.FaturaDAO;
import Modelo.Cliente;
import Modelo.Fatura;
import Modelo.Mensagem;
import Modelo.bi.BiWhats;
import util.Funcoes;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author Emanuel
 */
public class WhatsApp {

    final static String linkApi = "http://whatsapp.ebmtecnologias.com.br:3333/";
    final static String linkEnvio = "https://json.ebmtecnologias.com.br/EBM/Tecnologias/interno/whatsapp/";
    //final static String linkEnvio = "http://10.1.5.203:8080/EBM/Tecnologias/interno/whatsapp/";

    public static Gson g = new Gson();

    //para envio
    private String autenticacao; // Aqui será um token único para autorizar o envio das mensagens
    private String token; //Envio - serve para informar qual o grupo de destino da EBM // Necessita que isGroupMsg seja true
    private Boolean isGroupMsg;  //Usado para informar se vai ser enviado para grupo ou para pessoa separada
    private String session;  //vai pegar daqui de dentro
    private String number; //para quando isGroupMsg é false
    private String text; //Texto a ser enviado / Link a ser enviado
    private String name;  //nome do arquivo
    private String path;  // Base64do Arquivo a ser enviado
    private int tipo; //1 - mensagem, 2 - file, 3 link
    private String caption;

    public WhatsApp() {
        super();
        this.tipo = 1;
        this.autenticacao = "c61e6860-aff3-11eb-a32e-0a796391454e";
        this.isGroupMsg = false;
    }

    /**
     * @return the autenticacao
     */
    public String getAutenticacao() {
        return autenticacao;
    }

    /**
     * @param autenticacao the autenticacao to set
     */
    public void setAutenticacao(String autenticacao) {
        this.autenticacao = autenticacao;
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
     * @return the isGroupMsg
     */
    public Boolean getIsGroupMsg() {
        return isGroupMsg;
    }

    /**
     * @param isGroupMsg the isGroupMsg to set
     */
    public void setIsGroupMsg(Boolean isGroupMsg) {
        this.isGroupMsg = isGroupMsg;
    }

    /**
     * @return the session
     */
    public String getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(String session) {
        this.session = session;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the file
     */
    public String getPath() {
        return path;
    }

    /**
     * @param file the file to set
     */
    public void setPath(String path) {
        this.path = path;
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

    /**
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        String retorno = "";

        while (!retorno.equals("99")) {

            retorno = JOptionPane.showInputDialog(null, "Iniciar Sessão\n"
                    + "\n"
                    /* + "1 - Conectar 0800\n"
                    //     + "2 - Conectar Fixo\n"*/
                    + "3 - Enviar Faturas\n"
                    + "4 - Enviar Atrasados - 1º Aviso\n"
                    + "5 - Enviar Atrasados - 2º Aviso\n"
                    + "6 - Enviar Atrasados - 3º Aviso\n"
                    + "7 - Gerar BI\n"
                    + "9 - Testar Envio\n"
                    + "\n\n\n");

            switch (retorno) {

                case "2":
                    //    System.err.println(conectar(2));
                    break;

                case "3":
                    //JOptionPane.showMessageDialog(null, "DESATIVADA PARA NÃO DAR MERDA");
                    new WhatsApp().enviarFatura();
                    break;

                case "4":

                    new WhatsApp().enviarAtrasados(1);
                    break;

                case "5":

                    new WhatsApp().enviarAtrasados(2);
                    break;

                case "6":

                    new WhatsApp().enviarAtrasados(3);
                    break;

                case "7":

                    new WhatsApp().produzBI();
                    break;

                case "9":
                    WhatsApp zap = new WhatsApp();
                    /*  zap.setNumber("5528999111455");
                    zap.setText(String.valueOf(UUID.randomUUID()));
                    enviar(zap);
                    // JOptionPane.showMessageDialog(null, UUID.randomUUID()); */

                    zap.enviarTesteEBM();
                    break;

            }
        }

        //  System.err.println(UUID.randomUUID());
    }

    public static String conectar(int tipo) throws MalformedURLException, UnsupportedEncodingException, IOException {

        Mensagem m = new Mensagem();

        String parametros = new Gson().toJson(m);

        URL url = new URL(linkApi + "start"); // URL to your application
        Map<String, String> params = new LinkedHashMap<>();

        params.put("webhook", m.getWebHook());
        params.put("session", m.getSession(tipo));

        StringBuilder postData = new StringBuilder();
        // POST as urlencoded is basically key-value pairs, as with GET
        // This creates key=value&key=value&... pairs
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(param.getKey());
            postData.append('=');
            postData.append(String.valueOf(param.getValue()));

            //  System.err.println(param.getKey() + " - " + String.valueOf(param.getValue()));
        }

        // Convert string to byte array, as it should be sent
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        // Connect, easy
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // Tell server that this is POST and in which format is the data
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        // This gets the output from your server
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String retorno = "";
        for (int c; (c = in.read()) >= 0;) {
            retorno = retorno + (char) c;

        }

        return retorno;
    }

    public static String enviar(WhatsApp zap) throws MalformedURLException, UnsupportedEncodingException, IOException, InterruptedException {

        System.err.println("Entrou para enviar tipo " + zap.getTipo() + " para " + zap.getNumber());
        String retorno = "";
        HttpsURLConnection con = null;
        try {

            String urlParameters = g.toJson(zap);

            System.err.println(urlParameters);

            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

            StringBuilder content;

            try {

                URL myurl = new URL(linkEnvio);

                //      System.out.println("CON -> " + con);
                //   System.out.println(linkEnvio);
                con = (HttpsURLConnection) myurl.openConnection();

                con.setDoOutput(true);
                con.setRequestMethod("POST");

                //  con.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestProperty("Content-Type", "application/json");

                try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                    wr.write(postData);

                }

                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {

                    String line;
                    content = new StringBuilder();

                    while ((line = in.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }

                    retorno = content.toString();

                }

                System.out.println("RETORNO FOI: -> " + content.toString());
            } finally {
                con.disconnect();

            }

        } catch (Exception ee) {

            ee.printStackTrace();
        }

        Thread.sleep(1000);
        return retorno;

    }

    public void enviarFatura() throws Exception {

        Fatura f;
        FaturaDAO fDao = new FaturaDAO();

        //Buscar ArrayList de Faturas
        ArrayList<String> faturas = new ArrayList<String>();

        faturas = fDao.buscaFaturas();

        //Percorrer o Array
        int c = 0;
        for (c = 0; c < faturas.size(); c++) {

            Thread.sleep(1000);
            //Percorrer o Array
            f = new Fatura(faturas.get(c));
            f = new FaturaDAO().buscarFatura(f);

            f.setC(new FaturaDAO().buscaNumerosWhatsApp(f.getC()));

            String corpo = "*-> MENSAGEM AUTOMÁTICA <-*\n"
                    + "\n"
                    + "Olá, " + f.getC().getNomeFantasia() + ".\n"
                    + "Tudo bem?\n"
                    + "Esta é uma mensagem automática da *EBM Tecnologias*.\n"
                    + "\n"
                    + "A sua fatura do mês de " + f.getMesFaturado() + " já está disponível para pagamento.\n"
                    + "\n"
                    + "*Valor*: R$ " + f.getValor() + "\n"
                    + "*Vencimento*: " + f.getDataVencimento() + "\n"
                    + "*Código de barras*: " + f.getCodigoBarras() + " \n"
                    + "\n"
                    + "Qualquer dúvida, favor manter contato no número: 0800 330 0800 ou através do e-mail *financeiro@ebmtecnologias.com.br*.\n"
                    + "\n"
                    + "Tenha um excelente dia!"
                    + "\n\n"
                    + "*NÃO RESPONDA A ESTA MENSAGEM*";

            WhatsApp zap = new WhatsApp();

            int i = 0;
            for (i = 0; i < f.getC().getWhatsapp().size(); i++) {

                if (f.getC().getWhatsapp().get(i).isFinanceiro()) {

                    if (!fDao.verificaEnvio(f, f.getC().getWhatsapp().get(i).getNumero(), 1)) {

                        System.err.println("Vai enviar para " + f.getC().getWhatsapp().get(i).getNumero() + " - " + f.getC().getWhatsapp().get(i).getNome());

                        zap.setNumber(f.getC().getWhatsapp().get(i).getNumero());
                        // zap.setNumber("5528999111455");
                        //  zap.setNumber("558007775162-1619995449");

                        zap.setTipo(1);

                        //   zap.setNumber(f.getC().getWhatsapp().get(i).getNumero());
                        zap.setText(corpo);
                        enviar(zap);

                        zap.setTipo(2);  //file

                        zap.setPath(f.getBoleto());
                        zap.setCaption("Boleto - EBM Tecnologias - " + f.getMesFaturado() + ".pdf");
                        enviar(zap);

                        zap.setPath(f.getNotaFiscal());
                        zap.setCaption("Nota Fiscal - EBM Tecnologias - " + f.getNumeroNota() + ".pdf");
                        enviar(zap);

                        fDao.salvaEnvio(f.getFatura(), f.getC().getWhatsapp().get(i).getNumero(), 1, f.getC().getIdInterno());  // 1 - Fatura nova

                    } else {
                        System.err.println("Já foi enviado para " + f.getC().getWhatsapp().get(i).getNumero() + " - " + f.getC().getWhatsapp().get(i).getNome());

                    }
                }
            }
        }
    }

    public void enviarAtrasados(int tipo) throws Exception {

        Fatura f;
        FaturaDAO fDao = new FaturaDAO();

        //Buscar ArrayList de Faturas
        ArrayList<String> faturas = new ArrayList<String>();

        faturas = fDao.buscaFaturasAtrasadas();

        //Percorrer o Array
        int c = 0;
        for (c = 0; c < faturas.size(); c++) {

            //Percorrer o Array
            f = new Fatura(faturas.get(c));
            f = new FaturaDAO().buscarFatura(f);

            f.setC(new FaturaDAO().buscaNumerosWhatsApp(f.getC()));

            String corpo = "*-> MENSAGEM AUTOMÁTICA <-*\n"
                    + "\n"
                    + "Olá, " + f.getC().getNomeFantasia() + ".\n"
                    + "Tudo bem?\n"
                    + "Esta é uma mensagem automática da *EBM Tecnologias*.\n"
                    + "\n"
                    + "A sua fatura do mês de " + f.getMesFaturado() + " está em aberto em nosso sistema.\n"
                    + "\n"
                    + "*Valor*: R$ " + f.getValor() + "\n"
                    + "*Vencimento*: " + f.getDataVencimento() + "\n"
                    + "*Código de barras*: " + f.getCodigoBarras() + " \n"
                    + "\n"
                    + "Qualquer dúvida, favor manter contato no número: 0800 330 0800 ou através do e-mail *financeiro@ebmtecnologias.com.br*.\n"
                    + "\n"
                    + "Tenha um excelente dia!"
                    + "\n\n"
                    + "*NÃO RESPONDA A ESTA MENSAGEM*";

            WhatsApp zap = new WhatsApp();

            int i = 0;
            for (i = 0; i < f.getC().getWhatsapp().size(); i++) {

                if (f.getC().getWhatsapp().get(i).isFinanceiro()) {

                    if (!fDao.verificaEnvio(f, f.getC().getWhatsapp().get(i).getNumero(), tipo)) {

                        System.err.println("Vai enviar para " + f.getC().getWhatsapp().get(i).getNumero() + " - " + f.getC().getWhatsapp().get(i).getNome() + " - " + f.getC().getNomeFantasia());

                        zap.setNumber(f.getC().getWhatsapp().get(i).getNumero());
                        // zap.setNumber("5528999111455");
                        //  zap.setNumber("558007775162-1619995449");

                        zap.setTipo(1);

                        zap.setText(corpo);
                        enviar(zap);

                        zap.setText("");

                        zap.setTipo(2);  //file

                        zap.setPath(f.getBoleto());
                        zap.setCaption("Boleto - " + f.getMesFaturado() + ".pdf");
                        enviar(zap);

                        zap.setPath(f.getNotaFiscal());
                        zap.setCaption("Nota Fiscal - " + f.getNumeroNota() + ".pdf");
                        enviar(zap);

                        fDao.salvaEnvio(f.getFatura(), f.getC().getWhatsapp().get(i).getNumero(), 2, f.getC().getIdInterno());  // 1 - Fatura nova

                    } else {
                        System.err.println("Já foi enviado para " + f.getC().getWhatsapp().get(i).getNumero() + " - " + f.getC().getWhatsapp().get(i).getNome());

                    }
                }
            }
        }
    }

    public void enviarTesteEBM() throws Exception {

        Fatura f;
        FaturaDAO fDao = new FaturaDAO();

        //Buscar ArrayList de Faturas
        ArrayList<String> faturas = new ArrayList<String>();

        faturas = fDao.buscaFaturasTeste();

        //Percorrer o Array
        int c = 0;
        for (c = 0; c < faturas.size(); c++) {

            //Percorrer o Array
            f = new Fatura(faturas.get(c));
            f = new FaturaDAO().buscarFatura(f);

            f.setC(new FaturaDAO().buscaNumerosWhatsApp(f.getC()));

            String corpo = "*-> MENSAGEM AUTOMÁTICA <-*\n"
                    + "\n"
                    + "Olá, " + f.getC().getNomeFantasia() + ".\n"
                    + "Tudo bem?\n"
                    + "\n"
                    + "Viemos lembrar que a sua fatura do mês de " + f.getMesFaturado() + " está pendente há " + f.getDiasVencidos() + " dias.\n"
                    + "\nEvite sanções em seu nome efetuando o pagamento do título a seguir."
                    + "\n"
                    + "*Valor*: R$ " + f.getValor() + "\n"
                    + "*Vencimento*: " + f.getDataVencimento() + "\n"
                    + "*Código de barras*: " + f.getCodigoBarras() + " \n"
                    + "\n"
                    + "Lembre-se que o não pagamento acarreta em bloqueio parcial após 10 dias, bloqueio total em 40 dias e cancelamento e negativação em 70 dias."
                    + "\n\nQualquer dúvida, favor manter contato no número: 0800 330 0800 ou através do e-mail *financeiro@ebmtecnologias.com.br*.\n"
                    + "\n"
                    + "Tenha um excelente dia!"
                    + "\n\n"
                    + "*NÃO RESPONDA A ESTA MENSAGEM*";

            WhatsApp zap = new WhatsApp();

            int i = 0;
            for (i = 0; i < f.getC().getWhatsapp().size(); i++) {

                System.err.println("Vai enviar para " + f.getC().getWhatsapp().get(i).getNumero() + " - " + f.getC().getWhatsapp().get(i).getNome() + " - " + f.getC().getNomeFantasia());

                zap.setNumber("5528999111455");

                zap.setTipo(1);

                zap.setText(corpo);
                enviar(zap);

                zap.setText("");

                zap.setTipo(2);  //file

                zap.setPath(f.getBoleto());
                zap.setCaption("Boleto - " + f.getMesFaturado() + ".pdf");
                enviar(zap);

                zap.setPath(f.getNotaFiscal());
                zap.setCaption("Nota Fiscal - " + f.getNumeroNota() + ".pdf");
                enviar(zap);

            }
        }
    }

    public void produzBI() throws Exception {

        FaturaDAO fDao = new FaturaDAO();
        BiDAO bDao = new BiDAO();
        WhatsApp zap = new WhatsApp();
        BiWhats bi;

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        //Buscar as ids dos clientes que possuem BI
        clientes = bDao.buscarClientesBI();

        int cc = 0;

        for (cc = 0; cc < clientes.size(); cc++) {

            //Entrar no Array e buscar os dados do cliente
            bi = new BiWhats(); // Aqui já gera um novo ID

            //Tem que buscar a ID do cliente ali em cima
            bi.setCliente(clientes.get(cc));

            //Verifica se já existe algum bi
            if (!bDao.verificaEnvioBI(bi)) {

                //busca os dados do WhatsApp
                bi.setCliente(fDao.buscaNumerosWhatsApp(bi.getCliente()));

                //busca objeto por objeto
                //busca DIDs
                bi = bDao.buscaDIDs(bi);

                //Busca Recebidas por numero / cliente
                int bb = 0;

                for (bb = 0; bb < bi.getDIDs().size(); bb++) {
                    bi.getRecebidas().add(bDao.buscarDadosLigacoesRecebidas(bi.getDIDs().get(bb).getNumero(), bi));

                }

                //busca Efetuadas geral
                bi.getEfetuadas().add(bDao.buscarDadosLigacoesEfetuadas(bi));

                //  JOptionPane.showMessageDialog(null, bi.getEfetuadas().size());
                int entrada = 0;
                int saida = 0;
                int segundosF = 0;
                int mediaSegundosF = 0;
                int segundosM = 0;
                int mediaSegundosM = 0;

                bb = 0;
                for (bb = 0; bb < bi.getRecebidas().size(); bb++) {

                    entrada = entrada + bi.getRecebidas().get(bb).getLigacoes();
                    segundosF = segundosF + bi.getRecebidas().get(bb).getDuracaoSegundosF();
                    mediaSegundosF = mediaSegundosF + bi.getRecebidas().get(bb).getDuracaoMSegundosF();
                    segundosM = segundosM + bi.getRecebidas().get(bb).getDuracaoSegundosM();
                    mediaSegundosM = mediaSegundosM + bi.getRecebidas().get(bb).getDuracaoMSegundosM();

                }

                //Calcular porcentam de uso da linha
                bb = 0;
                for (bb = 0; bb < bi.getRecebidas().size(); bb++) {
                    bi.getRecebidas().get(bb).setPorcentagemUso(Funcoes.calculaPorcentagem(bi.getRecebidas().get(bb).getLigacoes(), entrada));

                }

                for (bb = 0; bb < bi.getEfetuadas().size(); bb++) {

                    saida = saida + bi.getEfetuadas().get(bb).getLigacoes();

                }

                //Aqui vai enviar
                String corpo = "Bom dia, " + clientes.get(cc).getNomeFantasia() + "."
                        + "\nTudo bem?"
                        + "\n\nEste é o relatório do dia *" + bi.getData().getDataInteira() + "*."
                        + "\n"
                        + "\n------------------ *GERAL* ------------------"
                        + "\n"
                        + "\nAconteceram *" + (entrada + saida) + "* ligações, sendo:"
                        + "\n*Entrada*: " + entrada + " ligações - " + Funcoes.calculaPorcentagem(entrada, entrada + saida) + "%."
                        + "\n*Saída* :  " + saida + " ligações - " + Funcoes.calculaPorcentagem(saida, entrada + saida) + "%."
                        + "\n"
                        + "\n---------------- *ENTRADA* ----------------"
                        + "\n"
                        + "\n*Ligações total*: " + entrada
                        + "\n*Duração total* : " + Funcoes.sec_to_time(segundosF + segundosM) + ""
                        //       + "\n*Tempo médio*: " + Funcoes.sec_to_time(((segundosF + segundosM) / entrada)) + ""
                        + "\n";

                //CRIAR UM FOR COM OS DIDS E INFORMAR SOBRE CADA 1;
                int i = 0;
                for (i = 0; i < bi.getRecebidas().size(); i++) {
                    corpo = corpo + "\n*Linha*: " + bi.getRecebidas().get(i).getNumero() + " - *" + (bi.getRecebidas().get(i).getlFixo() + bi.getRecebidas().get(i).getlMovel()) + "* ligações - *" + bi.getRecebidas().get(i).getPorcentagemUso() + "%*"
                            + "\n - *Fixo*  : " + bi.getRecebidas().get(i).getlFixo() + " ligações de " + bi.getRecebidas().get(i).getfNumerosDiferentes() + " nºs diferentes"// - "+(bi.getRecebidas().get(i).getlFixo()*100)/bi.getRecebidas().get(i).getLigacoes()+"%"
                            + "\n - *Móvel* : " + bi.getRecebidas().get(i).getlMovel() + " ligações de " + bi.getRecebidas().get(i).getmNumerosDiferentes() + " nºs diferentes" //- "+(bi.getRecebidas().get(i).getlMovel()*100)/bi.getRecebidas().get(i).getLigacoes()+"%"
                            + "\n";

                }

                corpo = corpo + ""
                        + "\n------------------ *SAÍDA* ------------------"
                        + "\n"
                        + "\n*Ligações total*: " + saida
                        + "\n*Duração total* : " + Funcoes.sec_to_time(bi.getEfetuadas().get(0).getDuracaoSegundosF() + bi.getEfetuadas().get(0).getDuracaoSegundosM()) + ""
                        //       + "\n*Tempo médio*: " + Funcoes.sec_to_time(((segundosF + segundosM) / entrada)) + ""
                        + "\n"
                        + "\n*Fixo*: " + bi.getEfetuadas().get(0).getlFixo() + "  ligações."
                        + "\n - *Duração total*  : " + Funcoes.sec_to_time(bi.getEfetuadas().get(0).getDuracaoSegundosF()) + ""
                        + "\n - *Nºs diferentes* : " + bi.getEfetuadas().get(0).getfNumerosDiferentes() + ""
                        + "\n"
                        + "\n*Móvel*: " + bi.getEfetuadas().get(0).getlMovel() + " ligações"
                        + "\n - *Duração total*  : " + Funcoes.sec_to_time(bi.getEfetuadas().get(0).getDuracaoSegundosM()) + ""
                        + "\n - *Nºs diferentes* : " + bi.getEfetuadas().get(0).getmNumerosDiferentes() + "";

                //Primeiro gera, depois envia.*/
                //Preciso organizar para verificar se já foi enviado algo antes, senão pode rodar duas vezes e duplicar
                corpo = corpo + ""
                        + "\n\n\n##################################"
                        + "\n\n*Esta mensagem é automática!*"
                        + "\n\n*Caso não deseje mais receber,*"
                        + "\n*comunique-nos através do 0800 330 0800 ou através do e-mail *financeiro@ebmtecnologias.com.br*.*";
                zap.setText(corpo);
                //Aqui salva no banco do BI
                fDao.salvaBi(bi);

                //   JOptionPane.showMessageDialog(null, "AQUI");
                //Aqui vai ter um for para os numeros a serem enviados... Quero uma cópia de cada mensagem enviada
                bb = 0;
                for (bb = 0; bb < bi.getCliente().getWhatsapp().size(); bb++) {

                    if (bi.getCliente().getWhatsapp().get(bb).isBi()) {
//                        JOptionPane.showMessageDialog(null, bi.getCliente().getWhatsapp().get(bb).getNumero() + "\n\n\n\n" + corpo);
//                        System.err.println(bi.getCliente().getWhatsapp().get(bb).getNumero() + "\n\n\n\n" + corpo);

                        zap.setNumber(bi.getCliente().getWhatsapp().get(bb).getNumero());
                        // zap.setNumber("558007775162-1619995449");
                        enviar(zap);

                        zap.setNumber("558007775162-1619995449");
                        enviar(zap);

                        //Aqui vai informar o Envio
                        fDao.salvaEnvio(bi.getCodigoBi(), bi.getCliente().getWhatsapp().get(bb).getNumero(), 6, bi.getCliente().getIdInterno());  //6 = BI
                    }
                }

            } else {
                System.err.println("BI já enviado para " + bi.getCliente().getNomeFantasia());
            }

        }

//JOptionPane.showMessageDialog(null, bi.getCodigoBi());
    }

}
