


<%@page import="sistemacomecial.controladores.ModosPagosControlador"%>
<%@page import="sistemacomecial.modelos.ModosPagos"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_modopago=Integer.parseInt(request.getParameter("id_modopago"));
    
    String tipo="error";
        String mensaje="Datos no eliminados";
        
    ModosPagos modopago=new ModosPagos();
    modopago.setId_modopago(id_modopago);
   
  
    if(ModosPagosControlador.eliminar(modopago)){
        tipo="success";
        mensaje="Datos eliminados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
