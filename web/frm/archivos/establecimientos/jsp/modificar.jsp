


<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.EstablecimientosControlador"%>
<%@page import="sistemacomecial.modelos.Ciudades"%>
<%@page import="sistemacomecial.modelos.Establecimientos"%>
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
    
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    establecimiento.setNombre_establecimiento(nombre_establecimiento);
    establecimiento.setActividad_economica(actividad_economica);
    establecimiento.setRuc(ruc);
    establecimiento.setRepresentante(representante);
    establecimiento.setTelefono_establecimiento(telefono_establecimiento);
    establecimiento.setDireccion_establecimiento(direccion_establecimiento);

    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);

    establecimiento.setCiudad(ciudad);
    String mensaje = "Datos no modificados";
    if (EstablecimientosControlador.modificar(establecimiento)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
