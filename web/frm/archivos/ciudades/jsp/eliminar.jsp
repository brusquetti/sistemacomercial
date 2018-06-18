

<%@page import="sistemacomecial.controladores.CiudadesControlador"%>
<%@page import="sistemacomecial.modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));

    String tipo = "error";
    String mensaje = "Datos no eliminados";

    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    
    if (CiudadesControlador.eliminar(ciudad)) {
        tipo = "success";
        mensaje = "Datos eliminados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
