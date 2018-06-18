/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sistemacomecial.modelos.Cuentas;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;


/**
 *
 * @author Administrator
 */
public class CuentasControlador {

    public static Cuentas buscarId(int id) throws SQLException {
        Cuentas cuentas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cuentas c "
                        + "left join usuarios u on c.id_usuario=u.id_usuario "
                        // + "left join tipospagos t on p.id_tipopago=t.id_tipopago "
                        + "where id_cuenta=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        cuentas = new Cuentas();
                        cuentas.setId_cuenta(rs.getInt("id_cuenta"));
                        cuentas.setFecha_apertura(rs.getDate("fecha_apertura"));
                        cuentas.setEstado_cuenta(rs.getString("estado_cuenta"));

                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return cuentas;
    }
    public static Cuentas buscarIdestado(int usuario) throws SQLException {
        Cuentas cuentas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cuentas c "
                        + "left join usuarios u on c.id_usuario=u.id_usuario "
                        // + "left join tipospagos t on p.id_tipopago=t.id_tipopago "
                        + "where estado_cuenta='ABIERTO' and c.id_usuario=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                  
                    ps.setInt(1, usuario);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        cuentas = new Cuentas();
                        cuentas.setId_cuenta(rs.getInt("id_cuenta"));
                        cuentas.setFecha_apertura(rs.getDate("fecha_apertura"));
                     
                        cuentas.setEstado_cuenta(rs.getString("estado_cuenta"));

                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return cuentas;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cuentas p "
                        + "left join usuarios c on c.id_usuario=p.id_usuario "
                        //   + "left join tipospagos t on t.id_tipopago=p.id_tipopago "
                        + "where upper(nombre_usuario) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_cuenta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("-->cuenta " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cuenta") + "</td>"
                                + "<td>" + rs.getString("fecha_apertura") + "</td>"
                               
                                + "<td>" + rs.getString("estado_cuenta") + "</td>"
                                + "<td>" + rs.getString("id_usuario") + "</td>"
                                + "<td>" + rs.getString("nombre_usuario") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(Cuentas cuenta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = cuenta.getUsuario().getId_usuario();

            String sql = "insert into cuentas(fecha_apertura"
                    + ",estado_cuenta,id_usuario)"
                    + "values('"
                    + cuenta.getFecha_apertura() + "','"
                    + cuenta.getEstado_cuenta() + "','"
                    
                    + v1 + "')";

            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_cuenta = keyset.getInt(1);
                    cuenta.setId_cuenta(id_cuenta);

                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }

            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Cuentas cuenta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update cuentas set id_usuario=? " + cuenta.getUsuario().getId_usuario() + " ,"
                    + "fecha_apertura=" + cuenta.getFecha_apertura() + ""
                    + "estado_cuenta=" + cuenta.getEstado_cuenta() + ""
                    + "id_usuario=" + cuenta.getUsuario().getId_usuario() + ""
                    + "where id_cuenta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, cuenta.getUsuario().getId_usuario());
                ps.setInt(2, cuenta.getId_cuenta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Cuentas cuenta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from cuentas where id_cuenta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, cuenta.getId_cuenta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificarestado(Cuentas cuenta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update cuentas set estado_cuenta='CERRADO' "
                    + " where id_cuenta=" + cuenta.getId_cuenta();
            try {

                Conexion.getSt().executeUpdate(sql);

                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
