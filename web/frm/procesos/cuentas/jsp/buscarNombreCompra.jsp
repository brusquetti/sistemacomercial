
<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.ComprasControlador"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_compra = request.getParameter("bnombre_compra");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = ComprasControlador.buscarNombre(nombre_compra, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>