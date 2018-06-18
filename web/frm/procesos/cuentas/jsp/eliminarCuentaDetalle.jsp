
<%@page import="sistemacomecial.modelos.Ventas"%>
<%@page import="sistemacomecial.modelos.DetallesCuentas"%>
<%@page import="sistemacomecial.modelos.Cuentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_detallecuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    Cuentas cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);
    
    DetallesCuentas detallecuenta = new DetallesCuentas();
    detallecuenta.setId_detallecuenta(id_detallecuenta);
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);



    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>