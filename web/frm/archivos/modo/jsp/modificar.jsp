



<%@page import="sistemacomecial.controladores.ModosPagosControlador"%>
<%@page import="sistemacomecial.modelos.ModosPagos"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_modopago=Integer.parseInt(request.getParameter("id_modopago"));
    String nombre_modopago=request.getParameter("nombre_modopago");
    
    String tipo="error";
    ModosPagos modopago=new ModosPagos();
    modopago.setId_modopago(id_modopago);
    modopago.setNombre_modopago(nombre_modopago);
    String mensaje="Datos no Modificados";
    if(ModosPagosControlador.modificar(modopago)){
        tipo="success";
        mensaje="Datos modificados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
