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
import sistemacomecial.modelos.Ventas;
import sistemacomecial.modelos.Clientes;
import sistemacomecial.modelos.Establecimientos;
import sistemacomecial.modelos.Puestos;
import sistemacomecial.modelos.Timbrados;
import sistemacomecial.modelos.TiposPagos;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class VentasControlador {

    public static Ventas buscarId(int id) throws SQLException {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas c "
                        + "left join clientes a on c.id_cliente = a.id_cliente "
                       // + "left join tipospagos t on c.id_tipopago = t.id_tipopago "
                        + "left join establecimientos e on c.id_establecimiento = e.id_establecimiento "
                        + "left join puestos p on c.id_puesto = p.id_puesto "
                        + "left join timbrados ti on c.id_timbrado = ti.id_timbrado "
                        + "left join usuarios u on u.id_usuario = c.id_usuario "
                        + "where id_venta=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        ventas = new Ventas();
                        ventas.setId_venta(rs.getInt("id_venta"));
                        ventas.setFecha_venta(rs.getDate("fecha_venta"));
                        ventas.setEstado_venta(rs.getString("estado_venta"));
                        ventas.setNumero_factura_venta(rs.getInt("numero_factura_venta"));

                        Clientes cliente = new Clientes();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                        ventas.setCliente(cliente);

                        Establecimientos establecimiento = new Establecimientos();
                        establecimiento.setId_establecimiento(rs.getInt("id_establecimiento"));
                        ventas.setEstablecimiento(establecimiento);

                        Puestos puesto = new Puestos();
                        puesto.setId_puesto(rs.getInt("id_puesto"));
                        ventas.setPuesto(puesto);

                        Timbrados timbrado = new Timbrados();
                        timbrado.setId_timbrado(rs.getInt("id_timbrado"));
                        timbrado.setNumero_timbrado(rs.getString("numero_timbrado"));
                        ventas.setTimbrado(timbrado);
                    } else {

                        try {

                            String sqli = "SELECT COUNT(*) AS Ultimo, COUNT(numero_factura_venta) FROM ventas ";

                            System.out.println("sss" + sqli);
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                                int num = 0;
                                ResultSet rsi = psi.executeQuery();
                                if (rsi.next()) {
                                    ventas = new Ventas();
                                    num = rsi.getInt("Ultimo");
                                    ventas.setNumero_factura_venta(num + 1);

                                    ventas.setId_venta(0);
                                    ventas.setEstado_venta("ACTIVO");
                                    java.sql.Date fecha_venta = new java.sql.Date(new java.util.Date().getTime());
                                    ventas.setFecha_venta(fecha_venta);

                                    Clientes cliente = new Clientes();
                                    cliente.setId_cliente(0);
                                    cliente.setNombre_cliente("");
                                    cliente.setRuc_cliente("");
                                    ventas.setCliente(cliente);

                                   

                                } else {
                                    num = 1;
                                }

                                psi.close();
                            }
                        } catch (SQLException ex) {
                            System.out.println("--> " + ex.getLocalizedMessage());

                        }
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas p "
                        + "left join clientes c on c.id_cliente=p.id_cliente "
                     //   + "left join tipospagos t on t.id_tipopago=p.id_tipopago "
                        + "where upper(nombre_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getInt("numero_factura_venta") + "</td>"
                                + "<td>" + rs.getString("fecha_venta") + "</td>"
                                + "<td>" + rs.getString("estado_venta") + "</td>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td>" + rs.getString("ruc_cliente") + "</td>"
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

    public static boolean agregar(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = venta.getCliente().getId_cliente();

            String sql = "insert into ventas(fecha_venta"
                    + ",id_cliente,estado_venta,id_establecimiento,id_puesto"
                    + ",numero_factura_venta,id_timbrado,id_usuario)"
                    + "values('"
                    + venta.getFecha_venta() + "','"
                    + v1 + "','"
                    //+ venta.getEstado_venta() + "','"
                    //+ venta.getPago().getId_tipopago() + "','"
                    + venta.getEstado_venta() + "','"
                    + venta.getEstablecimiento().getId_establecimiento() + "','"
                    + venta.getPuesto().getId_puesto() + "','"
                    + venta.getNumero_factura_venta() + "','"
                    + venta.getTimbrado().getId_timbrado() + "','"
                    + venta.getUsuario().getId_usuario() + "')";

            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_venta = keyset.getInt(1);
                    venta.setId_venta(id_venta);

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

    public static boolean modificar(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set id_cliente=" + venta.getCliente().getId_cliente() + ","
                    + "fecha_venta='" + venta.getFecha_venta() + "'"
                 //   + "id_tipopago=" + venta.getPago().getId_tipopago() + ""
                  //  + "id_tipopago=" + venta.getPago().getId_tipopago() + ""
                    + "id_usuario=" + venta.getUsuario().getId_usuario() + ""
                    + " where id_venta=" + venta.getId_venta();
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

    public static boolean eliminar(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ventas where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, venta.getId_venta());
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

    public static boolean modificarestado(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set estado_venta='ANULADO'  "
                    + " where id_venta=" + venta.getId_venta();
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
     public static boolean modificarestadocobro(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set estado_venta='COBRADO'  "
                    + " where id_venta=" + venta.getId_venta();
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
    

    public static Ventas buscarNumFactura(int id) throws SQLException {
        Ventas ventas = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "SELECT COUNT(*) AS Ultimo, COUNT(numero_factura_venta) FROM ventas ";

                System.out.println("sss" + sqli);
                try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        ventas = new Ventas();

                        ventas.setNumero_factura_venta(rsi.getInt("Ultimo"));

                    }
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }
    
     public static Ventas buscarTotalfactura(int id) throws SQLException {
        Ventas ventas = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "select v.numero_factura_venta, v.id_venta,SUM(total) as total from ventas v "
                        + " left join detallesventas dv on dv.id_venta=v.id_venta "
                        + " where v.id_venta=? "
                        + " group by v.id_venta ";

                System.out.println("sss" + sqli);
                try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                    psi.setInt(1, id);
                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        ventas = new Ventas();
                        ventas.setId_venta(rsi.getInt("id_venta"));
                        ventas.setNumero_factura_venta(rsi.getInt("numero_factura_venta"));
                        ventas.setTotal(rsi.getInt("total"));

                    }
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }
}
