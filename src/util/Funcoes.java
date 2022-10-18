/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonToken;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Emanuel
 */
public class Funcoes {


public static String servidorAPI(){

//oficial  "http://json.ebmtecnologias.com.br:3333/"


return "http://10.1.5.14/";

}


    public Map jsonToLinkedHashMap(String param) {

        Map<String, String> result = new LinkedHashMap<>();
        if (param != null) {
            StringBuffer sb = new StringBuffer(param);
            try {
                String str = sb.substring(1, sb.length() - 1);
                String[] srArray = str.split(",");
                String tempStr = null;
                for (int i = 0; srArray != null && i < srArray.length; i++) {
                    tempStr = srArray[i];
                    if (tempStr != null && tempStr.contains(":")) {
                        String[] keyVal = tempStr.split(":");
                        if (keyVal.length > 1) {
                            result.put(keyVal[0].replace("\"", ""), keyVal[1].replace("\"", ""));
                        } else {
                            result.put(keyVal[0], "");
                        }
                    }

                }

            } catch (Exception e) {
                System.out.println("In JSONConverter: Issue while parsing the JSON => " + e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    public static long descanso() {

        long descanso = 0;

        while (descanso < 3500) {

            descanso = (long) (Math.random() * 10000);

        };

        return descanso;

    }

    public static String blobToBase64(Blob base) throws Exception {
        String retorno = null;
        try {
            retorno = new String(Base64.getEncoder().encodeToString(base.getBytes(1, (int) base.length())));
        } catch (Exception e) {
            System.err.println("Ocorreu uma exceção em converter Blob para Base 64: " + e.getMessage());
        }

        return retorno;

    }

    public String Maiuscula(String texto) {
        String posicao = "" + texto.charAt(0);
        String pos = posicao.toUpperCase();
        posicao = "";

        for (int i = 1; i < texto.length(); i++) {
            posicao = posicao + texto.charAt(i);
        }

        return pos + posicao;
    }

    public static void escreverArquivo(String nome, String corpo) throws IOException {

        FileWriter arq = new FileWriter("c:\\" + nome + ".txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(corpo);
        arq.close();

    }

    public static String MD5(String corpo) throws NoSuchAlgorithmException {

        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(corpo.getBytes(), 0, corpo.length());

        return String.valueOf(new BigInteger(1, m.digest()).toString(16));

    }

    public static String sec_to_time(int scound) {
        if (scound <= 0) {
            return "";
        }
        int h = scound / 3600;
        int m = scound % 3600 / 60;
        int s = scound % 60; // Less than 60 is the second, enough 60 is the minute

        String hh = "", mm = "", ss = "";
        if (h < 10) {
            hh = "0" + h;
        } else {
            hh = String.valueOf(h);
        }
        if (m < 10) {
            mm = "0" + m;
        } else {
            mm = String.valueOf(m);
        }
        if (s < 10) {
            ss = "0" + s;
        } else {
            ss = String.valueOf(s);
        }

        return hh + ":" + mm + ":" + ss;
    }

    public static double calculaPorcentagem(double valor, double geral) {

        double retorno = 0;
        try {
            retorno = (valor * 100) / geral;
        } catch (Exception e) {
            retorno = 0;
            System.err.println(e.getMessage());
        }

        long fact = (long) Math.pow(10, 2);
        retorno = retorno * fact;
        long tmp = Math.round(retorno);
        return (double) tmp / fact;

    }
}
