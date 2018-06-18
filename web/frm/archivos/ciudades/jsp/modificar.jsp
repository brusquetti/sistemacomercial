
<%@page import="sistemacomecial.controladores.CiudadesControlador"%>
<%@page import="sistemacomecial.modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    String nombre_ciudad = request.getParameter("nombre_ciudad");

    String tipo = "error";
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    ciudad.setNombre_ciudad(nombre_ciudad);

    String mensaje = "Datos no modificados";
    if (CiudadesControlador.modificar(ciudad)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
