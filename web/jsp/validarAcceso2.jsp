
<%@page import="sistemacomecial.controladores.UsuariosControlador"%>
<%@page import="sistemacomecial.modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String login_usuario=request.getParameter("login_usuario");
    String password_usuario=request.getParameter("password_usuario");
    
    Usuarios usuario=new Usuarios();
    usuario.setLogin_usuario(login_usuario);
    usuario.setPassword_usuario(password_usuario);
    boolean acceso=UsuariosControlador.validarAcceso(usuario);
    
    JSONObject obj=new JSONObject();
    obj.put("acceso",String.valueOf(acceso));
    out.print(obj);
    out.flush();
    %>

