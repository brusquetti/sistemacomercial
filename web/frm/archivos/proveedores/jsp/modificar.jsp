


<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.ProveedoresControlador"%>
<%@page import="sistemacomecial.modelos.Ciudades"%>
<%@page import="sistemacomecial.modelos.Proveedores"%>
<%
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
    String ruc_proveedor = request.getParameter("ruc_proveedor");
    String nombre_proveedor = request.getParameter("nombre_proveedor");
    String correo_proveedor = request.getParameter("correo_proveedor");
    String telefono_proveedor = request.getParameter("telefono_proveedor");
    String telefono2_proveedor = request.getParameter("telefono2_proveedor");
    String direccion_proveedor = request.getParameter("direccion_proveedor");
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));

    String tipo = "error";
    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    proveedor.setRuc_proveedor(ruc_proveedor);
    proveedor.setNombre_proveedor(nombre_proveedor);
    proveedor.setCorreo_proveedor(correo_proveedor);
    proveedor.setTelefono_proveedor(telefono_proveedor);
    proveedor.setTelefono2_proveedor(telefono2_proveedor);
    proveedor.setDireccion_proveedor(direccion_proveedor);

    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);

    proveedor.setCiudad(ciudad);
    String mensaje = "Datos no modificados";
    if (ProveedoresControlador.modificar(proveedor)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
