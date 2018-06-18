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
import sistemacomecial.modelos.Compras;
import sistemacomecial.modelos.DetallesCompras;
import sistemacomecial.modelos.Productos;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

//import javawebts.modelos.Productos;
//import javawebts.modelos.DetallesCompras;
//import javawebts.modelos.Compras;
//import javawebts.utiles.Conexion;
//import javawebts.utiles.Utiles;
/**
 *
 * @author Administrator
 */
public class DetallesComprasControlador {

    public static DetallesCompras buscarId(int id) throws SQLException {
        DetallesCompras detallecompra = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescompras dp "
                        + " left join compras p on p.id_compra=dp.id_compra "
                        + " left join productos a on a.id_producto=dp.id_producto  "
                        + "where dp.id_detallecompra=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallecompra = new DetallesCompras();
                        detallecompra.setId_detallecompra(rs.getInt("id_detallecompra"));
                        detallecompra.setCantidad_productocompra(rs.getInt("cantidad_productocompra"));

                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setCosto_compra(rs.getInt("costo_compra"));
                        producto.setIva_producto(rs.getInt("iva_producto"));
                        detallecompra.setProducto(producto);

                        Compras compra = new Compras();
                        compra.setId_compra(rs.getInt("id_compra"));
                        detallecompra.setCompra(compra);

                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecompra;
    }

    public static String buscarIdCompra(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescompras dp "
                        + "left join compras p on p.id_compra=dp.id_compra "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_compra=" + id + " "
                        + "order by id_detallecompra";
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
                        int cantidad = rs.getInt("cantidad_productocompra");
                        int costo = rs.getInt("costo_compra");
//                        BigDecimal cantidad = rs.getBigDecimal("cantidad_productocompra");
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
                                //+ "<td>" + rs.getString("id_detallecompra") + "</td>"
                                //+ "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getInt("costo_compra") + "</td>"
                                + "<td>" + df.format(totalexentas) + "</td>"
                                + "<td>" + df.format(totaliva5) + "</td>"
                                + "<td>" + df.format(totaliva10) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallecompra") + ")'"
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
                String sql = "select * from detallescompras dp "
                        + "left join compras p on p.id_compra=dp.id_compra "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where upper(a.nombre_producto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallecompra "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecompra") + "</td>"
                                + "<td>" + rs.getString("id_compra") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getInt("costo_compra") + "</td>"
                                + "<td>" + rs.getInt("iva_producto") + "</td>"
                                + "<td>" + rs.getInt("cantidad_productocompra") + "</td>"
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

    public static boolean agregar(DetallesCompras detallecompra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallescompras "
                    + "(id_compra,id_producto,cantidad_productocompra,total) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecompra.getCompra().getId_compra());
                ps.setInt(2, detallecompra.getProducto().getId_producto());
                ps.setInt(3, detallecompra.getCantidad_productocompra());
                ps.setInt(4, detallecompra.getTotal());
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

    public static boolean modificar(DetallesCompras detallecompra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallescompras set "
                    + "id_compra=?,"
                    + "id_producto=?,"
                    + "cantidad_productocompra=? "
                    //+ "total=? "
                    + "where id_detallecompra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecompra.getCompra().getId_compra());
                ps.setInt(2, detallecompra.getProducto().getId_producto());
                ps.setInt(3, detallecompra.getCantidad_productocompra());
                //ps.setInt(4, detallecompra.getTotal());
                ps.setInt(4, detallecompra.getId_detallecompra());
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

    public static boolean eliminar(DetallesCompras detallecompra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescompras where id_detallecompra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecompra.getId_detallecompra());
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

    public static boolean eliminarc(DetallesCompras detallecompra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescompras dp "
                        + "left join compras p on p.id_compra=dp.id_compra "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + " where p.id_compra= " + detallecompra.getCompra().getId_compra();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stocks set cantidad_existente= cantidad_existente - " + rs.getInt("cantidad_productocompra") + " where id_producto=" + rs.getInt("id_producto") + "";

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

    public static boolean eliminarProductoCompra(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescompras where id_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, compra.getId_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + compra.getId_compra());
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
