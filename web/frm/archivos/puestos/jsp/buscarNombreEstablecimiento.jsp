



<%@page import="sistemacomecial.controladores.EstablecimientosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    String nombre_establecimiento = request.getParameter("bnombre_establecimiento");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = EstablecimientosControlador.buscarNombre(nombre_establecimiento, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
      System.out.println("--->" + contenido);
    out.println(obj);
    out.flush();
%>