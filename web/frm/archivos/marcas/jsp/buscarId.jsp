
<%@page import="sistemacomecial.controladores.MarcasControlador"%>
<%@page import="sistemacomecial.modelos.Marcas"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_marca = 0;
    if (request.getParameter("id_marca") != "") {
        id_marca = Integer.parseInt(request.getParameter("id_marca"));
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);

    marca = MarcasControlador.buscarId(marca);
    if (marca.getId_marca() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_marca", marca.getId_marca());
    obj.put("nombre_marca", marca.getNombre_marca());
    
    out.print(obj);
    out.flush();
%>