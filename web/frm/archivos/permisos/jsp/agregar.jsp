

<%@page import="sistemacomecial.controladores.PermisosControlador"%>
<%@page import="sistemacomecial.modelos.Roles"%>
<%@page import="sistemacomecial.modelos.Formularios"%>
<%@page import="sistemacomecial.modelos.Permisos"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_permiso=Integer.parseInt(request.getParameter("id_permiso"));
    String orden_permiso=request.getParameter("orden_permiso");
    int id_formulario=Integer.parseInt(request.getParameter("id_formulario"));
    int id_rol=Integer.parseInt(request.getParameter("id_rol"));
    
    Permisos permiso=new Permisos();
    Formularios formulario =new Formularios();
    formulario.setId_formulario(id_formulario);
    Roles rol=new Roles();
    rol.setId_rol(id_rol);
    permiso.setId_permiso(id_permiso);
    permiso.setOrden_permiso(orden_permiso);
    permiso.setFormulario(formulario);
    permiso.setRol(rol);
    String mensaje="Datos no Agregados";
    if(PermisosControlador.agregar(permiso)){
        mensaje="Datos agregados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
