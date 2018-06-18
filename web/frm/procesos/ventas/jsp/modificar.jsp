
<%@page import="sistemacomecial.modelos.TiposPagos"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="sistemacomecial.modelos.Clientes"%>
<%@page import="sistemacomecial.controladores.VentasControlador"%>
<%@page import="sistemacomecial.modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    String sfecha_venta = request.getParameter("fecha_venta");
    java.sql.Date fecha_venta = Utiles.stringToSqlDate(sfecha_venta);
    String estado_venta = request.getParameter("estado_venta");

    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    
 

    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    venta.setFecha_venta(fecha_venta);
    venta.setEstado_venta(estado_venta);
    venta.setCliente(cliente);
    if (VentasControlador.modificar(venta)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>