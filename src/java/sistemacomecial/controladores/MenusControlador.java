/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemacomecial.modelos.Menus;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class MenusControlador {

    public static boolean agregar(Menus menu) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into menus(nombre_menu,codigo_menu) "
                    + "values('" + menu.getNombre_menu() + "','" + menu.getCodigo_menu() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Menus buscarId(Menus menu) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from menus where id_menu='" + menu.getId_menu() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    menu.setNombre_menu(rs.getString("nombre_menu"));
                    menu.setCodigo_menu(rs.getString("codigo_menu"));
                } else {
                    menu.setId_menu(0);
                    menu.setNombre_menu("");
                    menu.setCodigo_menu("");
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return menu;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from menus where upper(nombre_menu) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_menu offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_menu") + "</td>"
                                + "<td>" + rs.getString("nombre_menu") + "</td>"
                                + "<td>" + rs.getString("codigo_menu") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error:" + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Menus menu) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from menus where id_menu=" + menu.getId_menu();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean modificar(Menus menu) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update menus set nombre_menu='" + menu.getNombre_menu() + "',"
                    + "codigo_menu='" + menu.getCodigo_menu() + "'"
                    + " where id_menu=" + menu.getId_menu();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
}
