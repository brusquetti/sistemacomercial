/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemacomecial.modelos.Establecimientos;
import sistemacomecial.modelos.Puestos;
import sistemacomecial.modelos.Timbrados;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class TimbradosControlador {

    public static boolean agregar(Timbrados timbrado) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into timbrados(numero_timbrado,fecha_inicio,fecha_vencimiento,fecha_actual"
                    + ",id_puesto,id_establecimiento,desde,hasta) "
                    + "values('" + timbrado.getNumero_timbrado() + "','"
                    + timbrado.getFecha_inicio() + "','"
                    + timbrado.getFecha_vencimiento() + "','"
                    + timbrado.getFecha_actual() + "','"
                    + timbrado.getPuesto().getId_puesto() + "','"
                    + timbrado.getEstablecimiento().getId_establecimiento() + "','"
                    + timbrado.getDesde() + "','"
                    + timbrado.getHasta() + "')";

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

    public static Timbrados buscarId(Timbrados timbrado) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from timbrados c, puestos cd, establecimientos e "
                    + "where c.id_puesto=cd.id_puesto and"
                    + " c.id_establecimiento=e.id_establecimiento"
                    + " and id_timbrado='" + timbrado.getId_timbrado() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    timbrado.setNumero_timbrado(rs.getString("numero_timbrado"));
                    timbrado.setFecha_inicio(rs.getDate("fecha_inicio"));
                    timbrado.setFecha_vencimiento(rs.getDate("fecha_vencimiento"));
                    timbrado.setFecha_actual(rs.getDate("fecha_actual"));
                    timbrado.setDesde(0);
                    timbrado.setHasta(0);

                    Puestos puesto = new Puestos();
                    puesto.setId_puesto(rs.getInt("id_puesto"));
                    puesto.setNombre_puesto(rs.getString("nombre_puesto"));

                    timbrado.setPuesto(puesto);

                    Establecimientos establecimiento = new Establecimientos();
                    establecimiento.setId_establecimiento(rs.getInt("id_establecimiento"));
                    establecimiento.setNombre_establecimiento(rs.getString("nombre_establecimiento"));

                    timbrado.setEstablecimiento(establecimiento);

                } else {
                    timbrado.setId_timbrado(0);
                    timbrado.setNumero_timbrado("");
                    java.sql.Date fecha_inicio = new java.sql.Date(new java.util.Date().getTime());
                    timbrado.setFecha_inicio(fecha_inicio);
                    java.sql.Date fecha_vencimiento = new java.sql.Date(new java.util.Date().getTime());
                    timbrado.setFecha_vencimiento(fecha_vencimiento);
                    java.sql.Date fecha_actual = new java.sql.Date(new java.util.Date().getTime());
                    timbrado.setFecha_actual(fecha_actual);
                    timbrado.setDesde(0);
                    timbrado.setHasta(0);

                    Puestos puesto = new Puestos();
                    puesto.setId_puesto(0);
                    puesto.setNombre_puesto("");

                    timbrado.setPuesto(puesto);

                    Establecimientos establecimiento = new Establecimientos();
                    establecimiento.setId_establecimiento(0);
                    establecimiento.setNombre_establecimiento("");

                    timbrado.setEstablecimiento(establecimiento);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return timbrado;
    }

    public static String buscarNumero_timbrado(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from timbrados where upper(numero_timbrado) like '%" + nombre.toUpperCase() + "%'"
                        + "order by id_timbrado offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_timbrado") + "</td>"
                                + "<td>" + rs.getString("numero_timbrado") + "</td>"
                                + "<td>" + rs.getDate("fecha_inicio") + "</td>"
                                + "<td>" + rs.getDate("fecha_inicio") + "</td>"
                                + "<td>" + rs.getDate("fecha_inicio") + "</td>"
                                + "<td>" + rs.getInt("desde") + "</td>"
                                + "<td>" + rs.getInt("hasta") + "</td>"
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

    public static boolean modificar(Timbrados timbrado) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update timbrados set numero_timbrado='" + timbrado.getNumero_timbrado()
                    + "',fecha_inicio='" + timbrado.getFecha_inicio() + ""
                    + "',fecha_vencimiento='" + timbrado.getFecha_vencimiento() + ""
                    + "',fecha_actual='" + timbrado.getFecha_actual() + ""
                    + "',id_puesto='" + timbrado.getPuesto().getId_puesto() + ""
                    + "',id_establecimiento='" + timbrado.getEstablecimiento().getId_establecimiento() + ""
                    + "',desde='" + timbrado.getDesde()+ ""
                    + "',hasta='" + timbrado.getHasta()+ ""
                    + "' where id_timbrado=" + timbrado.getId_timbrado();
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

    public static boolean eliminar(Timbrados timbrado) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from timbrados where id_timbrado=" + timbrado.getId_timbrado();
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
