
<%@page import="sistemacomecial.controladores.CajasControlador"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="sistemacomecial.modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
   String monto_inicial =  request.getParameter("monto_inicial");
    String sfecha_apertura = request.getParameter("fecha_apertura");
    java.sql.Date fecha_apertura = Utiles.stringToSqlDate(sfecha_apertura);


    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
   
   

    if (CajasControlador.modificar(caja)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>