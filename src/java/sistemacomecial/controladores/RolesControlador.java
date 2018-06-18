/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemacomecial.modelos.Roles;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class RolesControlador {
      public static boolean agregar(Roles rol) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into roles(nombre_rol) "
                    + "values('" + rol.getNombre_rol() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Roles buscarId(Roles rol) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from roles where id_rol='" + rol.getId_rol() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    rol.setNombre_rol(rs.getString("nombre_rol"));
                } else {
                    rol.setId_rol(0);
                    rol.setNombre_rol("");
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return rol;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from roles where upper(nombre_rol) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_rol offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_rol") + "</td>"
                                + "<td>" + rs.getString("nombre_rol") + "</td>"
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
    
    public static boolean eliminar(Roles rol) throws SQLException{
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="delete from roles where id_rol="+rol.getId_rol();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    public static boolean modificar(Roles rol) throws SQLException{
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="update roles set nombre_rol='"+rol.getNombre_rol()+"'"
                    +" where id_rol="+rol.getId_rol();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
}
