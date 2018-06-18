


<%@page import="sistemacomecial.controladores.TiposPagosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_tipopago=request.getParameter("bnombre_tipopago");
    int pagina=Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje="Busqueda exitosa";
    String contenido=TiposPagosControlador.buscarNombre(nombre_tipopago,pagina);
    JSONObject obj=new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    out.print(obj);
    out.flush();
%>

