<%@page import="sistemacomecial.controladores.CajasControlador"%>
<%@page import="sistemacomecial.modelos.Cajas"%>
<%@page import="sistemacomecial.controladores.DetallesCajasControlador"%>
<%@page import="sistemacomecial.controladores.CajasControlador"%>
<%@page import="sistemacomecial.modelos.DetallesCajas"%>
<%@page import="sistemacomecial.modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setCaja(caja);
    CajasControlador.modificarestado(caja);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>