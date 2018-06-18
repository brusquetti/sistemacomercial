
<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.TimbradosControlador"%>
<%@page import="sistemacomecial.modelos.Establecimientos"%>
<%@page import="sistemacomecial.modelos.Timbrados"%>
<%@page import="sistemacomecial.modelos.Puestos"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    String numero_timbrado = request.getParameter("numero_timbrado");
    String sfecha_inicio = request.getParameter("fecha_inicio");
    java.sql.Date Fecha_inicio = Utiles.stringToSqlDate(sfecha_inicio);
    String sfecha_vencimiento = request.getParameter("fecha_vencimiento");
    java.sql.Date Fecha_vencimiento = Utiles.stringToSqlDate(sfecha_vencimiento);
    String sfecha_actual = request.getParameter("fecha_actual");
    java.sql.Date Fecha_actual = Utiles.stringToSqlDate(sfecha_actual);
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    int desde = Integer.parseInt(request.getParameter("desde"));
    int hasta = Integer.parseInt(request.getParameter("hasta"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    Timbrados timbrado = new Timbrados();
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);

    timbrado.setId_timbrado(id_timbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    timbrado.setFecha_inicio(Fecha_inicio);
    timbrado.setFecha_vencimiento(Fecha_vencimiento);
    timbrado.setFecha_actual(Fecha_actual);
    timbrado.setDesde(desde);
    timbrado.setHasta(hasta);

    timbrado.setPuesto(puesto);
    timbrado.setEstablecimiento(establecimiento);
    if (TimbradosControlador.agregar(timbrado)) {
        tipo = "success";
        mensaje = "Datos agregados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>