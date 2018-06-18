
<%@page import="sistemacomecial.controladores.MenusControlador"%>
<%@page import="sistemacomecial.modelos.Menus"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_menu=Integer.parseInt(request.getParameter("id_menu"));
    
    String tipo="error";
        String mensaje="Datos no eliminados";
        
    Menus menu=new Menus();
    menu.setId_menu(id_menu);
   
  
    if(MenusControlador.eliminar(menu)){
        tipo="success";
        mensaje="Datos eliminados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
