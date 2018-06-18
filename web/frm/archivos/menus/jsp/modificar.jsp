
<%@page import="sistemacomecial.controladores.MenusControlador"%>
<%@page import="sistemacomecial.modelos.Menus"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_menu=Integer.parseInt(request.getParameter("id_menu"));
    String nombre_menu=request.getParameter("nombre_menu");
    String codigo_menu=request.getParameter("codigo_menu");
    
    String tipo="error";
    Menus menu=new Menus();
    menu.setId_menu(id_menu);
    menu.setNombre_menu(nombre_menu);
    menu.setCodigo_menu(codigo_menu);
    
    String mensaje="Datos no Modificados";
    if(MenusControlador.modificar(menu)){
        tipo="success";
        mensaje="Datos modificados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
