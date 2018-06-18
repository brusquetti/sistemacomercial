<%@page import="sistemacomecial.modelos.TiposPagos"%>
<%@page import="sistemacomecial.controladores.DetallesCajasControlador"%>
<%@page import="sistemacomecial.modelos.Proveedores"%>
<%@page import="sistemacomecial.modelos.Clientes"%>
<%@page import="sistemacomecial.controladores.CajasControlador"%>
<%@page import="sistemacomecial.modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_caja = 0;
    if (request.getParameter("id_caja") != "") {
        id_caja = Integer.parseInt(request.getParameter("id_caja"));

    }

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Cajas cajas = CajasControlador.buscarId(id_caja);
    if (id_caja != 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        cajas = new Cajas();
        cajas.setId_caja(0);
        java.sql.Date fecha_apertura = new java.sql.Date(new java.util.Date().getTime());
        cajas.setFecha_apertura(fecha_apertura);
        cajas.setMonto_inicial(0);
        cajas.setEstado_caja("ABIERTO");

    }

    String contenido_detalle = DetallesCajasControlador.buscarIdCaja(cajas.getId_caja());

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_caja", String.valueOf(cajas.getId_caja()));
    obj.put("fecha_apertura", String.valueOf(cajas.getFecha_apertura()));
    obj.put("monto_inicial", String.valueOf(cajas.getMonto_inicial()));
    obj.put("estado_caja", cajas.getEstado_caja());
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>