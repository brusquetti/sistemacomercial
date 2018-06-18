




<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.EstablecimientosControlador"%>
<%@page import="sistemacomecial.modelos.Establecimientos"%>
<%@page import="sistemacomecial.modelos.Ciudades"%>
<%
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    String nombre_establecimiento = request.getParameter("nombre_establecimiento");
    String actividad_economica = request.getParameter("actividad_economica");
    String ruc = request.getParameter("ruc");
    String representante = request.getParameter("representante");
    String telefono_establecimiento = request.getParameter("telefono_establecimiento");
    String direccion_establecimiento = request.getParameter("direccion_establecimiento");
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);

    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    establecimiento.setNombre_establecimiento(nombre_establecimiento);
    establecimiento.setActividad_economica(actividad_economica);
    establecimiento.setRuc(ruc);
    establecimiento.setRepresentante(representante);
    establecimiento.setTelefono_establecimiento(telefono_establecimiento);
    establecimiento.setDireccion_establecimiento(direccion_establecimiento);
    establecimiento.setCiudad(ciudad);

    if (EstablecimientosControlador.agregar(establecimiento)) {
        tipo = "success";
        mensaje = "Datos agregados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>