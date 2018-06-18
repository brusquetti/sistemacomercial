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
import sistemacomecial.modelos.Cuentas;
import sistemacomecial.modelos.DetallesCuentas;
import sistemacomecial.modelos.ModosPagos;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;


//import javawebts.modelos.Compras;
//import javawebts.modelos.DetallesCuentas;
//import javawebts.modelos.Cuentas;
//import javawebts.utiles.Conexion;
//import javawebts.utiles.Utiles;
/**
 *
 * @author Administrator
 */
public class DetallesCuentasControlador {

    public static DetallesCuentas buscarId(int id) throws SQLException {
        DetallesCuentas detallecuenta = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescuentas dp "
                        + "left join cuentas p on p.id_cuenta=dp.id_cuenta "
                        + "left join compras c on c.id_compra=dp.id_compra "
                        + "left join modospagos t on t.id_modopago=dp.id_modopago "
                        + "where dp.id_detallecuenta=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallecuenta = new DetallesCuentas();
                        detallecuenta.setId_detallecuenta(rs.getInt("id_detallecuenta"));
                        detallecuenta.setImporte(rs.getInt("importe"));

                        Compras compra = new Compras();
                        compra.setId_compra(rs.getInt("id_compra"));
                        detallecuenta.setCompra(compra);

                        Cuentas cuenta = new Cuentas();
                        cuenta.setId_cuenta(rs.getInt("id_cuenta"));
                        detallecuenta.setCuenta(cuenta);

                        ModosPagos modo = new ModosPagos();
                        modo.setId_modopago(rs.getInt("id_modopago"));
                        modo.setNombre_modopago(rs.getString("nombre_modopago"));
                        detallecuenta.setModo(modo);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecuenta;
    }

    public static String buscarIdCuenta(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescuentas dp "
                        + "left join cuentas p on p.id_cuenta=dp.id_cuenta "
                        + "left join compras c on c.id_compra=dp.id_compra "
                        + "left join modospagos a on a.id_modopago=dp.id_modopago "
                        + "where dp.id_cuenta=" + id + " "
                        + "order by id_detallecuenta";
                System.out.println("--> " + sql);
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
                                //  + "<td>" + rs.getString("id_detallecuenta") + "</td>"
                                + "<td>" + rs.getString("id_compra") + "</td>"
                                //+ "<td>" + rs.getString("id_modopago") + "</td>"
                                + "<td>" + rs.getString("nombre_modopago") + "</td>"
                                // + "<td>" + rs.getString("total") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallecuenta") + ")'"
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
                String sql = "select * from detallescuentas dp "
                        + "left join cuentas p on p.id_cuenta=dp.id_cuenta "
                        + "left join compras a on a.id_compra=dp.id_compra "
                        + "where upper(a.nombre_compra) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallecuenta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecuenta") + "</td>"
                                + "<td>" + rs.getString("id_cuenta") + "</td>"
                                + "<td>" + rs.getString("id_compra") + "</td>"
                                //     + "<td>" + rs.getString("numero_factura_compra") + "</td>"
                                + "<td>" + rs.getInt("total") + "</td>"
                                + "<td>" + rs.getInt("iva_compra") + "</td>"
                                + "<td>" + rs.getInt("importe") + "</td>"
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

    public static boolean agregar(DetallesCuentas detallecuenta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallescuentas "
                    + "(id_cuenta,id_compra,id_modopago,importe) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecuenta.getCuenta().getId_cuenta());
                ps.setInt(2, detallecuenta.getCompra().getId_compra());
                ps.setInt(3, detallecuenta.getModo().getId_modopago());
                ps.setInt(4, detallecuenta.getImporte());

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

    public static boolean modificar(DetallesCuentas detallecuenta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallescuentas set "
                    + "id_cuenta=?,"
                    + "id_compra=?,"
                    + "cantidad_compracuenta=? "
                    + "where id_detallecuenta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecuenta.getCuenta().getId_cuenta());
                ps.setInt(2, detallecuenta.getCompra().getId_compra());
                //  ps.setInt(3, detallecuenta.getCantidad_compracuenta());
                ps.setInt(4, detallecuenta.getId_detallecuenta());
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

    public static boolean eliminar(DetallesCuentas detallecuenta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescuentas where id_detallecuenta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecuenta.getId_detallecuenta());
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

    public static boolean eliminarc(DetallesCuentas detallecuenta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescuentas dp "
                        + "left join cuentas p on p.id_cuenta=dp.id_cuenta "
                        + "left join compras a on a.id_compra=dp.id_compra "
                        + " where p.id_cuenta= " + detallecuenta.getCuenta().getId_cuenta();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stocks set cantidad_exi= cantidad_exi - " + rs.getInt("cantidad_compracuenta") + " where id_compra=" + rs.getInt("id_compra") + "";

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

    public static boolean eliminarCompraCuenta(Cuentas cuenta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescuentas where id_cuenta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, cuenta.getId_cuenta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + cuenta.getId_cuenta());
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
