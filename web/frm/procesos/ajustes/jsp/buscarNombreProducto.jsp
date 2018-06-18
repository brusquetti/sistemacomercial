<%@page import="sistemacomecial.controladores.ProductosControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    String nombre_producto = request.getParameter("bnombre_producto");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = ProductosControlador.buscarNombre(nombre_producto, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
      System.out.println("--->" + contenido);
    out.println(obj);
    out.flush();
%>