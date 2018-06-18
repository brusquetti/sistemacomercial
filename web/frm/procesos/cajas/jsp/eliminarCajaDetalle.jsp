
<%@page import="sistemacomecial.modelos.Ventas"%>
<%@page import="sistemacomecial.modelos.DetallesCajas"%>
<%@page import="sistemacomecial.modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_detallecaja = Integer.parseInt(request.getParameter("id_caja"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    
    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setId_detallecaja(id_detallecaja);
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);



    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>