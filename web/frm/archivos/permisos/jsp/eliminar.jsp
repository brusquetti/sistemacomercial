

<%@page import="sistemacomecial.controladores.PermisosControlador"%>
<%@page import="sistemacomecial.modelos.Permisos"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_permiso=Integer.parseInt(request.getParameter("id_permiso"));
    
    String tipo="error";
        String mensaje="Datos no eliminados";
        
    Permisos permiso=new Permisos();
    permiso.setId_permiso(id_permiso);
   
  
    if(PermisosControlador.eliminar(permiso)){
        tipo="success";
        mensaje="Datos eliminados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
