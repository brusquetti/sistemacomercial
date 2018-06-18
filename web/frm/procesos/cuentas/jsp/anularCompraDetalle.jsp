
<%@page import="sistemacomecial.controladores.StocksControlador"%>
<%@page import="sistemacomecial.modelos.Stocks"%>
<%@page import="sistemacomecial.modelos.Productos"%>
<%@page import="sistemacomecial.modelos.DetallesCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int id_detallecompra = Integer.parseInt(request.getParameter("id_detallecompra"));
    int cantidad_productocompra = Integer.parseInt(request.getParameter("cantidad_productocompra"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesCompras detallecompra = new DetallesCompras();
    detallecompra.setId_detallecompra(id_detallecompra);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    Stocks stock = new Stocks();

    //stock.setCantidad_exi(cantidad_productocompra);

    stock.setProducto(producto);
if (StocksControlador.restarDetalle(stock)){
    tipo="success";
    mensaje="datos cerrado caja";
}

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>