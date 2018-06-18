

<%@page import="sistemacomecial.controladores.StocksControlador"%>
<%@page import="sistemacomecial.modelos.Stocks"%>
<%@page import="sistemacomecial.modelos.Marcas"%>
<%@page import="sistemacomecial.modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.ProductosControlador"%>
<%@page import="sistemacomecial.modelos.Productos"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    String nombre_producto = request.getParameter("nombre_producto");
    int costo_venta = Integer.parseInt(request.getParameter("costo_venta"));
    int costo_compra = Integer.parseInt(request.getParameter("costo_compra"));
    int iva_producto = Integer.parseInt(request.getParameter("iva_producto"));
    int cantidad_min = Integer.parseInt(request.getParameter("cantidad_min"));
    int cantidad_max = Integer.parseInt(request.getParameter("cantidad_max"));
    int cantidad_existente = 0;
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);

    Categorias categoria = new Categorias();
    categoria.setId_categoria(id_categoria);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setCategoria(categoria);
    producto.setMarca(marca);

    producto.setNombre_producto(nombre_producto);
    producto.setCosto_venta(costo_venta);
    producto.setCosto_compra(costo_compra);
    producto.setIva_producto(iva_producto);

    producto.setCategoria(categoria);

    if (ProductosControlador.agregar(producto)) {
        tipo = "success";
        mensaje = "Datos agregados";
    }
    
    
    Stocks stock = new Stocks();

    stock.setCantidad_existente(cantidad_existente);
    stock.setCantidad_max(cantidad_max);
    stock.setCantidad_min(cantidad_min);
    stock.setProducto(producto);

    StocksControlador.agregar(stock);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_producto", String.valueOf(producto.getId_producto()));
    out.print(obj);
    out.flush();
%>