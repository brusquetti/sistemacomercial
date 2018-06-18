


<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.EstablecimientosControlador"%>
<%@page import="sistemacomecial.modelos.Establecimientos"%>
<%
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));

    String tipo = "error";
    String mensaje = "Datos no eliminados";

    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    
    if (EstablecimientosControlador.eliminar(establecimiento)) {
        tipo = "success";
        mensaje = "Datos eliminados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
