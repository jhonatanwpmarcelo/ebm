/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import Modelo.Fatura;
import Modelo.WhatsApp;
import Modelo.bi.BiWhats;
import Modelo.bi.DID;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.Funcoes;

/**
 *
 * @author Emanuel
 */
public class BiDAO {

    /**
     *
     */
    public ArrayList<Cliente> buscarClientesBI() throws Exception {
        Cliente c = new Cliente();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        String query = "";

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {

                query = "select id, id_magnus, nome from ebm_interno.cliente where bi = 1;";

                //    System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                        c = new Cliente();

                        c.setIdInterno(rs.getInt("id"));
                        c.setIdMagnus(rs.getInt("id_magnus"));
                        c.setNomeFantasia(rs.getString("nome"));

                        clientes.add(c);

                        //   System.err.println(rs.getString("nome"));
                    }

                    st.close();
                    rs.close();

                }
            } catch (Exception e) {
                System.err.println("Ocorreu uma exceção! ");
                e.printStackTrace();

            }

        }

        return clientes;

    }

    /**
     *
     */
    public DID buscarDadosLigacoesRecebidas(String numero, BiWhats bi) throws Exception {

        String query = "";
        DID d = new DID();

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {
                //FIXO
                query = "Select fixo.total as fTotal, fixo.segundos as fsegundos, fixo.segundosMedio as fsmedio, fixo.tempo as fTempo, fixo.tempoMedio as fTempoM, fixo.nDiferentes as fNDiferentes,\n"
                        + "     movel.total as mTotal, movel.segundos as msegundos, movel.segundosMedio as msmedio, movel.tempo as mTempo, movel.tempoMedio as mTempoM, movel.nDiferentes as mNDiferentes  from \n"
                        + "\n"
                        + "(SELECT \n"
                        + " coalesce(COUNT(*),0) total,\n"
                        + " coalesce(SUM(real_sessiontime),0) segundos,\n"
                        + " SEC_TO_TIME(coalesce(SUM(real_sessiontime),0)) tempo,\n"
                        + "SUBSTRING( SEC_TO_TIME(coalesce(SUM(real_sessiontime) / COUNT(*),0)),1,8) tempoMedio,\n"
                        + " coalesce(SUM(real_sessiontime) / COUNT(*),0) segundosMedio,\n"
                        + "coalesce(count(distinct(callerid)),0) nDiferentes\n"
                        + "FROM\n"
                        + " mbilling.pkg_cdr\n"
                        + "WHERE"
                        + " sipiax = 3\n"
                        + " AND ( callerid LIKE '__1%' OR callerid LIKE '__2%' OR callerid LIKE '__3%' OR callerid LIKE '__4%' OR callerid LIKE '__5%')\n"
                        + " AND calledstation = '" + numero + "' AND id_user = " + bi.getCliente().getIdMagnus() + "\n"
                        + " AND DATE_FORMAT(starttime, '%d/%m/%Y') = '" + bi.getData().getDataInteira() + "'\n"
                        + ") as fixo, \n"
                        + "\n"
                        + "(SELECT \n"
                        + " coalesce(COUNT(*),0) total,\n"
                        + " coalesce(SUM(real_sessiontime),0) segundos,\n"
                        + " SEC_TO_TIME(coalesce(SUM(real_sessiontime),0)) tempo,\n"
                        + "SUBSTRING( SEC_TO_TIME(coalesce(SUM(real_sessiontime) / COUNT(*),0)),1,8) tempoMedio,\n"
                        + " coalesce(SUM(real_sessiontime) / COUNT(*),0) segundosMedio,\n"
                        + "coalesce(count(distinct(callerid)),0) nDiferentes\n"
                        + "FROM\n"
                        + " mbilling.pkg_cdr\n"
                        + "WHERE\n"
                        + " sipiax = 3\n"
                        + " AND ( callerid LIKE '__6%' OR callerid LIKE '__7%' OR callerid LIKE '__8%' OR callerid LIKE '__9%')\n"
                        + " AND calledstation = '" + numero + "' AND id_user = " + bi.getCliente().getIdMagnus() + "\n"
                        + " AND DATE_FORMAT(starttime, '%d/%m/%Y') = '" + bi.getData().getDataInteira() + "'\n"
                        + ") as movel\n"
                        + "\n"
                        + "";

                //  System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
                        d = new DID();

                        d.setNumero(numero);
                        d.setlFixo(rs.getInt("fTotal"));
                        d.setDuracaoTotalF(rs.getString("ftempo"));
                        d.setDuracaoMediaF(rs.getString("fTempoM"));
                        d.setfNumerosDiferentes(rs.getInt("fNDiferentes"));
                        d.setDuracaoSegundosF(rs.getInt("fsegundos"));
                        d.setDuracaoMSegundosF(rs.getInt("fsmedio"));

                        d.setlMovel(rs.getInt("mTotal"));
                        d.setDuracaoTotalM(rs.getString("mtempo"));
                        d.setDuracaoMediaM(rs.getString("mTempoM"));
                        d.setmNumerosDiferentes(rs.getInt("mNDiferentes"));
                        d.setDuracaoSegundosM(rs.getInt("msegundos"));
                        d.setDuracaoMSegundosM(rs.getInt("msmedio"));

                        //   System.err.println(rs.getString("linha_digitavel"));
                    }

                    st.close();
                    rs.close();

                }
            } catch (Exception e) {
                System.err.println("Ocorreu uma exceção! ");
                e.printStackTrace();

            }

        }

        return d;

    }

    /**
     *
     */
    public DID buscarDadosLigacoesEfetuadas(BiWhats bi) throws Exception {

        String query = "";
        DID d = new DID();

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {
                //FIXO
                query = "Select fixo.total as fTotal, fixo.segundos as fsegundos, fixo.segundosMedio as fsmedio, fixo.tempo as fTempo, fixo.tempoMedio as fTempoM, fixo.nDiferentes as fNDiferentes,\n"
                        + "     movel.total as mTotal, movel.segundos as msegundos, movel.segundosMedio as msmedio, movel.tempo as mTempo, movel.tempoMedio as mTempoM, movel.nDiferentes as mNDiferentes,\n"
                        + "     coalesce(fixo.callerid,0) as fCallerID, coalesce(movel.callerid,0) as mCallerID from \n"
                        + "\n"
                        + "(SELECT \n"
                        + "callerid,\n"
                        + " coalesce(COUNT(*),0) total,\n"
                        + " coalesce(SUM(real_sessiontime),0) segundos,\n"
                        + " SEC_TO_TIME(coalesce(SUM(real_sessiontime),0)) tempo,\n"
                        + "SUBSTRING( SEC_TO_TIME(coalesce(SUM(real_sessiontime) / COUNT(*),0)),1,8) tempoMedio,\n"
                        + " coalesce(SUM(real_sessiontime) / COUNT(*),0) segundosMedio,\n"
                        + "count(distinct(calledstation)) nDiferentes\n"
                        + "FROM\n"
                        + " mbilling.pkg_cdr\n"
                        + "WHERE\n"
                        + "sipiax = 0\n"
                        + " AND ( calledstation LIKE '55__1%' OR calledstation LIKE '55__2%' OR calledstation LIKE '55__3%' OR calledstation LIKE '55__4%' OR calledstation LIKE '55__5%')\n"
                        + " AND id_user = " + bi.getCliente().getIdMagnus() + "\n"
                        + " AND DATE_FORMAT(starttime, '%d/%m/%Y') = '" + bi.getData().getDataInteira() + "'\n"
                        + ") as fixo, \n"
                        + "\n"
                        + "(SELECT \n"
                        + "callerid,\n"
                        + " coalesce(COUNT(*),0) total,\n"
                        + " coalesce(SUM(real_sessiontime),0) segundos,\n"
                        + " SEC_TO_TIME(coalesce(SUM(real_sessiontime),0)) tempo,\n"
                        + "SUBSTRING( SEC_TO_TIME(coalesce(SUM(real_sessiontime) / COUNT(*),0)),1,8) tempoMedio,\n"
                        + " coalesce(SUM(real_sessiontime) / COUNT(*),0) segundosMedio,\n"
                        + "count(distinct(calledstation)) nDiferentes\n"
                        + "FROM\n"
                        + " mbilling.pkg_cdr\n"
                        + "WHERE\n"
                        + "sipiax = 0\n"
                        + " AND ( calledstation LIKE '55__6%' OR calledstation LIKE '55__7%' OR calledstation LIKE '55__8%' OR calledstation LIKE '55__9%')\n"
                        + " AND id_user = " + bi.getCliente().getIdMagnus() + "\n"
                        + " AND DATE_FORMAT(starttime, '%d/%m/%Y') = '" + bi.getData().getDataInteira() + "'\n"
                        + ") as movel\n"
                        + "\n"
                        + "";

                //   System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
                        d = new DID();

                        if (rs.getString("fCallerID").equals("0")) {
                            d.setNumero(rs.getString("fCallerID"));
                        }
                        if (rs.getString("mCallerID").equals("0")) {
                            d.setNumero(rs.getString("mCallerID"));
                        }

                        d.setlFixo(rs.getInt("fTotal"));
                        d.setDuracaoTotalF(rs.getString("ftempo"));
                        d.setDuracaoMediaF(rs.getString("fTempoM"));
                        d.setfNumerosDiferentes(rs.getInt("fNDiferentes"));
                        d.setDuracaoSegundosF(rs.getInt("fsegundos"));
                        d.setDuracaoMSegundosF(rs.getInt("fsmedio"));

                        d.setlMovel(rs.getInt("mTotal"));
                        d.setDuracaoTotalM(rs.getString("mtempo"));
                        d.setDuracaoMediaM(rs.getString("mTempoM"));
                        d.setmNumerosDiferentes(rs.getInt("mNDiferentes"));
                        d.setDuracaoSegundosM(rs.getInt("msegundos"));
                        d.setDuracaoMSegundosM(rs.getInt("msmedio"));

                        //   System.err.println(rs.getString("linha_digitavel"));
                    }

                    st.close();
                    rs.close();

                }
            } catch (Exception e) {
                System.err.println("Ocorreu uma exceção! ");
                e.printStackTrace();

            }

        }

        return d;

    }

    public BiWhats buscaDIDs(BiWhats bi) throws Exception {

        String query = "";
        DID d = new DID();

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {

                query = "SELECT did FROM mbilling.pkg_did\n"
                        + " WHERE\n"
                        + " id IN (SELECT id_did FROM mbilling.pkg_did_use\n"
                        + " WHERE status = 1 AND id_user = " + bi.getCliente().getIdMagnus() + ") order by 1 asc;";

                //     System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                        d = new DID();

                        d.setNumero(rs.getString("did"));

                        bi.getDIDs().add(d);
                        //   System.err.println(rs.getString("linha_digitavel"));
                    }

                    st.close();
                    rs.close();

                }
            } catch (Exception e) {
                System.err.println("Ocorreu uma exceção! ");
                e.printStackTrace();

            }

        }

        return bi;

    }

    public boolean verificaEnvioBINumero(BiWhats bi, String numero) throws Exception {

        String query;

        boolean retorno = false;

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {

                query = "SELECT \n"
                        + "    COUNT(*) existe\n"
                        + "FROM\n"
                        + "    ebm_interno.whatsApp_envio\n"
                        + "WHERE\n"
                        + " numero = '"+numero+"'\n"
                        + " and tipo = 6\n"
                        + " AND codigo_controle = (SELECT codigo FROM\n"
                        + " ebm_interno.whatsApp_bi\n"
                        + " WHERE\n"
                        + " id_cliente = "+bi.getCliente().getIdInterno()+" AND DATE_FORMAT(data, '%d/%m/%Y') = '"+bi.getData().getDataInteira()+"')";

                   System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                        if (rs.getInt("existe") > 0) {
                            retorno = true;

                        };
                    }

                    st.close();
                    rs.close();

                }
            } catch (Exception e) {
                System.err.println("Ocorreu uma exceção! " + e.getMessage());
                //e.printStackTrace();

            }

        }
        return retorno;
    }
    
     public boolean verificaEnvioBI(BiWhats bi) throws Exception {

        String query;

        boolean retorno = false;

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {

                query = "SELECT \n"
                        + "    COUNT(*) existe\n"
                        + "FROM\n"
                        + "    ebm_interno.whatsApp_envio\n"
                        + "WHERE\n"
                        + "  tipo = 6\n"
                        + " AND codigo_controle = (SELECT codigo FROM\n"
                        + " ebm_interno.whatsApp_bi\n"
                        + " WHERE\n"
                        + " id_cliente = "+bi.getCliente().getIdInterno()+" AND DATE_FORMAT(data, '%d/%m/%Y') = '"+bi.getData().getDataInteira()+"')";

                 //  System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                        if (rs.getInt("existe") > 0) {
                            retorno = true;

                        };
                    }

                    st.close();
                    rs.close();

                }
            } catch (Exception e) {
                System.err.println("Ocorreu uma exceção! " + e.getMessage());
                //e.printStackTrace();

            }

        }
        return retorno;
    }

    public ArrayList<String> buscaFaturas() throws Exception {

        String query;

        ArrayList<String> faturas = new ArrayList<String>();

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {

                query = "SELECT \n"
                        + "    codigo_fatura\n"
                        + "FROM\n"
                        + "    ebm_interno.fatura\n"
                        + "WHERE\n"
                        + "    situacao = 1 AND valor > 0\n"
                        + "        AND id_nota_fiscal IS NOT NULL\n"
                        + "        AND DATE_FORMAT(periodo_faturado, '%Y%m') > '202104';";

                // System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                        faturas.add(rs.getString("codigo_fatura"));
                    }

                    st.close();
                    rs.close();

                }
            } catch (Exception e) {
                System.err.println("Ocorreu uma exceção! " + e.getMessage());
                //e.printStackTrace();

            }

        }
        return faturas;
    }

    public void salvaEnvio(String codigoControle, String numero, int tipo, int idCliente) throws Exception {

        String query;

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            query = "INSERT INTO `ebm_interno`.`whatsApp_envio` (`id_cliente`, `numero`, `tipo`, `codigo_controle`) VALUES (?,?,?,?);";

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, String.valueOf(idCliente));
            st.setString(2, numero);
            st.setString(3, String.valueOf(tipo));
            st.setString(4, codigoControle);
            st.executeUpdate();

        } catch (Exception e) {

            System.err.println("Erro ao Salvar envio do Whats: ->" + e.getMessage());
            //   Funcoes.relataErro("CdrDAO", "Atualizando IDAndroid", "Atualizando os valores de CDR dentro do idAndroid", e.getMessage());

        }

    }

    public void salvaBi(BiWhats bi) throws Exception {

        String query;

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            query = "INSERT INTO `ebm_interno`.`whatsApp_bi` (`codigo`,`id_cliente`, `data`, `json`)  VALUES (?,?,now() - interval 1 day,?);";

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, bi.getCodigoBi());
            st.setString(2, String.valueOf(bi.getCliente().getIdInterno()));
            //    st.setString(3, "now() - interval 1 day");
            st.setString(3, new Gson().toJson(bi));
            st.executeUpdate();

        } catch (Exception e) {

            System.err.println("Erro ao Salvar envio do Whats: ->" + e.getMessage());
            //   Funcoes.relataErro("CdrDAO", "Atualizando IDAndroid", "Atualizando os valores de CDR dentro do idAndroid", e.getMessage());

        }

    }

}
