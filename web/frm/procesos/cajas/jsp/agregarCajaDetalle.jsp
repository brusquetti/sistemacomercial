<%@page import="sistemacomecial.controladores.VentasControlador"%>
<%@page import="sistemacomecial.controladores.DetallesCajasControlador"%>
<%@page import="sistemacomecial.modelos.TiposPagos"%>
<%@page import="sistemacomecial.modelos.Cajas"%>
<%@page import="sistemacomecial.modelos.Ventas"%>
<%@page import="sistemacomecial.modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    //  int totaldetallecaja = Integer.parseInt(request.getParameter("totaldetallecaja"));

    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int total = Integer.parseInt(request.getParameter("total"));

    int numero_factura_venta = Integer.parseInt(request.getParameter("numero_factura_venta"));
    int id_tipopago = Integer.parseInt(request.getParameter("id_tipopago"));
    String nombre_tipopago = request.getParameter("nombre_tipopago");

    int totald = importe - total;

    String tipo = "error";

    String mensaje = "Datos no agregados.";

    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setId_detallecaja(id_detallecaja);
    detallecaja.setImporte(importe);
    detallecaja.setVuelto(totald);

    //  detallecaja.setCantidad_cajaventa(cantidad_cajaventa);
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    venta.setNumero_factura_venta(numero_factura_venta);
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);

    TiposPagos pago = new TiposPagos();
    pago.setId_tipopago(id_tipopago);
    pago.setNombre_tipopago(nombre_tipopago);
    detallecaja.setVenta(venta);
    detallecaja.setCaja(caja);
    detallecaja.setPago(pago);
    //ComprasControlador.modificarestado(compra);
    VentasControlador.modificarestadocobro(venta);
    
    if (DetallesCajasControlador.agregar(detallecaja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    venta = new Ventas();
    venta.setId_venta(id_venta);
    venta.setNumero_factura_venta(0);
    pago = new TiposPagos();
    pago.setId_tipopago(id_tipopago);
    pago.setNombre_tipopago(nombre_tipopago);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>