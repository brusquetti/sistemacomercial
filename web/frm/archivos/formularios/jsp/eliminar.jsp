
<%@page import="sistemacomecial.controladores.FormulariosControlador"%>
<%@page import="sistemacomecial.modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_formulario=Integer.parseInt(request.getParameter("id_formulario"));
    
    String tipo="error";
        String mensaje="Datos no eliminados";
        
    Formularios formulario=new Formularios();
    formulario.setId_formulario(id_formulario);
   
  
    if(FormulariosControlador.eliminar(formulario)){
        tipo="success";
        mensaje="Datos eliminados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
