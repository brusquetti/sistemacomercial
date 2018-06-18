



<%@page import="sistemacomecial.modelos.Ciudades"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.ClientesControlador"%>
<%@page import="sistemacomecial.modelos.Clientes"%>
<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    String ruc_cliente = request.getParameter("ruc_cliente");

    String nombre_cliente = request.getParameter("nombre_cliente");
    String apellido_cliente = request.getParameter("apellido_cliente");
    int cedula_cliente = Integer.parseInt(request.getParameter("cedula_cliente"));
    String sfecha_nacimiento_cliente = request.getParameter("fecha_nacimiento_cliente");
    java.sql.Date fecha_nacimiento_cliente = Utiles.stringToSqlDate(sfecha_nacimiento_cliente);
    //String cedula_cliente = request.getParameter("cedula_cliente");
    String direccion_cliente = request.getParameter("direccion_cliente");
    String telefono_cliente = request.getParameter("telefono_cliente");
    //String telefono_cliente = request.getParameter("telefono_cliente");
    String correo_cliente = request.getParameter("correo_cliente");
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    cliente.setRuc_cliente(ruc_cliente);
    cliente.setNombre_cliente(nombre_cliente);
    cliente.setApellido_cliente(apellido_cliente);
    cliente.setCedula_cliente(cedula_cliente);
    cliente.setFecha_nacimiento_cliente(fecha_nacimiento_cliente);

    cliente.setDireccion_cliente(direccion_cliente);
    cliente.setTelefono_cliente(telefono_cliente);
    cliente.setCorreo_cliente(correo_cliente);
    cliente.setCiudad(ciudad);

    if (ClientesControlador.agregar(cliente)) {
        tipo = "success";
        mensaje = "Datos agregados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>