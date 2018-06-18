
<%@page import="sistemacomecial.modelos.ModosPagos"%>
<%@page import="sistemacomecial.modelos.Ventas"%>
<%@page import="sistemacomecial.modelos.Cuentas"%>
<%@page import="sistemacomecial.controladores.DetallesCuentasControlador"%>
<%@page import="sistemacomecial.modelos.DetallesCuentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecuenta = Integer.parseInt(request.getParameter("id_detallecuenta"));
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesCuentas detallecuenta = DetallesCuentasControlador.buscarId(id_detallecuenta);
    if (detallecuenta != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecuenta = new DetallesCuentas();
        detallecuenta.setId_detallecuenta(0);

        Cuentas cuenta = new Cuentas();
        cuenta.setId_cuenta(0);
        detallecuenta.setCuenta(cuenta);

        Ventas venta = new Ventas();
        venta.setId_venta(0);
        detallecuenta.setVenta(venta);

        ModosPagos pago = new ModosPagos();
        pago.setId_modopago(0);
        detallecuenta.setVenta(venta);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detallecuenta", String.valueOf(detallecuenta.getId_detallecuenta()));
    obj.put("id_cuenta", String.valueOf(detallecuenta.getCuenta().getId_cuenta()));
    obj.put("id_venta", String.valueOf(detallecuenta.getVenta().getId_venta()));
    obj.put("numero_factura_venta", String.valueOf(detallecuenta.getVenta().getNumero_factura_venta()));
    obj.put("id_modopago", String.valueOf(detallecuenta.getModo().getId_modopago()));
    obj.put("importe", String.valueOf(detallecuenta.getImporte()));
    out.print(obj);
    out.flush();
%>