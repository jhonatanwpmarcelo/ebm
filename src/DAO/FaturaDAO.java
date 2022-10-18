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
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import util.Funcoes;

/**
 *
 * @author Emanuel
 */
public class FaturaDAO {

    /**
     *
     */
    public Fatura buscarFatura(Fatura f) throws Exception {

        String query = "";

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {
            System.out.println("------> " + "Buscando dados da fatura: " + f.getFatura());
            try {

                query = "SELECT \n"
                        + "    f.id AS id_fatura,\n"
                        + "    DATEDIFF(NOW(), b.data_vencimento) diasVencidos,\n"
                        + "    format(f.valor,2) as valor,\n"
                        + "    DATE_FORMAT(periodo_faturado, '%M de %Y') mes_faturado,\n"
                        + "    DATE_FORMAT(periodo_faturado, '%m-%Y') mes_faturado_simples,\n"
                        + "    DATE_FORMAT(data_faturamento, '%d/%m/%Y') data_faturamento,\n"
                        + "    DATE_FORMAT(b.data_vencimento, '%d/%m/%Y') data_vencimento,\n"
                        + "    c.fantasia nome,\n"
                        + "    c.nome empresa,\n"
                        + "    c.cpf_cnpj doc,\n"
                        + "    b.nosso_numero,\n"
                        + "    b.linha_digitavel,\n"
                        + "    b.corpo corpo_boleto,\n"
                        + "    n.corpo corpo_nota_fiscal,\n"
                        + "    c.usuario username,\n"
                        + "    c.senha password,\n"
                        + "    n.numero AS numero,\n"
                        + "    c.id AS idInterno\n"
                        + "FROM\n"
                        + "    ebm_interno.fatura AS f,\n"
                        + "    ebm_interno.boleto AS b,\n"
                        + "    ebm_interno.nota_fiscal AS n,\n"
                        + "    ebm_interno.cliente AS c\n"
                        + "WHERE\n"
                        + "        c.id = f.id_cliente\n"
                        + "        AND n.id = f.id_nota_fiscal\n"
                        + "        AND b.id = f.id_boleto\n"
                        + "        AND f.codigo_fatura = '" + f.getFatura() + "';";

                  // System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                        f.setValor(rs.getString("valor"));
                        f.setDiasVencidos(rs.getInt("diasVencidos"));
                        f.setMesFaturado(rs.getString("mes_faturado"));
                        f.setDataVencimento(rs.getString("data_vencimento"));

                        f.setBoleto(rs.getString("corpo_boleto"));
                        f.setNotaFiscal(rs.getString("corpo_nota_fiscal"));
                        f.setCodigoBarras(rs.getString("linha_digitavel"));
                        f.setNumeroNota(rs.getString("numero"));

                        f.getC().setNomeFantasia(rs.getString("empresa"));
                        f.getC().setIdInterno(rs.getInt("idInterno"));
                        f.getC().setCnpj(rs.getString("doc"));

                        //   System.err.println(rs.getString("linha_digitavel"));
                    }

                    st.close();
                    rs.close();

                }
            } catch (Exception e) {
                System.err.println("Ocorreu uma exceção! -- " + e.getMessage());
                e.printStackTrace();

            }

        }

        return f;

    }

    /**
     *
     */
    public Cliente buscarClientes(Cliente c) throws Exception {

        String query = "";

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {

                query = "SELECT * FROM ebm_interno.cliente where id = '" + c.getIdInterno() + "' and ativo = 1;";

                //  System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
                        c.setIdInterno(rs.getInt("id"));
                        c.setIdMagnus(rs.getInt("id_magnus"));
                        c.setNomeFantasia(rs.getString("nome"));

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

        return c;

    }

    public Cliente buscaNumerosWhatsApp(Cliente c) throws Exception {

        String query = "";
        WhatsApp wa = new WhatsApp();

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {

                query = "SELECT \n"
                        + "    nome, numero, financeiro, tecnico, bi\n"
                        + "FROM\n"
                        + "    ebm_interno.whatsapp\n"
                        + "WHERE\n"
                        + "    id_cliente =  " + c.getIdInterno() + ";";

//                    System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                        wa = new WhatsApp();

                        wa.setNome(rs.getString("nome"));
                        wa.setNumero(rs.getString("numero"));
                        wa.setFinanceiro(rs.getInt("financeiro"));
                        wa.setTecnico(rs.getInt("tecnico"));
                        wa.setBi(rs.getInt("Bi"));

                        c.getWhatsapp().add(wa);
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

        return c;

    }

    public boolean verificaEnvio(Fatura f, String numero, int tipo) throws Exception {

        String query;
        
       

        boolean retorno = false;

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {

                query = "SELECT \n"
                        + "    COUNT(*) total\n"
                        + "FROM\n"
                        + "    ebm_interno.whatsApp_envio\n"
                        + "WHERE\n"
                        + "    codigo_controle = '" + f.getFatura() + "'\n"
                        + "        AND numero = '" + numero + "'\n"
                        + "        AND tipo = " + tipo + ";";

//                    System.err.println(query);
                try (Statement st = conn.createStatement()) {
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                        if (rs.getInt("total") > 0) {
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

    public ArrayList<String> buscaFaturasAtrasadas() throws Exception {

        String query;

        ArrayList<String> faturas = new ArrayList<String>();

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {

              query = "SELECT \n"
                        + "    codigo_fatura\n"
                        + "FROM\n"
                        + "    ebm_interno.fatura\n"
                        + "WHERE\n"
                        + "    situacao = 2 AND valor > 0\n"
                        + "        AND id_nota_fiscal IS NOT NULL\n"
                        + "        AND DATE_FORMAT(periodo_faturado, '%Y%m') > '202104';";

                System.err.println(query);
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

public ArrayList<String> buscaFaturasTeste() throws Exception {

        String query;

        ArrayList<String> faturas = new ArrayList<String>();

        try (Connection conn = ConexaoMysql_BI.getConnectionMysql()) {

            try {

                query = "select codigo_fatura from ebm_interno.fatura where id_boleto is not null and id_nota_fiscal is not null order by id desc limit 1";

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
