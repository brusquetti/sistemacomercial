/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemacomecial.modelos.TiposPagos;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;



/**
 *
 * @author Administrator
 */
public class TiposPagosControlador {

    public static boolean agregar(TiposPagos tipopago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into tipospagos(nombre_tipopago) "
                    + "values('" + tipopago.getNombre_tipopago() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static TiposPagos buscarId(TiposPagos tipopago) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from tipospagos where id_tipopago='" + tipopago.getId_tipopago() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    tipopago.setNombre_tipopago(rs.getString("nombre_tipopago"));
                } else {
                    tipopago.setId_tipopago(0);
                    tipopago.setNombre_tipopago("");
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return tipopago;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from tipospagos where upper(nombre_tipopago) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_tipopago offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_tipopago") + "</td>"
                                + "<td>" + rs.getString("nombre_tipopago") + "</td>"
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
    
    public static boolean eliminar(TiposPagos tipopago) throws SQLException{
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="delete from tipospagos where id_tipopago="+tipopago.getId_tipopago();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    public static boolean modificar(TiposPagos tipopago) throws SQLException{
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="update tipospagos set nombre_tipopago='"+tipopago.getNombre_tipopago()+"'"
                    +" where id_tipopago="+tipopago.getId_tipopago();
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
