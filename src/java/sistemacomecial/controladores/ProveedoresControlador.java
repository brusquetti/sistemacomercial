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
import sistemacomecial.modelos.Proveedores;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class ProveedoresControlador {

    public static boolean agregar(Proveedores proveedor) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into proveedores(ruc_proveedor,nombre_proveedor"
                    + ",correo_proveedor"
                    + ",direccion_proveedor,id_ciudad,telefono_proveedor,telefono2_proveedor) "
                    + "values('" + proveedor.getRuc_proveedor() + "','"
                    + proveedor.getNombre_proveedor() + "','"
                    + proveedor.getCorreo_proveedor() + "','"
                    + proveedor.getDireccion_proveedor() + "','"
                    + proveedor.getCiudad().getId_ciudad() + "','"
                    + proveedor.getTelefono_proveedor() + "','"
                    + proveedor.getTelefono2_proveedor() + "')";
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Proveedores buscarId(Proveedores proveedor) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from proveedores c, ciudades cd "
                    + "where c.id_ciudad=cd.id_ciudad and id_proveedor='" + proveedor.getId_proveedor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));
                    proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                    proveedor.setCorreo_proveedor(rs.getString("correo_proveedor"));
                    proveedor.setTelefono_proveedor(rs.getString("telefono_proveedor"));
                    proveedor.setTelefono2_proveedor(rs.getString("telefono2_proveedor"));
                    proveedor.setDireccion_proveedor(rs.getString("direccion_proveedor"));
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));

                    proveedor.setCiudad(ciudad);

                } else {
                    proveedor.setId_proveedor(0);
                    proveedor.setRuc_proveedor("");
                    proveedor.setNombre_proveedor("");
                    proveedor.setCorreo_proveedor("");
                    proveedor.setTelefono_proveedor("");
                    proveedor.setTelefono2_proveedor("");
                    proveedor.setDireccion_proveedor("");
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");

                    proveedor.setCiudad(ciudad);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return proveedor;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from proveedores where upper(nombre_proveedor) like '%" + nombre.toUpperCase() + "%'"
                        + "order by id_proveedor offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_proveedor") + "</td>"
                                + "<td>" + rs.getString("ruc_proveedor") + "</td>"
                                + "<td>" + rs.getString("nombre_proveedor") + "</td>"
                                + "<td>" + rs.getString("correo_proveedor") + "</td>"
                                + "<td>" + rs.getString("telefono_proveedor") + "</td>"
                                + "<td>" + rs.getString("telefono2_proveedor") + "</td>"
                                + "<td>" + rs.getString("direccion_proveedor") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(Proveedores proveedor) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update proveedores set nombre_proveedor='" + proveedor.getNombre_proveedor()
                    + "', correo_proveedor='" + proveedor.getCorreo_proveedor() + ""
                    + "', telefono_proveedor='" + proveedor.getTelefono_proveedor() + ""
                    + "', telefono2_proveedor='" + proveedor.getTelefono2_proveedor() + ""
                    + "', direccion_proveedor='" + proveedor.getDireccion_proveedor() + ""
                    + "', ruc_proveedor='" + proveedor.getRuc_proveedor() + ""
                    + "',id_ciudad='" + proveedor.getCiudad().getId_ciudad() + ""
                    + "' where id_proveedor=" + proveedor.getId_proveedor();
            System.out.println(sql);

            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Proveedores proveedor) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from proveedores where id_proveedor=" + proveedor.getId_proveedor();
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
