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
import sistemacomecial.modelos.Clientes;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class ClientesControlador {

    public static boolean agregar(Clientes cliente) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into clientes(ruc_cliente,nombre_cliente,apellido_cliente,cedula_cliente"
                    + ",fecha_nacimiento_cliente,direccion_cliente,correo_cliente,id_ciudad,telefono_cliente) "
                    + "values('" + cliente.getRuc_cliente() + "','"
                    + cliente.getNombre_cliente() + "','"
                    + cliente.getApellido_cliente() + "','"
                    + cliente.getCedula_cliente() + "','"
                    + cliente.getFecha_nacimiento_cliente() + "','"
                    + cliente.getDireccion_cliente() + "','"
                    + cliente.getCorreo_cliente() + "','"
                    + cliente.getCiudad().getId_ciudad() + "','"
                    + cliente.getTelefono_cliente() + "')";

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

    public static Clientes buscarId(Clientes cliente) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from clientes c, ciudades cd "
                    + "where c.id_ciudad=cd.id_ciudad and id_cliente='" + cliente.getId_cliente() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                    cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                    cliente.setApellido_cliente(rs.getString("apellido_cliente"));
                    cliente.setCedula_cliente(rs.getInt("cedula_cliente"));
                    cliente.setDireccion_cliente(rs.getString("direccion_cliente"));
                    cliente.setTelefono_cliente(rs.getString("telefono_cliente"));
                    cliente.setCorreo_cliente(rs.getString("correo_cliente"));
                    cliente.setFecha_nacimiento_cliente(rs.getDate("fecha_nacimiento_cliente"));

                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));

                    cliente.setCiudad(ciudad);

                } else {
                    cliente.setId_cliente(0);
                    cliente.setRuc_cliente("");
                    cliente.setNombre_cliente("");
                    cliente.setApellido_cliente("");
                    cliente.setCedula_cliente(0);

                    java.sql.Date fecha_nacimiento_cliente = new java.sql.Date(new java.util.Date().getTime());
                    cliente.setFecha_nacimiento_cliente(fecha_nacimiento_cliente);

                    cliente.setDireccion_cliente("");
                    cliente.setTelefono_cliente("");
                    cliente.setCorreo_cliente("");

                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");

                    cliente.setCiudad(ciudad);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return cliente;
    }

    public static Clientes buscarCedula(int id) throws SQLException {
        Clientes cliente = null;

        if (Conexion.conectar()) {
            String sql = "select * from clientes where cedula_cliente='" + id + "'";

            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    cliente = new Clientes();
                    cliente.setCedula_cliente(0);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return cliente;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from clientes where upper(nombre_cliente) like '%" + nombre.toUpperCase() + "%'"
                        + "order by id_cliente offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("ruc_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td>" + rs.getString("apellido_cliente") + "</td>"
                                + "<td>" + rs.getInt("cedula_cliente") + "</td>"
                                + "<td>" + rs.getDate("fecha_nacimiento_cliente") + "</td>"
                                + "<td>" + rs.getString("direccion_cliente") + "</td>"
                                + "<td>" + rs.getString("telefono_cliente") + "</td>"
                                + "<td>" + rs.getString("correo_cliente") + "</td>"
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

    public static boolean modificar(Clientes cliente) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update clientes set nombre_cliente='" + cliente.getNombre_cliente()
                    + "', apellido_cliente='" + cliente.getApellido_cliente() + ""
                    + "', cedula_cliente='" + cliente.getCedula_cliente() + ""
                    + "',fecha_nacimiento_cliente='" + cliente.getFecha_nacimiento_cliente() + ""
                    + "', direccion_cliente='" + cliente.getDireccion_cliente() + ""
                    + "', telefono_cliente='" + cliente.getTelefono_cliente() + ""
                    + "', correo_cliente='" + cliente.getCorreo_cliente() + ""
                    + "', ruc_cliente='" + cliente.getRuc_cliente() + ""
                    + "',id_ciudad='" + cliente.getCiudad().getId_ciudad() + ""
                    + "' where id_cliente=" + cliente.getId_cliente();
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

    public static boolean eliminar(Clientes cliente) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from clientes where id_cliente=" + cliente.getId_cliente();
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
