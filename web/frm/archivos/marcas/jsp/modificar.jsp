
<%@page import="sistemacomecial.controladores.MarcasControlador"%>
<%@page import="sistemacomecial.modelos.Marcas"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    String nombre_marca = request.getParameter("nombre_marca");

    String tipo = "error";
    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);
    marca.setNombre_marca(nombre_marca);

    String mensaje = "Datos no modificados";
    if (MarcasControlador.modificar(marca)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
