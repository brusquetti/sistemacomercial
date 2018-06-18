


<%@page import="sistemacomecial.controladores.PermisosControlador"%>
<%@page import="sistemacomecial.modelos.Permisos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_permiso = 0;
    if (request.getParameter("id_permiso") != "") {
        id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
    }

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Permisos permiso = new Permisos();
    permiso.setId_permiso(id_permiso);
    permiso = PermisosControlador.buscarId(permiso);
    
    if (permiso.getId_permiso() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_permiso", permiso.getId_permiso());
    obj.put("orden_permiso", permiso.getOrden_permiso());
    obj.put("id_rol", permiso.getRol().getId_rol());
    obj.put("nombre_rol", permiso.getRol().getNombre_rol());
    obj.put("id_formulario", permiso.getFormulario().getId_formulario());
    obj.put("nombre_formulario", permiso.getFormulario().getNombre_formulario());

    out.print(obj);
    out.flush();
%>
