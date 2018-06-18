
<%@page import="sistemacomecial.controladores.AjustesControlador"%>
<%@page import="sistemacomecial.modelos.Ajustes"%>
<%@page import="sistemacomecial.modelos.Usuarios"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ajuste = Integer.parseInt(request.getParameter("id_ajuste"));
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    String sfecha_ajuste = request.getParameter("fecha_ajuste");
    java.sql.Date fecha_ajuste = Utiles.stringToSqlDate(sfecha_ajuste);
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);

    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste(id_ajuste);
    ajuste.setFecha_ajuste(fecha_ajuste);
    ajuste.setUsuario(usuario);
    if (AjustesControlador.agregar(ajuste)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_ajuste", String.valueOf(ajuste.getId_ajuste()));
    out.print(obj);
    out.flush();

%>