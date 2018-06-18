

<%@page import="sistemacomecial.controladores.MarcasControlador"%>
<%@page import="sistemacomecial.modelos.Marcas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));

    String tipo = "error";
    String mensaje = "Datos no eliminados";

    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);
    
    if (MarcasControlador.eliminar(marca)) {
        tipo = "success";
        mensaje = "Datos eliminados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
