

<%@page import="sistemacomecial.controladores.CategoriasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    String nombre_categoria = request.getParameter("bnombre_categoria");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = CategoriasControlador.buscarNombre(nombre_categoria, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
      System.out.println("--->" + contenido);
    out.println(obj);
    out.flush();
%>