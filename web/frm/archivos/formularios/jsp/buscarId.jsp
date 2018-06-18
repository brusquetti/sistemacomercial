

<%@page import="sistemacomecial.controladores.FormulariosControlador"%>
<%@page import="sistemacomecial.modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%   
int id_formulario = 0;
    if (request.getParameter("id_formulario") != "") {
        id_formulario = Integer.parseInt(request.getParameter("id_formulario"));
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Formularios formulario = new Formularios();
    formulario.setId_formulario(id_formulario);

    formulario = FormulariosControlador.buscarId(formulario);
    if (formulario.getId_formulario() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_formulario", formulario.getId_formulario());
    obj.put("nombre_formulario", formulario.getNombre_formulario());
    obj.put("codigo_formulario",formulario.getCodigo_formulario());
   
    obj.put("id_menu",formulario.getMenu().getId_menu());
    obj.put("nombre_menu",formulario.getMenu().getNombre_menu());
    
    
    out.print(obj);
    out.flush();
%>


