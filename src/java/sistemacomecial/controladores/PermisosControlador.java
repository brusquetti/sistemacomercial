/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemacomecial.modelos.Formularios;
import sistemacomecial.modelos.Permisos;
import sistemacomecial.modelos.Roles;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;
//import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class PermisosControlador {

    public static boolean agregar(Permisos permiso) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into permisos(id_rol,id_formulario,orden_permiso) "
                    + "values('" + permiso.getRol().getId_rol() + "','"
                    + permiso.getFormulario().getId_formulario() + "','"
                    + permiso.getOrden_permiso() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Permisos buscarId(Permisos permiso) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from permisos,roles,formularios where "
                    + "roles.id_rol=permisos.id_rol and formularios.id_formulario\n"
                    + "=permisos.id_formulario and id_permiso='" + permiso.getId_permiso() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    permiso.setOrden_permiso(rs.getString("orden_permiso"));
                    permiso.setId_permiso(rs.getInt("id_permiso"));
                    //cargamos el rol
                    Roles rol = new Roles();
                    rol.setId_rol(rs.getInt("id_rol"));
                    rol.setNombre_rol(rs.getString("nombre_rol"));
                    permiso.setRol(rol);
                    //cargamos el formulario
                    Formularios formulario = new Formularios();
                    formulario.setId_formulario(rs.getInt("id_formulario"));
                    formulario.setNombre_formulario(rs.getString("nombre_formulario"));
                    permiso.setFormulario(formulario);
                } else {
                    permiso.setId_permiso(0);
                    permiso.setOrden_permiso("");
                    Roles rol = new Roles();
                    rol.setId_rol(0);
                    rol.setNombre_rol("");
                    permiso.setRol(rol);
                    //cargamos el formulario
                    Formularios formulario = new Formularios();
                    formulario.setId_formulario(0);
                    formulario.setNombre_formulario("");
                    permiso.setFormulario(formulario);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return permiso;
    }

    public static String buscarOrdenPermiso(String ordenpermiso, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisos where upper(orden_permiso) like '%" + ordenpermiso.toUpperCase() + "%'"
                        + " order by id_permiso offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_permiso") + "</td>"
                                + "<td>" + rs.getString("orden_permiso") + "</td>"
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

    public static boolean eliminar(Permisos permiso) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from permisos where id_permiso=" + permiso.getId_permiso();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean modificar(Permisos permiso) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update permisos set orden_permiso='" + permiso.getOrden_permiso() + "',"
                    + "id_rol='" + permiso.getRol().getId_rol() + "',"
                    + "id_formulario='" + permiso.getFormulario().getId_formulario()+ "'"
                    + " where id_permiso=" + permiso.getId_permiso();
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
