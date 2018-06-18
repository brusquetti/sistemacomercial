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
import sistemacomecial.modelos.Ajustes;
import sistemacomecial.modelos.DetallesAjustes;
import sistemacomecial.modelos.Productos;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

public class DetallesAjustesControlador {

    public static DetallesAjustes buscarId(int id) throws SQLException {
        DetallesAjustes detalleajuste = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesajustes dp "
                        + "left join ajustes p on p.id_ajuste=dp.id_ajuste "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_detalleajuste=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleajuste = new DetallesAjustes();
                        detalleajuste.setId_detalleajuste(rs.getInt("id_detalleajuste"));
                        detalleajuste.setCantidad_ajuste(rs.getInt("cantidad_ajuste"));
                        detalleajuste.setMotivo(rs.getString("motivo"));

                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));

                        producto.setNombre_producto(rs.getString("nombre_producto"));

                        //producto.setCosto_compra(rs.getInt("costo_compra"));
                        //producto.setIva_producto(rs.getInt("iva_producto"));
                        detalleajuste.setProducto(producto);

                        Ajustes ajuste = new Ajustes();
                        ajuste.setId_ajuste(rs.getInt("id_ajuste"));
                        detalleajuste.setAjuste(ajuste);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleajuste;
    }

    public static String buscarIdAjuste(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesajustes  dp "
                        + "left join ajustes p on p.id_ajuste=dp.id_ajuste "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_ajuste=" + id + " "
                        + "order by id_detalleajuste";
                //System.out.println("hola "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_ajuste");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                // + "<td>" + rs.getString("id_detalleajuste") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getString("motivo") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detalleajuste") + ")'"
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
                String sql = "select * from detallesajustes dp "
                        + "left join ajustes p on p.id_ajuste=dp.id_ajuste "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "left join stocks s on s.id_producto=p.id_producto " 
                        + "where upper(a.nombre_producto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detalleajuste "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("id_detalleajuste") + "</td>"
                                + "<td>" + rs.getInt("id_ajuste") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                               // + "<td>" + rs.getInt("costo_compra") + "</td>"
                               // + "<td>" + rs.getInt("iva_producto") + "</td>"
                                //+ "<td>" + rs.getInt("cantidad_ajuste") + "</td>"
                                + "<td>" + rs.getInt("cantidad_existente") + "</td>"
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

    public static boolean agregar(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallesajustes "
                    + "(id_ajuste,id_producto,cantidad_ajuste,motivo) "
                    + "values (?,?,?,?)";

            System.out.println(sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getAjuste().getId_ajuste());
                ps.setInt(2, detalleajuste.getProducto().getId_producto());
                ps.setInt(3, detalleajuste.getCantidad_ajuste());
                ps.setString (4, detalleajuste.getMotivo());
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

    public static boolean modificar(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallesajustes set "
                    + "id_ajuste=?,"
                    + "id_producto=?,"
                    + "cantidad_ajuste=? "
                    + "where id_detalleajuste=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getAjuste().getId_ajuste());
                ps.setInt(2, detalleajuste.getProducto().getId_producto());
                ps.setInt(3, detalleajuste.getCantidad_ajuste());

                ps.setInt(5, detalleajuste.getId_detalleajuste());
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

    public static boolean eliminar(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesajustes where id_detalleajuste=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getId_detalleajuste());
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

    public static boolean eliminarc(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesajustes dp "
                        + "left join ajustes p on p.id_ajuste=dp.id_ajuste "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + " where p.id_ajuste= " + detalleajuste.getAjuste().getId_ajuste();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stocks set cantidad_existente= cantidad_existente - " + rs.getInt("cantidad_ajuste") + " where id_producto=" + rs.getInt("id_producto") + "";

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

    public static boolean eliminarProductoAjuste(Ajustes ajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesajustes where id_ajuste=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ajuste.getId_ajuste());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + ajuste.getId_ajuste());
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
