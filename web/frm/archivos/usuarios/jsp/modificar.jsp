
<%@page import="sistemacomecial.controladores.UsuariosControlador"%>
<%@page import="sistemacomecial.modelos.Roles"%>
<%@page import="sistemacomecial.modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    String nombre_usuario = request.getParameter("nombre_usuario");
    String password_usuario = request.getParameter("password_usuario");
    String login_usuario = request.getParameter("login_usuario");
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));
    String tipo = "error";
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    usuario.setNombre_usuario(nombre_usuario);
    usuario.setPassword_usuario(password_usuario);
    usuario.setLogin_usuario(login_usuario);
    Roles rol = new Roles();
    rol.setId_rol(id_rol);
    usuario.setRol(rol);
    String mensaje = "Datos no modificados";
    if (UsuariosControlador.modificar(usuario)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
