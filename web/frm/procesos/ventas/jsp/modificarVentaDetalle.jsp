<%@page import="sistemacomecial.controladores.StocksControlador"%>
<%@page import="sistemacomecial.modelos.Stocks"%>
<%@page import="sistemacomecial.controladores.DetallesVentasControlador"%>
<%@page import="sistemacomecial.modelos.DetallesVentas"%>
<%@page import="sistemacomecial.modelos.Productos"%>
<%@page import="sistemacomecial.modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleventa = Integer.parseInt(request.getParameter("id_detalleventa"));
    int cantidad_productoventa = Integer.parseInt(request.getParameter("cantidad_productoventa"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";

    DetallesVentas detalleventa = new DetallesVentas();
    detalleventa.setId_detalleventa(id_detalleventa);
    detalleventa.setCantidad_productoventa(cantidad_productoventa);

    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    
    

    detalleventa.setVenta(venta);
    detalleventa.setProducto(producto);

    if (DetallesVentasControlador.modificar(detalleventa)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
     Stocks stock = new Stocks();

    stock.setCantidad_existente(cantidad_productoventa);

    stock.setProducto(producto);

    StocksControlador.agregarProdStock(stock);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>