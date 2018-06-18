
<%@page import="sistemacomecial.modelos.Compras"%>
<%@page import="sistemacomecial.controladores.StocksControlador"%>
<%@page import="sistemacomecial.modelos.Stocks"%>
<%@page import="sistemacomecial.modelos.Productos"%>
<%@page import="sistemacomecial.controladores.DetallesComprasControlador"%>
<%@page import="sistemacomecial.modelos.DetallesCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    int id_detallecompra = Integer.parseInt(request.getParameter("id_detallecompra"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int cantidad_productocompra = Integer.parseInt(request.getParameter("cantidad_productocompra"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Compras compra=new Compras();
    compra.setId_compra(id_compra);
    
    DetallesCompras detallecompra = new DetallesCompras();
    detallecompra.setId_detallecompra(id_detallecompra);
    detallecompra.setCompra(compra);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    Stocks stock = new Stocks();

    stock.setCantidad_existente(cantidad_productocompra);

    stock.setProducto(producto);

    StocksControlador.RestarProdStock(stock);
    //if (DetallesComprasControlador.eliminar(detallecompra)) {
    if (DetallesComprasControlador.eliminar(detallecompra)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    //detallecompra = new DetallesCompras();
    //detallecompra.setId_detallecompra(id_detallecompra);
    //DetallesComprasControlador.eliminar(detallecompra);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>