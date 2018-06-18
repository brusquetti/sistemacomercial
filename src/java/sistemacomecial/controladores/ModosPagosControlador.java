/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemacomecial.modelos.ModosPagos;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;



/**
 *
 * @author Administrator
 */
public class ModosPagosControlador {

    public static boolean agregar(ModosPagos modopago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into modospagos(nombre_modopago) "
                    + "values('" + modopago.getNombre_modopago() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static ModosPagos buscarId(ModosPagos modopago) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from modospagos where id_modopago='" + modopago.getId_modopago() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    modopago.setNombre_modopago(rs.getString("nombre_modopago"));
                } else {
                    modopago.setId_modopago(0);
                    modopago.setNombre_modopago("");
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return modopago;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from modospagos where upper(nombre_modopago) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_modopago offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_modopago") + "</td>"
                                + "<td>" + rs.getString("nombre_modopago") + "</td>"
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
    
    public static boolean eliminar(ModosPagos modopago) throws SQLException{
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="delete from modospagos where id_modopago="+modopago.getId_modopago();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    public static boolean modificar(ModosPagos modopago) throws SQLException{
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="update modospagos set nombre_modopago='"+modopago.getNombre_modopago()+"'"
                    +" where id_modopago="+modopago.getId_modopago();
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
