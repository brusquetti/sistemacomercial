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
import sistemacomecial.modelos.Compras;
import sistemacomecial.modelos.ModosPagos;
import sistemacomecial.modelos.Proveedores;
import sistemacomecial.modelos.TiposPagos;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class ComprasControlador {

    public static Compras buscarId(int id) throws SQLException {
        Compras compras = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from compras c "
                        + "left join proveedores a on c.id_proveedor=a.id_proveedor "
                        + "left join usuarios u on c.id_usuario=u.id_usuario "
                       // + "left join tipospagos t on c.id_tipopago=t.id_tipopago "
                        + "left join modospagos m on c.id_modopago=m.id_modopago "
                        + " where id_compra=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        compras = new Compras();
                        compras.setId_compra(rs.getInt("id_compra"));
                        compras.setNumero_factura_compra(rs.getString("numero_factura_compra"));
                        compras.setFecha_compra(rs.getDate("fecha_compra"));
                        //compras.setTimbrado_compra(rs.getString("timbrado_compra"));
                        compras.setEstado_compra(rs.getString("estado_compra"));
                        compras.setFecha_emitida(rs.getDate("fecha_emitida"));

                        Proveedores proveedor = new Proveedores();
                        proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                        proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                        proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));
                        compras.setProveedor(proveedor);

//                        TiposPagos pago = new TiposPagos();
//                        pago.setId_tipopago(rs.getInt("id_tipopago"));
//                        pago.setNombre_tipopago(rs.getString("nombre_tipopago"));
//                        compras.setPago(pago);
                        
                        ModosPagos modo = new ModosPagos();
                        modo.setId_modopago(rs.getInt("id_modopago"));
                        modo.setNombre_modopago(rs.getString("nombre_modopago"));
                        compras.setModo(modo);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return compras;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from compras c "
                        + "left join proveedores p on c.id_proveedor=p.id_proveedor "
                        + "where upper(nombre_proveedor) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_compra "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_compra") + "</td>"
                                + "<td>" + rs.getString("numero_factura_compra") + "</td>"
                                + "<td>" + rs.getString("fecha_compra") + "</td>"
                                //+ "<td>" + rs.getString("timbrado_compra") + "</td>"
                                + "<td>" + rs.getString("estado_compra") + "</td>"
                                + "<td>" + rs.getString("fecha_emitida") + "</td>"
                                + "<td>" + rs.getString("id_proveedor") + "</td>"
                                + "<td>" + rs.getString("nombre_proveedor") + "</td>"
                                + "<td>" + rs.getString("ruc_proveedor") + "</td>"
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

    public static boolean agregar(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = compra.getProveedor().getId_proveedor();
            String sql = "insert into compras(numero_factura_compra"
                    + ",fecha_compra,id_proveedor,estado_compra,fecha_emitida,id_usuario,id_modopago)"
                    + "values('"
                    + compra.getNumero_factura_compra() + "','"
                    + compra.getFecha_compra() + "','"
                    //+ compra.getTimbrado_compra() + "','"
                    + v1 + "','"
                    + compra.getEstado_compra() + "','"
                    + compra.getFecha_emitida() + "','"
                    + compra.getUsuario().getId_usuario() + "','"
                    + compra.getModo().getId_modopago() + "')";

            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_compra = keyset.getInt(1);
                    compra.setId_compra(id_compra);

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

    public static boolean modificar(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update compras set id_proveedor= " + compra.getProveedor().getId_proveedor() + " ,"
                    + " numero_factura_compra= ' " + compra.getNumero_factura_compra() + " ' , "
                    + " fecha_compra= ' " + compra.getFecha_compra() + " ' , "
                    + " fecha_compra= ' " + compra.getFecha_compra() + " ' , "
                    + " estado_compra= ' " + compra.getEstado_compra() + " ' , "
                    //+ "timbrado_compra= '" + compra.getTimbrado_compra() + "' "
                    + " fecha_emitida= ' " + compra.getFecha_emitida() + " ' , "
                    + "id_usuario=" + compra.getUsuario().getId_usuario() + ""
                    + " where id_compra=" + compra.getId_compra();
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, compra.getProveedor().getId_proveedor());
                ps.setInt(2, compra.getId_compra());
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

    public static boolean eliminar(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from compras where id_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, compra.getId_compra());
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

    public static boolean modificarestado(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update compras set estado_compra='ANULADO'  "
                    + " where id_compra=" + compra.getId_compra();
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
     public static boolean modificarestadocobro(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update compras set estado_compra='PAGADO'  "
                    + " where id_compra=" + compra.getId_compra();
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
     
     public static boolean modificarestadoPago(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update compras set estado_compra='PAGADO'  "
                    + " where id_compra=" + compra.getId_compra();
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
      public static Compras buscarTotalfactura(int id) throws SQLException {
        Compras compras = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "select v.id_compra,SUM(total) as total from compras v "
                        + " left join detallescompras dv on dv.id_compra=v.id_compra "
                        + " where v.id_compra=? "
                        + " group by v.id_compra";

                System.out.println("sss" + id);

                try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                    psi.setInt(1, id);
                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        compras = new Compras();
                        compras.setId_compra(rsi.getInt("id_compra"));
                       
                        compras.setTotal(rsi.getInt("total"));

                    }
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return compras;
    }
     
}
