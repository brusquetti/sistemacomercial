<%@page import="sistemacomecial.modelos.Productos"%>
<%@page import="sistemacomecial.modelos.Ajustes"%>
<%@page import="sistemacomecial.controladores.DetallesAjustesControlador"%>
<%@page import="sistemacomecial.modelos.DetallesAjustes"%>
<%@page import="sistemacomecial.modelos.DetallesAjustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleajuste = Integer.parseInt(request.getParameter("id_detalleajuste"));
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    DetallesAjustes detalleajuste = DetallesAjustesControlador.buscarId(id_detalleajuste);
    if (detalleajuste != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleajuste = new DetallesAjustes();
        detalleajuste.setId_detalleajuste(0);
        
        Ajustes ajuste = new Ajustes();
        ajuste.setId_ajuste(0);
        detalleajuste.setAjuste(ajuste);
        
        Productos producto = new Productos();
        producto.setId_producto(0);
        
   
        detalleajuste.setProducto(producto);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_detalleajuste", String.valueOf(detalleajuste.getId_detalleajuste()));
    obj.put("id_ajuste", String.valueOf(detalleajuste.getAjuste().getId_ajuste()));
    obj.put("id_producto", String.valueOf(detalleajuste.getProducto().getId_producto()));
    obj.put("nombre_producto", detalleajuste.getProducto().getNombre_producto());
    obj.put("cantidad_ajuste", String.valueOf(detalleajuste.getCantidad_ajuste()));    
    out.print(obj);
    out.flush();
%>