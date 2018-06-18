


<%@page import="sistemacomecial.controladores.TiposPagosControlador"%>
<%@page import="sistemacomecial.modelos.TiposPagos"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_tipopago=Integer.parseInt(request.getParameter("id_tipopago"));
    
    String tipo="error";
        String mensaje="Datos no eliminados";
        
    TiposPagos tipopago=new TiposPagos();
    tipopago.setId_tipopago(id_tipopago);
   
  
    if(TiposPagosControlador.eliminar(tipopago)){
        tipo="success";
        mensaje="Datos eliminados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
