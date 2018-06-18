

<%@page import="sistemacomecial.controladores.MarcasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    String nombre_marca = request.getParameter("bnombre_marca");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = MarcasControlador.buscarNombre(nombre_marca, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
      System.out.println("--->" + contenido);
    out.println(obj);
    out.flush();
%>