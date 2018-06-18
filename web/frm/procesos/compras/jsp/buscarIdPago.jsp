



<%@page import="sistemacomecial.controladores.TiposPagosControlador"%>
<%@page import="sistemacomecial.modelos.TiposPagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  
    int id_tipopago = 0;
    if (request.getParameter("id_tipopago") != "") {
        id_tipopago = Integer.parseInt(request.getParameter("id_tipopago"));
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    TiposPagos tipopago = new TiposPagos();
    tipopago.setId_tipopago(id_tipopago);

    tipopago = TiposPagosControlador.buscarId(tipopago);
    if (tipopago.getId_tipopago() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);


    obj.put("id_tipopago",tipopago.getId_tipopago());
    obj.put("nombre_tipopago",tipopago.getNombre_tipopago());
    out.print(obj);
    out.flush();
%>
