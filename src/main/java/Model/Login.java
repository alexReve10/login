/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.ConnectionDB;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author frankalexandermoncad
 */
public class Login {

    

    public Connection cn = null;
    public Statement st = null;

    public Statement Conectar() {

        try {

            Connection cn = ConnectionDB.getConnection();

            st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        } catch (SQLException i) {
            JOptionPane.showMessageDialog(null, i);
        }
        return st;

    }

}
