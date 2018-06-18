
<%@page import="sistemacomecial.controladores.UsuariosControlador"%>
<%@page import="sistemacomecial.modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

    String tipo = "error";
    String mensaje = "Datos no eliminados";

    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    
    if (UsuariosControlador.eliminar(usuario)) {
        tipo = "success";
        mensaje = "Datos eliminados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
