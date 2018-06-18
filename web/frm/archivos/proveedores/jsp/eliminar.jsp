


<%@page import="org.json.simple.JSONObject"%>
<%@page import="sistemacomecial.controladores.ProveedoresControlador"%>
<%@page import="sistemacomecial.modelos.Proveedores"%>
<%
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));

    String tipo = "error";
    String mensaje = "Datos no eliminados";

    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    
    if (ProveedoresControlador.eliminar(proveedor)) {
        tipo = "success";
        mensaje = "Datos eliminados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
