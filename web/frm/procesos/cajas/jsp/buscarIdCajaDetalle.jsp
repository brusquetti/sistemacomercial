
<%@page import="sistemacomecial.controladores.DetallesCajasControlador"%>
<%@page import="sistemacomecial.modelos.TiposPagos"%>
<%@page import="sistemacomecial.modelos.Ventas"%>
<%@page import="sistemacomecial.modelos.Cajas"%>
<%@page import="sistemacomecial.modelos.DetallesCajas"%>
<%@page import="sistemacomecial.modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesCajas detallecaja = DetallesCajasControlador.buscarId(id_detallecaja);
    if (detallecaja != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecaja = new DetallesCajas();
        detallecaja.setId_detallecaja(0);

        Cajas caja = new Cajas();
        caja.setId_caja(0);
        detallecaja.setCaja(caja);

        Ventas venta = new Ventas();
        venta.setId_venta(0);
        detallecaja.setVenta(venta);

        TiposPagos pago = new TiposPagos();
        pago.setId_tipopago(0);
        detallecaja.setVenta(venta);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detallecaja", String.valueOf(detallecaja.getId_detallecaja()));
    obj.put("id_caja", String.valueOf(detallecaja.getCaja().getId_caja()));
    obj.put("id_venta", String.valueOf(detallecaja.getVenta().getId_venta()));
    obj.put("numero_factura_venta", String.valueOf(detallecaja.getVenta().getNumero_factura_venta()));
       // obj.put("total", String.valueOf(detallecaja.getVenta().getTotal()));

    obj.put("id_tipopago", String.valueOf(detallecaja.getPago().getId_tipopago()));

    obj.put("importe", String.valueOf(detallecaja.getImporte()));
    out.print(obj);
    out.flush();
%>