/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import sistemacomecial.utiles.Conexion;

/**
 *
 * @author ALUMNO
 */
public class EJE {
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
                    int total = 0;
                    int totale = 0;
                    int total10 = 0;
                    int totalf = 0;
                    //BigDecimal total = BigDecimal.ZERO;
                    int pago = 1;
                    int totalexentas = 0;
                    int totaliva10 = 0;

                    //BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        pago = rs.getInt("id_tipopago");
                        int cantidad = rs.getInt("importe");
                        //BigDecimal cantidad = rs.getBigDecimal("importe");
                        if (pago == 1) {

                             totalexentas =  cantidad;
                            //totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else {

                            totalexentas = 0;
                            //totaliva5 = 0;
                             totaliva10 =  cantidad;
                            total10 = total10 + totaliva10;
                        }
                        totalf = totale + total10;
                        //total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                // + "<td>" + rs.getString("id_detalleajuste") + "</td>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura_venta") + "</td>"
                                //+ "<td>" + rs.getString("id_tipopago") + "</td>"
                                //+ "<td>" + rs.getString("nombre_tipopago") + "</td>"
                                + "<td>" + df.format(totalexentas) + "</td>"
                                + "<td>" + df.format(totaliva10) + "</td>"
                                //+ "<td>" + rs.getString("total") + "</td>"
                             //   + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                //+ "<td>" + rs.getString("vuelto") + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallecaja") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=3><b>SUB-TOTAL:</b></td><td class='left'><b>" + df.format(totale) + "</b></td><td><b>" + df.format(total10) + "</td></tr>";
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
}