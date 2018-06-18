
<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.TimbradosControlador"%>
<%@page import="sistemacomecial.modelos.Timbrados"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));

    String tipo = "error";
    String mensaje = "Datos no eliminados";

    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    
    if (TimbradosControlador.eliminar(timbrado)) {
        tipo = "success";
        mensaje = "Datos eliminados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
