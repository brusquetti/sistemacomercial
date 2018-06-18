
<%@page import="sistemacomecial.modelos.Menus"%>
<%@page import="sistemacomecial.controladores.MenusControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_menu=Integer.parseInt(request.getParameter("id_menu"));
    String tipo="error";
    String mensaje="Datos no encontrados";
    String nuevo="true";
    Menus menu=new Menus();
    menu.setId_menu(id_menu);
    menu=MenusControlador.buscarId(menu);
    if(menu!=null){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj= new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    obj.put("nuevo",nuevo);
    
    obj.put("id_menu",menu.getId_menu());
    obj.put("nombre_menu",menu.getNombre_menu());
    
    out.print(obj);
    out.flush(); 
%>
