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
import sistemacomecial.modelos.Categorias;
import sistemacomecial.modelos.Marcas;
import sistemacomecial.modelos.Productos;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class ProductosControlador {

    public static boolean agregar(Productos producto) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into productos(nombre_producto"
                    + ",costo_venta,costo_compra"
                    + ",iva_producto,id_marca,id_categoria) "
                    + "values('" + producto.getNombre_producto() + "','"
                    + producto.getCosto_venta() + "','"
                    + producto.getCosto_compra() + "','"
                    + producto.getIva_producto() + "','"
                    + producto.getMarca().getId_marca() + "','"
                    + producto.getCategoria().getId_categoria() + "')";
            System.out.println(sql);
            try {

                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_producto = keyset.getInt(1);
                    producto.setId_producto(id_producto);

                }
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Productos buscarId(Productos producto) throws SQLException {
        if (Conexion.conectar()) {
            String sql = "select * from productos c, categorias cd, marcas m "
                    + "where c.id_categoria=cd.id_categoria and c.id_marca=m.id_marca and id_producto='" + producto.getId_producto() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    producto.setNombre_producto(rs.getString("nombre_producto"));
                    producto.setCosto_venta(rs.getInt("costo_venta"));
                    producto.setCosto_compra(rs.getInt("costo_compra"));
                    producto.setIva_producto(rs.getInt("iva_producto"));

                    Marcas marca = new Marcas();
                    marca.setId_marca(rs.getInt("id_marca"));
                    marca.setNombre_marca(rs.getString("nombre_marca"));

                    producto.setMarca(marca);

                    Categorias categoria = new Categorias();
                    categoria.setId_categoria(rs.getInt("id_categoria"));
                    categoria.setNombre_categoria(rs.getString("nombre_categoria"));

                    producto.setCategoria(categoria);

                } else {
                    producto.setId_producto(0);
                    producto.setNombre_producto("");
                    producto.setCosto_venta(0);
                    producto.setCosto_compra(0);
                    producto.setIva_producto(0);

                    Marcas marca = new Marcas();
                    marca.setId_marca(0);
                    marca.setNombre_marca("");

                    producto.setMarca(marca);

                    Categorias categoria = new Categorias();
                    categoria.setId_categoria(0);
                    categoria.setNombre_categoria("");

                    producto.setCategoria(categoria);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return producto;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from productos p "
                        // + "left join marcas m on p.id_marca=m.id_marca "
                        // + "left join categorias c on c.id_categoria=p.id_categoria "
                        + "left join stocks s on s.id_producto=p.id_producto "
                        + "where upper(p.nombre_producto) like '%" + nombre.toUpperCase() + "%'"
                        + "order by p.id_producto offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                // + "<td>" + rs.getString("nombre_marca") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getInt("costo_venta") + "</td>"
                                + "<td>" + rs.getInt("costo_compra") + "</td>"
                             //   + "<td>" + rs.getInt("iva_producto") + "</td>"
                                + "<td>" + rs.getInt("cantidad_existente") + "</td>"
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

    public static boolean modificar(Productos producto) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update productos set nombre_producto='" + producto.getNombre_producto()
                    + "', costo_venta='" + producto.getCosto_venta() + ""
                    + "', costo_compra='" + producto.getCosto_compra() + ""
                    + "', iva_producto='" + producto.getIva_producto() + ""
                    + "',id_marca='" + producto.getMarca().getId_marca() + ""
                    + "',id_categoria='" + producto.getCategoria().getId_categoria() + ""
                    + "' where id_producto=" + producto.getId_producto();
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

    public static boolean modificarc(Productos producto) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update productos set costo_compra='" + producto.getCosto_compra()
                    //+ "', costo_venta='" + producto.getCosto_venta()
                    + "' where id_producto=" + producto.getId_producto();
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

    public static boolean modificara(Productos producto) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update productos set costo_venta='" + producto.getCosto_venta()
                    //+ "', costo_venta='" + producto.getCosto_venta()
                    + "' where id_producto=" + producto.getId_producto();
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

    public static boolean eliminar(Productos producto) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from productos where id_producto=" + producto.getId_producto();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
    
    public static Productos buscarExi(int id) throws SQLException {
     System.out.println("hola" + id);
        Productos productos = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "select cantidad_existente,id_producto from stocks "
                            + " where id_producto=? ";
                   try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                    psi.setInt(1, id);
                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        productos = new Productos();
                        productos.setId_producto(rsi.getInt("id_producto"));
                        productos.setCantidad_existente(rsi.getInt("cantidad_existente"));
                    }
                   System.out.println("cantidad" + rsi.getInt("cantidad_existente"));
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return productos;
    }

}
