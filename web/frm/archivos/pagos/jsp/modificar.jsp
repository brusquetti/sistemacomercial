



<%@page import="sistemacomecial.controladores.TiposPagosControlador"%>
<%@page import="sistemacomecial.modelos.TiposPagos"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_tipopago=Integer.parseInt(request.getParameter("id_tipopago"));
    String nombre_tipopago=request.getParameter("nombre_tipopago");
    
    String tipo="error";
    TiposPagos tipopago=new TiposPagos();
    tipopago.setId_tipopago(id_tipopago);
    tipopago.setNombre_tipopago(nombre_tipopago);
    String mensaje="Datos no Modificados";
    if(TiposPagosControlador.modificar(tipopago)){
        tipo="success";
        mensaje="Datos modificados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
