<%@page import="sistemacomecial.controladores.DetallesAjustesControlador"%>
<%@page import="sistemacomecial.controladores.StocksControlador"%>
<%@page import="sistemacomecial.modelos.Stocks"%>
<%@page import="sistemacomecial.modelos.Productos"%>
<%@page import="sistemacomecial.modelos.DetallesAjustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int id_detalleajuste = Integer.parseInt(request.getParameter("id_detalleajuste"));
int cantidad_ajuste = Integer.parseInt(request.getParameter("cantidad_ajuste"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesAjustes detalleajuste = new DetallesAjustes();
    detalleajuste.setId_detalleajuste(id_detalleajuste);
    
    
    Productos producto=new Productos();
    producto.setId_producto(id_producto);
    
     Stocks stock = new Stocks();

    stock.setCantidad_existente(cantidad_ajuste);
  
    stock.setProducto(producto);

    StocksControlador.RestarProdStock(stock);
    
    

    if (DetallesAjustesControlador.eliminar(detalleajuste)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>