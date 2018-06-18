
<%@page import="sistemacomecial.controladores.ModosPagosControlador"%>
<%@page import="sistemacomecial.modelos.ModosPagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  
    int id_modopago = 0;
    if (request.getParameter("id_modopago") != "") {
        id_modopago = Integer.parseInt(request.getParameter("id_modopago"));
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    ModosPagos modopago = new ModosPagos();
    modopago.setId_modopago(id_modopago);

    modopago = ModosPagosControlador.buscarId(modopago);
    if (modopago.getId_modopago() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);


    obj.put("id_modopago",modopago.getId_modopago());
    obj.put("nombre_modopago",modopago.getNombre_modopago());
    out.print(obj);
    out.flush();
%>
