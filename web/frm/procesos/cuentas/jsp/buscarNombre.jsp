
<%@page import="sistemacomecial.controladores.CuentasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_cuenta = request.getParameter("bnombre_cuenta");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = CuentasControlador.buscarNombre(nombre_cuenta, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>