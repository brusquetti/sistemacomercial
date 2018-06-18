
<%@page import="sistemacomecial.controladores.RolesControlador"%>
<%@page import="sistemacomecial.modelos.Roles"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));
    String nombre_rol=request.getParameter("nombre_rol");
    Roles rol=new Roles();
    rol.setId_rol(id_rol);
    rol.setNombre_rol(nombre_rol);
    String mensaje="Datos no Agregados";
    if(RolesControlador.agregar(rol)){
        mensaje="Datos agregados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
