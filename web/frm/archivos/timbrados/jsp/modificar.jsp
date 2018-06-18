
<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.TimbradosControlador"%>
<%@page import="sistemacomecial.modelos.Establecimientos"%>
<%@page import="sistemacomecial.modelos.Puestos"%>
<%@page import="sistemacomecial.modelos.Timbrados"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    String numero_timbrado = request.getParameter("numero_timbrado");
    String sFacha_inicio = request.getParameter("fecha_inicio");
    java.sql.Date Fecha_inicio = Utiles.stringToSqlDate(sFacha_inicio);
    String sFacha_vencimiento = request.getParameter("fecha_inicio");
    java.sql.Date Fecha_vencimiento = Utiles.stringToSqlDate(sFacha_vencimiento);
    String sFacha_actual = request.getParameter("fecha_actual");
    java.sql.Date Fecha_actual = Utiles.stringToSqlDate(sFacha_actual);
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    int desde = Integer.parseInt(request.getParameter("desde"));
    int hasta = Integer.parseInt(request.getParameter("hasta"));

    String tipo = "error";
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    timbrado.setFecha_inicio(Fecha_inicio);
    timbrado.setFecha_vencimiento(Fecha_vencimiento);
    timbrado.setFecha_actual(Fecha_actual);
    timbrado.setDesde(desde);
    timbrado.setHasta(hasta);
    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    timbrado.setPuesto(puesto);
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    timbrado.setEstablecimiento(establecimiento);
    String mensaje = "Datos no modificados";
    if (TimbradosControlador.modificar(timbrado)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
