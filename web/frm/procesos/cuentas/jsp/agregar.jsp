
<%@page import="sistemacomecial.modelos.Usuarios"%>
<%@page import="sistemacomecial.modelos.Cuentas"%>
<%@page import="sistemacomecial.controladores.CuentasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    String sfecha_apertura = request.getParameter("fecha_apertura");
    java.sql.Date fecha_apertura = Utiles.stringToSqlDate(sfecha_apertura);
   
    String estado_cuenta = "ABIERTO";
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);

    Cuentas cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);
    cuenta.setFecha_apertura(fecha_apertura);
 
    cuenta.setEstado_cuenta(estado_cuenta);
    cuenta.setUsuario(usuario);

    if (CuentasControlador.agregar(cuenta)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_cuenta", String.valueOf(cuenta.getId_cuenta()));
    out.print(obj);
    out.flush();

%>