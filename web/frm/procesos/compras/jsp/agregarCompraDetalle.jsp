<%@page import="sistemacomecial.controladores.StocksControlador"%>
<%@page import="sistemacomecial.modelos.Stocks"%>
<%@page import="sistemacomecial.controladores.ProductosControlador"%>
<%@page import="sistemacomecial.controladores.DetallesComprasControlador"%>
<%@page import="sistemacomecial.modelos.Productos"%>
<%@page import="sistemacomecial.modelos.Compras"%>
<%@page import="sistemacomecial.modelos.DetallesCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecompra = Integer.parseInt(request.getParameter("id_detallecompra"));
    int cantidad_productocompra = Integer.parseInt(request.getParameter("cantidad_productocompra"));
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int costo_compra = Integer.parseInt(request.getParameter("costo_compra"));
    int iva_producto = Integer.parseInt(request.getParameter("iva_producto"));
    int totald = cantidad_productocompra * costo_compra;

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetallesCompras detallecompra = new DetallesCompras();
    detallecompra.setId_detallecompra(id_detallecompra);
    detallecompra.setCantidad_productocompra(cantidad_productocompra);
    detallecompra.setTotal(totald);

    Compras compra = new Compras();
    compra.setId_compra(id_compra);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setCosto_compra(costo_compra);
    producto.setIva_producto(iva_producto);
    detallecompra.setCompra(compra);
    detallecompra.setProducto(producto);

    if (DetallesComprasControlador.agregar(detallecompra)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setCosto_compra(costo_compra);

    ProductosControlador.modificarc(producto);

    Stocks stock = new Stocks();

    stock.setCantidad_existente(cantidad_productocompra);
  
    stock.setProducto(producto);

    StocksControlador.agregarProdStock(stock);
    producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setCosto_compra(costo_compra);
    ProductosControlador.modificarc(producto);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>