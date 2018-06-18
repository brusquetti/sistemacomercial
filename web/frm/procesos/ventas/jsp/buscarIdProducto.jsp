


<%@page import="sistemacomecial.modelos.Stocks"%>
<%@page import="sistemacomecial.controladores.StocksControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.ProductosControlador"%>
<%@page import="sistemacomecial.modelos.Productos"%>
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
    producto.setNombre_producto("");
    producto.setIva_producto(0);
    producto.setCosto_venta(0);
    producto.setCantidad_existente(0);

    //Stocks stock = new Stocks();
    //stock.setProducto(producto);
    producto = ProductosControlador.buscarId(producto);
    if (producto.getId_producto() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    //stock = StocksControlador.buscarId(stock);
    Productos productos = ProductosControlador.buscarExi(producto.getId_producto());
    if (productos != null) {
        tipo = "success";
        mensaje = "Datos encontrados cantidad";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_producto", producto.getId_producto());
    obj.put("nombre_producto", producto.getNombre_producto());
    obj.put("costo_venta", producto.getCosto_venta());
    obj.put("iva_producto", producto.getIva_producto());

    obj.put("stock_producto", productos.getCantidad_existente());

    out.print(obj);
    out.flush();
%>