/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.controladores;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemacomecial.modelos.Ciudades;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class CiudadesControlador {
            public static boolean agregar(Ciudades ciudad) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into ciudades(nombre_ciudad) "
                    + "values('" + ciudad.getNombre_ciudad() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Ciudades buscarId(Ciudades ciudad) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from ciudades where id_ciudad='" + ciudad.getId_ciudad() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));

                } else {
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return ciudad;
    }
    public static String buscarNombre(String nombre, int pagina) throws SQLException{
        int offset = (pagina-1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from ciudades where upper(nombre_ciudad) like '%" + nombre.toUpperCase() + "%'"
                        + "order by id_ciudad offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while(rs.next()){
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_ciudad") + "</td>"
                                + "<td>" + rs.getString("nombre_ciudad") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }catch(SQLException ex){
                    System.err.println("Error: "+ ex);
                }
                Conexion.cerrar();
            }catch(Exception ex){
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    public static boolean modificar(Ciudades ciudad) throws SQLException{
       boolean valor=false;
        if(Conexion.conectar()){
            String sql="update ciudades set nombre_ciudad='"+ciudad.getNombre_ciudad()
                    +"' where id_ciudad="+ciudad.getId_ciudad();
            System.out.println(sql);
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor; 
    }
    public static boolean eliminar(Ciudades ciudad) throws SQLException{
              boolean valor=false;
        if(Conexion.conectar()){
            String sql="delete from ciudades where id_ciudad="+ciudad.getId_ciudad();
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
