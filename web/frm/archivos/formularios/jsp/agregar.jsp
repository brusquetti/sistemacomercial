
<%@page import="sistemacomecial.modelos.Menus"%>
<%@page import="sistemacomecial.controladores.FormulariosControlador"%>
<%@page import="sistemacomecial.modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //int id_formulario=Integer.parseInt(request.getParameter("id_formulario"));
    String nombre_formulario=request.getParameter("nombre_formulario");
    String codigo_formulario=request.getParameter("codigo_formulario");
    
    int id_menu =Integer.parseInt(request.getParameter("id_menu"));
    Menus menu = new Menus();
    menu.setId_menu(id_menu);

    Formularios formulario=new Formularios();
    formulario.setMenu(menu);
    //formulario.setId_formulario(id_formulario);
    formulario.setNombre_formulario(nombre_formulario);
    formulario.setCodigo_formulario(codigo_formulario);
    String mensaje="Datos no Agregados";
    if(FormulariosControlador.agregar(formulario)){
        mensaje="Datos agregados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
