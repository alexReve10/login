/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.UsuarioDTO;
import Vista.IniciarSesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frankalexandermoncad
 */
public class UsuarioDAOJDBC extends IniciarSesion  {

    private static final String SQL_ALL = "SELECT nombre, nit, correo, clave FROM empresa";
    private static final String SQL_CREAR = "INSERT INTO `empresa`(`nombre`, `nit`, `correo`, `clave`) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE `empresa` SET `nombre`=?, `correo`=?,`clave`=? WHERE nit=?";
    private static final String SQL_DELETE = "DELETE FROM `empresa` WHERE nit=?";

    
    public List<UsuarioDTO> consultar() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        UsuarioDTO empresa;
        List<UsuarioDTO> empresas = new ArrayList<>();

        try {
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement(this.SQL_ALL);
            res = ps.executeQuery();
            while (res.next()) {
                empresa = new UsuarioDTO(
                        res.getString("nombre"),
                        res.getString("nit"),
                        res.getString("correo"),
                        res.getString("clave")
                );

                empresas.add(empresa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnectionDB.close(res);
                ConnectionDB.close(ps);
                ConnectionDB.close(con);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return empresas;
    }

    public int crear(UsuarioDTO e) {
        return this.ejecutarSQL(e, 3);
    }

    public int actualizar(UsuarioDTO e) {
        return this.ejecutarSQL(e, 2);
    }

    public int eliminar(UsuarioDTO e) {
        return this.ejecutarSQL(e, 1);
    }

    private int ejecutarSQL(UsuarioDTO e, int t) {
        Connection con = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            con = ConnectionDB.getConnection();
            switch (t) {
                case 1: {
                    ps = con.prepareStatement(this.SQL_DELETE);
                    ps.setString(1, e.getUsuario());
                    break;
                }
                case 2: {
                    ps = con.prepareStatement(this.SQL_UPDATE);
                    ps.setString(1, e.getNombre());
                    ps.setString(2, e.getCorreo());
                    ps.setString(3, e.getClave());
                    ps.setString(4, e.getUsuario());
                    break;
                }
                case 3: {
                    ps = con.prepareStatement(this.SQL_CREAR);
                    ps.setString(1, e.getNombre());
                    ps.setString(2, e.getUsuario());
                    ps.setString(3, e.getCorreo());
                    ps.setString(4, e.getClave());
                    break;
                }
                default: break;
            }
            
            registros = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnectionDB.close(ps);
                ConnectionDB.close(con);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return registros;
    }
}

