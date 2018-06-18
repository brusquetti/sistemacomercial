

<%@page import="sistemacomecial.modelos.Formularios"%>
<%@page import="sistemacomecial.modelos.Roles"%>
<%@page import="sistemacomecial.controladores.PermisosControlador"%>
<%@page import="sistemacomecial.modelos.Permisos"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
    //String nombre_permiso=request.getParameter("nombre_permiso");
    String orden_permiso = request.getParameter("orden_permiso");
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));
    int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));

    String tipo = "error";
    Permisos permiso = new Permisos();
    permiso.setId_permiso(id_permiso);
    Roles rol = new Roles();
    rol.setId_rol(id_rol);
    Formularios formulario = new Formularios();      
    formulario.setId_formulario(id_formulario);
    permiso.setRol(rol);
    permiso.setFormulario(formulario);

    //permiso.setNombre_permiso(nombre_permiso);
    permiso.setOrden_permiso(orden_permiso);
    String mensaje = "Datos no Modificados";
    if (PermisosControlador.modificar(permiso)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";

    };
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
