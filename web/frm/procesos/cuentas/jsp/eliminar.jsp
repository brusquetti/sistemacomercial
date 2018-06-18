
<%@page import="sistemacomecial.controladores.DetallesCuentasControlador"%>
<%@page import="sistemacomecial.controladores.CuentasControlador"%>
<%@page import="sistemacomecial.modelos.DetallesCuentas"%>
<%@page import="sistemacomecial.modelos.Cuentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Cuentas cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);
    DetallesCuentas detallecuenta = new DetallesCuentas();
    detallecuenta.setCuenta(cuenta);
    CuentasControlador.modificarestado(cuenta);
    

    if (DetallesCuentasControlador.eliminarc(detallecuenta)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);
    CuentasControlador.modificarestado(cuenta);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>