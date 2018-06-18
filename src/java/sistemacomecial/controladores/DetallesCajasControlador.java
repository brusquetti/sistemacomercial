package sistemacomecial.controladores;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import sistemacomecial.modelos.Cajas;
import sistemacomecial.modelos.DetallesCajas;
import sistemacomecial.modelos.TiposPagos;
import sistemacomecial.modelos.Ventas;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import javawebts.modelos.Ventas;
//import javawebts.modelos.DetallesCajas;
//import javawebts.modelos.Cajas;
//import javawebts.utiles.Conexion;
//import javawebts.utiles.Utiles;
/**
 *
 * @author Administrator
 */
public class DetallesCajasControlador {

    public static DetallesCajas buscarId(int id) throws SQLException {
        DetallesCajas detallecaja = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.id_caja=dp.id_caja "
                        + "left join ventas a on a.id_venta=dp.id_venta "
                        + "left join tipospagos t on t.id_tipopago=dp.id_tipopago "
                        + "where dp.id_detallecaja=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallecaja = new DetallesCajas();
                        detallecaja.setId_detallecaja(rs.getInt("id_detallecaja"));
                        detallecaja.setImporte(rs.getInt("importe"));

                        Ventas venta = new Ventas();
                        venta.setId_venta(rs.getInt("id_venta"));
                        venta.setNumero_factura_venta(rs.getInt("numero_factura_venta"));
                       // venta.setTotal(rs.getInt("total"));
                        detallecaja.setVenta(venta);

                        Cajas caja = new Cajas();
                        caja.setId_caja(rs.getInt("id_caja"));
                        detallecaja.setCaja(caja);

                        TiposPagos pago = new TiposPagos();
                        pago.setId_tipopago(rs.getInt("id_tipopago"));
                        pago.setNombre_tipopago(rs.getString("nombre_tipopago"));
                        detallecaja.setPago(pago);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecaja;
    }

    public static String buscarIdCaja(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas  dp "
                        + "left join ventas a on a.id_venta=dp.id_venta "
                        + "left join tipospagos t on t.id_tipopago=dp.id_tipopago "
                        + "where dp.id_caja=" + id + " "
                        + "order by id_detallecaja";
                //System.out.println("hola "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("importe");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                // + "<td>" + rs.getString("id_detalleajuste") + "</td>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura_venta") + "</td>"
                                //+ "<td>" + rs.getString("id_tipopago") + "</td>"
                                + "<td>" + rs.getString("nombre_tipopago") + "</td>"
                                //+ "<td>" + rs.getString("total") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               //+ "<td>" + rs.getString("vuelto") + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallecaja") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.id_caja=dp.id_caja "
                        + "left join ventas a on a.id_venta=dp.id_venta "
                        + "where upper(a.nombre_venta) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallecaja "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecaja") + "</td>"
                                + "<td>" + rs.getString("id_caja") + "</td>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("nombre_venta") + "</td>"
                                + "<td>" + rs.getInt("costo_venta") + "</td>"
                                + "<td>" + rs.getInt("iva_venta") + "</td>"
                                + "<td>" + rs.getInt("cantidad_ventacaja") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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

    public static boolean agregar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallescajas "
                    + "(id_caja,id_venta,importe,id_tipopago) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getCaja().getId_caja());
                ps.setInt(2, detallecaja.getVenta().getId_venta());
                //ps.setInt(3, detallecaja.getVenta().getNumero_factura_venta());
                ps.setInt(3, detallecaja.getImporte());
                ps.setInt(4, detallecaja.getPago().getId_tipopago());
                //ps.setInt(5, detallecaja.getVuelto());

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

    public static boolean modificar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallescajas set "
                    + "id_caja=?,"
                    + "id_venta=?,"
                    + "cantidad_ventacaja=? "
                    + "where id_detallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getCaja().getId_caja());
                ps.setInt(2, detallecaja.getVenta().getId_venta());
                //  ps.setInt(3, detallecaja.getCantidad_ventacaja());
                ps.setInt(4, detallecaja.getId_detallecaja());
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

    public static boolean eliminar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescajas where id_detallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getId_detallecaja());
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

    public static boolean eliminarc(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.id_caja=dp.id_caja "
                        + "left join ventas a on a.id_venta=dp.id_venta "
                        + " where p.id_caja= " + detallecaja.getCaja().getId_caja();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stocks set cantidad_exi= cantidad_exi - " + rs.getInt("cantidad_ventacaja") + " where id_venta=" + rs.getInt("id_venta") + "";

                        System.out.println(" descontar stcok" + sqli);
                        try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                            psi.executeUpdate();
                            psi.close();
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

                    ps.close();
                    valor = true;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarVentaCaja(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescajas where id_caja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, caja.getId_caja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + caja.getId_caja());
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
