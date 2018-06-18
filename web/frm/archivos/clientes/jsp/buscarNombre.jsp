



<%@page import="sistemacomecial.controladores.ClientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    String nombre_cliente = request.getParameter("bnombre_cliente");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = ClientesControlador.buscarNombre(nombre_cliente, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
      System.out.println("--->" + contenido);
    out.println(obj);
    out.flush();
%>