



<%@page import="sistemacomecial.controladores.DetallesComprasControlador"%>
<%@page import="sistemacomecial.modelos.Productos"%>
<%@page import="sistemacomecial.modelos.Compras"%>
<%@page import="sistemacomecial.modelos.DetallesCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecompra = Integer.parseInt(request.getParameter("id_detallecompra"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesCompras detallecompra = DetallesComprasControlador.buscarId(id_detallecompra);
    if (detallecompra != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecompra = new DetallesCompras();
        detallecompra.setId_detallecompra(0);

        Compras compra = new Compras();
        compra.setId_compra(0);
        detallecompra.setCompra(compra);

        Productos producto = new Productos();
        producto.setId_producto(0);
        producto.setNombre_producto("");
        producto.setCosto_compra(0);
        detallecompra.setProducto(producto);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detallecompra", String.valueOf(detallecompra.getId_detallecompra()));
    obj.put("id_compra", String.valueOf(detallecompra.getCompra().getId_compra()));
    obj.put("id_producto", String.valueOf(detallecompra.getProducto().getId_producto()));
    obj.put("nombre_producto", detallecompra.getProducto().getNombre_producto());
    obj.put("costo_compra", detallecompra.getProducto().getCosto_compra());
    obj.put("iva_producto", detallecompra.getProducto().getIva_producto());
    obj.put("cantidad_productocompra", String.valueOf(detallecompra.getCantidad_productocompra()));
    out.print(obj);
    out.flush();
%>