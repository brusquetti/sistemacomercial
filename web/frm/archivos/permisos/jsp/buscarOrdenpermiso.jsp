<%@page import="sistemacomecial.controladores.PermisosControlador"%>
<%@page import="java.util.ArrayList"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String orden_permiso=request.getParameter("borden_permiso");
    int pagina=Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje="Busqueda exitosa";
    String contenido=PermisosControlador.buscarOrdenPermiso(orden_permiso,pagina);
    JSONObject obj=new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    out.print(obj);
    out.flush();
%>
