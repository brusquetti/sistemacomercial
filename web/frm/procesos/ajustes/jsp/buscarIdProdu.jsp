<%@page import="sistemacomecial.controladores.ProductosControlador"%>
<%@page import="sistemacomecial.modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_producto = 0;
    if (request.getParameter("id_producto") != "") {
        id_producto = Integer.parseInt(request.getParameter("id_producto"));
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    producto = ProductosControlador.buscarId(producto);
    if (producto.getId_producto() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_producto", producto.getId_producto());
    obj.put("nombre_producto", producto.getNombre_producto());
    //obj.put("costo_compra", producto.getCosto_compra());
    //obj.put("iva_producto", producto.getIva_producto());
    out.print(obj);
    out.flush();
%>