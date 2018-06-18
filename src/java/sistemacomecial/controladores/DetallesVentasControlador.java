package sistemacomecial.controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import sistemacomecial.modelos.Ventas;
import sistemacomecial.modelos.DetallesVentas;
import sistemacomecial.modelos.Productos;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

//import javawebts.modelos.Productos;
//import javawebts.modelos.DetallesVentas;
//import javawebts.modelos.Ventas;
//import javawebts.utiles.Conexion;
//import javawebts.utiles.Utiles;
/**
 *
 * @author Administrator
 */
public class DetallesVentasControlador {

    public static DetallesVentas buscarId(int id) throws SQLException {
        DetallesVentas detalleventa = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dp "
                        + " left join ventas p on p.id_venta=dp.id_venta "
                        + " left join productos a on a.id_producto=dp.id_producto  "
                        + "where dp.id_detalleventa=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleventa = new DetallesVentas();
                        detalleventa.setId_detalleventa(rs.getInt("id_detalleventa"));
                        detalleventa.setCantidad_productoventa(rs.getInt("cantidad_productoventa"));

                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setCosto_venta(rs.getInt("costo_venta"));
                        producto.setIva_producto(rs.getInt("iva_producto"));
                        detalleventa.setProducto(producto);

                        Ventas venta = new Ventas();
                        venta.setId_venta(rs.getInt("id_venta"));
                        detalleventa.setVenta(venta);

                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleventa;
    }

    public static String buscarIdVenta(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dp "
                        + "left join ventas p on p.id_venta=dp.id_venta "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_venta=" + id + " "
                        + "order by id_detalleventa";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    int total = 0;
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int totalf = 0;
                    //BigDecimal total = BigDecimal.ZERO;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;
                    //BigDecimal totald = BigDecimal.ZERO;

                    while (rs.next()) {

                        iva = rs.getInt("iva_producto");
                        int cantidad = rs.getInt("cantidad_productoventa");
                        int costo = rs.getInt("costo_venta");
//                        BigDecimal cantidad = rs.getBigDecimal("cantidad_productoventa");
//                        total = total.add(cantidad);
//                        BigDecimal cantidad1 = rs.getBigDecimal("total");

                        if (iva == 0) {

                            totalexentas = costo * cantidad;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = costo * cantidad;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = costo * cantidad;
                            total10 = total10 + totaliva10;
                        }

                        liqiva5 = total5 / 21;
                        liqiva10 = total10 / 11;

                        total = liqiva5 + liqiva10;

                        totalf = total5 + totale + total10;

                        //totald = totald.add(cantidad1);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                //+ "<td>" + rs.getString("id_detalleventa") + "</td>"
                                //+ "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getInt("costo_venta") + "</td>"
                                + "<td>" + df.format(totalexentas) + "</td>"
                                + "<td>" + df.format(totaliva5) + "</td>"
                                + "<td>" + df.format(totaliva10) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detalleventa") + ")'"
                                + " type='button' class='btn btn-primary btn-sm bloquearBoton'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";

                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=7>No existen registros ...</td></tr>";
                    } else {
                        //tabla += "<tr><td colspan=4>TOTAL</td><td class='centrado'>" + df.format(total) + "</td><td class='izquierda'>" + df.format(totald) + "</td></tr>";
                        tabla += "<tr><td colspan=3><b>SUB-TOTAL:</b></td><td class='left'><b>" + df.format(totale) + "</b></td><td><b>" + df.format(total5) + "</b></td><th>" + df.format(total10) + "</td></tr>";
                        tabla += "<tr><td colspan=6><b>LIQUIDACION DEL IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(total) + " </td> </tr>";
                        tabla += "<tr><td colspan=5 ><b><P ALIGN=right>TOTAL A PAGAR:</b></td><td class='left'><b>" + df.format(totalf) + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";
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
                String sql = "select * from detallesventas dp "
                        + "left join ventas p on p.id_venta=dp.id_venta "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where upper(a.nombre_producto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detalleventa "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detalleventa") + "</td>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getInt("costo_venta") + "</td>"
                                + "<td>" + rs.getInt("iva_producto") + "</td>"
                                + "<td>" + rs.getInt("cantidad_productoventa") + "</td>"
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

    public static boolean agregar(DetallesVentas detalleventa) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallesventas "
                    + "(id_venta,id_producto,cantidad_productoventa,total) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleventa.getVenta().getId_venta());
                ps.setInt(2, detalleventa.getProducto().getId_producto());
                ps.setInt(3, detalleventa.getCantidad_productoventa());
                ps.setInt(4, detalleventa.getTotal());
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

    public static boolean modificar(DetallesVentas detalleventa) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallesventas set "
                    + "id_venta=?,"
                    + "id_producto=?,"
                    + "cantidad_productoventa=? "
                    + "where id_detalleventa=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleventa.getVenta().getId_venta());
                ps.setInt(2, detalleventa.getProducto().getId_producto());
                ps.setInt(3, detalleventa.getCantidad_productoventa());
                ps.setInt(4, detalleventa.getId_detalleventa());
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

    public static boolean eliminar(DetallesVentas detalleventa) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesventas where id_detalleventa=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleventa.getId_detalleventa());
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

    public static boolean eliminarc(DetallesVentas detalleventa) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dp "
                        + "left join ventas p on p.id_venta=dp.id_venta "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + " where p.id_venta= " + detalleventa.getVenta().getId_venta();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stocks set cantidad_existente= cantidad_existente + " + rs.getInt("cantidad_productoventa") + " where id_producto=" + rs.getInt("id_producto") + "";

                        System.out.println(" aumentar stcok" + sqli);
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

    public static boolean eliminarProductoVenta(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesventas where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, venta.getId_venta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + venta.getId_venta());
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
