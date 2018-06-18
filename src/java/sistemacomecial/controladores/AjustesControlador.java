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
import sistemacomecial.modelos.Ajustes;
import sistemacomecial.utiles.Conexion;
import sistemacomecial.utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class AjustesControlador {

    public static Ajustes buscarId(int id) throws SQLException {
        Ajustes ajustes = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ajustes c "
                        + "left join usuarios u on u.id_usuario=c.id_usuario "
                        + "where id_ajuste=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        ajustes = new Ajustes();
                        ajustes.setId_ajuste(rs.getInt("id_ajuste"));
                        ajustes.setFecha_ajuste(rs.getDate("fecha_ajuste"));
                    } else {

                        ajustes = new Ajustes();

                        ajustes.setId_ajuste(0);

                        java.sql.Date fecha_ajuste = new java.sql.Date(new java.util.Date().getTime());
                        ajustes.setFecha_ajuste(fecha_ajuste);
                        

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ajustes;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ajustes c "
                        + "left join usuarios p on c.id_usuario=p.id_usuario "
                        + "where upper(nombre_usuario) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_ajuste "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_ajuste") + "</td>"
                                + "<td>" + rs.getDate("fecha_ajuste") + "</td>"
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

    public static boolean agregar(Ajustes ajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into ajustes(id_usuario,fecha_ajuste) "
                    + "values('" + ajuste.getUsuario().getId_usuario() + "','"
                    + ajuste.getFecha_ajuste() + "')";

            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_ajuste = keyset.getInt(1);
                    ajuste.setId_ajuste(id_ajuste);
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

    public static boolean modificar(Ajustes ajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ajustes set id_usuario=" + ajuste.getUsuario().getId_usuario() + ","
                    + "fecha_ajuste='" + ajuste.getFecha_ajuste() + "'"
                    + " where id_ajuste=" + ajuste.getId_ajuste();
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

    public static boolean eliminar(Ajustes ajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ajustes where id_ajuste=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ajuste.getId_ajuste());
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
}
