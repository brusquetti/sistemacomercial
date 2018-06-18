


<%@page import="sistemacomecial.controladores.ModosPagosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_modopago=request.getParameter("bnombre_modopago");
    int pagina=Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje="Busqueda exitosa";
    String contenido=ModosPagosControlador.buscarNombre(nombre_modopago,pagina);
    JSONObject obj=new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    out.print(obj);
    out.flush();
%>
