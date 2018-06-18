



<%@page import="sistemacomecial.controladores.PuestosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    String nombre_puesto = request.getParameter("bnombre_puesto");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = PuestosControlador.buscarNombre(nombre_puesto, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
      System.out.println("--->" + contenido);
    out.println(obj);
    out.flush();
%>