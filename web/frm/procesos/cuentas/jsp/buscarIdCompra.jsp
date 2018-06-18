
<%@page import="sistemacomecial.controladores.ComprasControlador"%>
<%@page import="sistemacomecial.modelos.Compras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  // int numero_factura_compra = 0;
   int id_compra = 0;
    if (request.getParameter("id_compra") != "") {
        id_compra = Integer.parseInt(request.getParameter("id_compra"));
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
   Compras compra=new Compras();
   compra.setId_compra(id_compra);
   // compra.setNumero_factura_compra(numero_factura_compra);
    
    Compras compras = ComprasControlador.buscarTotalfactura(id_compra);
    if (id_compra != 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";    
    } 
   // String contenido_detalle = DetallesComprasControlador.buscarIdCompra(id_compra);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_compra", String.valueOf(compras.getId_compra()));
 //   obj.put("numero_factura_compra", compras.getNumero_factura_compra());
    obj.put("total", compras.getTotal());
  
   // obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>