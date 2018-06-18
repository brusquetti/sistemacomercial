
<%@page import="sistemacomecial.controladores.CuentasControlador"%>
<%@page import="sistemacomecial.modelos.Cuentas"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
  // String monto_inicial =  request.getParameter("monto_inicial");
    String sfecha_apertura = request.getParameter("fecha_apertura");
    java.sql.Date fecha_apertura = Utiles.stringToSqlDate(sfecha_apertura);


    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Cuentas cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);
   
   

    if (CuentasControlador.modificar(cuenta)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>