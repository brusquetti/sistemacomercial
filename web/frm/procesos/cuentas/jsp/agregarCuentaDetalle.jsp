
<%@page import="sistemacomecial.controladores.DetallesCuentasControlador"%>
<%@page import="sistemacomecial.controladores.ComprasControlador"%>
<%@page import="sistemacomecial.modelos.ModosPagos"%>
<%@page import="sistemacomecial.modelos.Cuentas"%>
<%@page import="sistemacomecial.modelos.Compras"%>
<%@page import="sistemacomecial.modelos.DetallesCuentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecuenta = Integer.parseInt(request.getParameter("id_detallecuenta"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    int total = Integer.parseInt(request.getParameter("total"));
    int id_modopago = Integer.parseInt(request.getParameter("id_modopago"));
    String nombre_modopago = request.getParameter("nombre_modopago");
    int totald = importe - total;
    String tipo = "error";
    
    String mensaje = "Datos no agregados.";
    
    DetallesCuentas detallecuenta = new DetallesCuentas();
    detallecuenta.setId_detallecuenta(id_detallecuenta);
    detallecuenta.setVuelto(totald);
    detallecuenta.setImporte(importe);
    //  detallecuenta.setCantidad_cuentacompra(cantidad_cuentacompra);

    Compras compra = new Compras();
    compra.setId_compra(id_compra);
  //  compra.setNumero_factura_compra(numero_factura_compra);
    Cuentas cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);
    
    ModosPagos modo = new ModosPagos();
    modo.setId_modopago(id_modopago);
    modo.setNombre_modopago(nombre_modopago);
    detallecuenta.setCompra(compra);
    detallecuenta.setCuenta(cuenta);
    detallecuenta.setModo(modo);
        ComprasControlador.modificarestadoPago(compra);

    if (DetallesCuentasControlador.agregar(detallecuenta)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    compra = new Compras();
    compra.setId_compra(id_compra);
    modo = new ModosPagos();
    modo.setId_modopago(id_modopago);
    modo.setNombre_modopago(nombre_modopago);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>