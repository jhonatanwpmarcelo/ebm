package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.Funcoes;

/**
 *
 * @author Emanuel
 */
public class ConexaoMysql_BI {

    private static String ip = "bd.ebmtecnologias.com"; 
    private static String banco = "";
    private static String driver = "com.mysql.jdbc.Driver";
    private static Connection conexao = null;
    private static String usuario = "ebmtech";
    private static String senha = "E228595000oO!";
    //  private static String url="jdbc:mysql://"+ip+"/"+banco+"",usuario, senha;

    /**
     *
     * @return @throws ClassNotFoundException
     */
    public static Connection getConnectionMysql() throws Exception {

        try {
            Class.forName(driver);
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + banco + "", usuario, senha);
            }

            return conexao;
        } catch (SQLException e) {

            closeConnection();
            throw new RuntimeException(e);
        }

    }

    /**
     *
     */
    public static void closeConnection() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
