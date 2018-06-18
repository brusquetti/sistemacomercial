
<%@page import="sistemacomecial.controladores.CajasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_caja = request.getParameter("bnombre_caja");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = CajasControlador.buscarNombre(nombre_caja, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>