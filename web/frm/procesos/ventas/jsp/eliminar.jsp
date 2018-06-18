

<%@page import="sistemacomecial.controladores.DetallesVentasControlador"%>
<%@page import="sistemacomecial.modelos.DetallesVentas"%>
<%@page import="sistemacomecial.controladores.VentasControlador"%>
<%@page import="sistemacomecial.modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    DetallesVentas detalleventa = new DetallesVentas();
    detalleventa.setVenta(venta);
    VentasControlador.modificarestado(venta);
    if (DetallesVentasControlador.eliminarc(detalleventa)) {
        tipo = "success";
        mensaje = "Factura Anulado.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>