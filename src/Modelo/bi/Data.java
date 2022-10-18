/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.bi;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Emanuel
 */
public class Data {

    private String dataInteira;
    private ArrayList<Integer> hora;

    public Data() {
        super();

        this.dataInteira = LocalDate.now().minusDays(1).toString();
        String datas[] = dataInteira.split("-");
        this.dataInteira = datas[2] + "/" + datas[1] + "/" + datas[0];

        this.hora = new ArrayList<Integer>(23);

    }

    /**
     * @return the dataInteira
     */
    public String getDataInteira() {
        return dataInteira;
    }

    /**
     * @param dataInteira the dataInteira to set
     */
    public void setDataInteira(String dataInteira) {
        this.dataInteira = dataInteira;
    }

    /**
     * @return the hora
     */
    public ArrayList<Integer> getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(ArrayList<Integer> hora) {
        this.hora = hora;
    }

}
