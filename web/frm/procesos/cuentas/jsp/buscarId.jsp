
<%@page import="sistemacomecial.controladores.CuentasControlador"%>
<%@page import="sistemacomecial.controladores.DetallesCuentasControlador"%>
<%@page import="sistemacomecial.modelos.Cuentas"%>
<%@page import="sistemacomecial.modelos.Cuentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Cuentas cuentas = CuentasControlador.buscarIdestado( id_usuario);
    if (cuentas != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        cuentas = new Cuentas();
        cuentas.setId_cuenta(0);
        java.sql.Date fecha_apertura = new java.sql.Date(new java.util.Date().getTime());
        cuentas.setFecha_apertura(fecha_apertura);
       
        cuentas.setEstado_cuenta("CERRADO");
        mensaje = "Debe abrir la cuenta.";
    }

    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));

    String contenido_detalle = DetallesCuentasControlador.buscarIdCuenta(id_cuenta);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_cuenta", String.valueOf(cuentas.getId_cuenta()));
    obj.put("fecha_apertura", String.valueOf(cuentas.getFecha_apertura()));
    obj.put("estado_cuenta", cuentas.getEstado_cuenta());
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>