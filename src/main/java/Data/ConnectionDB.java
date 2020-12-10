/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Frankalexandermoacad
 */
public class ConnectionDB {

    public static final String HOST = "184.73.98.87";
    public static final String DB = "aplicativo";

    public static final String JDBC_URL = "jdbc:mysql://"+HOST+":3306/" + DB + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static final String BD_USUARIO = "alex";
    public static final String BD_CLAVE = "Frank.";

    private ConnectionDB() {
    }
    
    public static DataSource getDataSource() {
        BasicDataSource bs = new BasicDataSource();
        bs.setUrl(JDBC_URL);
        bs.setUsername(BD_USUARIO);
        bs.setPassword(BD_CLAVE);
        bs.setInitialSize(10);

        return bs;
    }

    public static Connection getConnection() throws SQLException {
     
        return getDataSource().getConnection();
    }
    
    public static void close(Connection con) throws SQLException{
        con.close();
    }

    public static void close(Statement state) throws SQLException{
        state.close();
    }

    public static void close(ResultSet res) throws SQLException{
        res.close();
    }

    public static void close(PreparedStatement ps) throws SQLException{
        ps.close();
    }

}
