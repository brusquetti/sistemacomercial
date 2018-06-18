<%@page import="sistemacomecial.modelos.Stocks"%>
<%@page import="sistemacomecial.modelos.Productos"%>
<%@page import="sistemacomecial.modelos.DetallesVentas"%>
<%@page import="sistemacomecial.controladores.StocksControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int id_detalleventa = Integer.parseInt(request.getParameter("id_detalleventa"));
    int cantidad_productoventa = Integer.parseInt(request.getParameter("cantidad_productoventa"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesVentas detalleventa = new DetallesVentas();
    detalleventa.setId_detalleventa(id_detalleventa);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    Stocks stock = new Stocks();

    stock.setCantidad_existente(cantidad_productoventa);

    stock.setProducto(producto);

    if (StocksControlador.restarDetalle(stock)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>