

<%@page import="sistemacomecial.controladores.CategoriasControlador"%>
<%@page import="sistemacomecial.modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));

    String tipo = "error";
    String mensaje = "Datos no eliminados";

    Categorias categoria = new Categorias();
    categoria.setId_categoria(id_categoria);
    
    if (CategoriasControlador.eliminar(categoria)) {
        tipo = "success";
        mensaje = "Datos eliminados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
