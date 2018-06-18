

<%@page import="sistemacomecial.controladores.DetallesComprasControlador"%>
<%@page import="sistemacomecial.modelos.DetallesCompras"%>
<%@page import="sistemacomecial.controladores.ComprasControlador"%>
<%@page import="sistemacomecial.modelos.Compras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));

    String tipo = "error";
    String mensaje = "Datos no anulados.";

    Compras compra = new Compras();
    compra.setId_compra(id_compra);
    DetallesCompras detallecompra = new DetallesCompras();
    detallecompra.setCompra(compra);
    ComprasControlador.modificarestado(compra);
    if (DetallesComprasControlador.eliminarc(detallecompra)) {
        tipo = "success";
        mensaje = "Factura Anulado.";
    }
   
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>