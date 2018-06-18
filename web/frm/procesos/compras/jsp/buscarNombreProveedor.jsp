



<%@page import="sistemacomecial.controladores.ProveedoresControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    String nombre_proveedor = request.getParameter("bnombre_proveedor");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = ProveedoresControlador.buscarNombre(nombre_proveedor, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
      System.out.println("--->" + contenido);
    out.println(obj);
    out.flush();
%>