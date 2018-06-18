/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.controladores;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemacomecial.modelos.Categorias;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class CategoriasControlador {
            public static boolean agregar(Categorias categoria) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into categorias(nombre_categoria) "
                    + "values('" + categoria.getNombre_categoria() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Categorias buscarId(Categorias categoria) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from categorias where id_categoria='" + categoria.getId_categoria() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    categoria.setNombre_categoria(rs.getString("nombre_categoria"));

                } else {
                    categoria.setId_categoria(0);
                    categoria.setNombre_categoria("");
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return categoria;
    }
    public static String buscarNombre(String nombre, int pagina) throws SQLException{
        int offset = (pagina-1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from categorias where upper(nombre_categoria) like '%" + nombre.toUpperCase() + "%'"
                        + "order by id_categoria offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while(rs.next()){
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_categoria") + "</td>"
                                + "<td>" + rs.getString("nombre_categoria") + "</td>"
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
    public static boolean modificar(Categorias categoria) throws SQLException{
       boolean valor=false;
        if(Conexion.conectar()){
            String sql="update categorias set nombre_categoria='"+categoria.getNombre_categoria()
                    +"' where id_categoria="+categoria.getId_categoria();
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
    public static boolean eliminar(Categorias categoria) throws SQLException{
              boolean valor=false;
        if(Conexion.conectar()){
            String sql="delete from categorias where id_categoria="+categoria.getId_categoria();
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
