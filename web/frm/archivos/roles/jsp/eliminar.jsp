
<%@page import="sistemacomecial.controladores.RolesControlador"%>
<%@page import="sistemacomecial.modelos.Roles"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_rol=Integer.parseInt(request.getParameter("id_rol"));
    
    String tipo="error";
        String mensaje="Datos no eliminados";
        
    Roles rol=new Roles();
    rol.setId_rol(id_rol);
   
  
    if(RolesControlador.eliminar(rol)){
        tipo="success";
        mensaje="Datos eliminados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
