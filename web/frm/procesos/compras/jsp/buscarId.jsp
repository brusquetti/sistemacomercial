<%@page import="sistemacomecial.modelos.ModosPagos"%>
<%@page import="sistemacomecial.modelos.TiposPagos"%>
<%@page import="sistemacomecial.controladores.DetallesComprasControlador"%>
<%@page import="sistemacomecial.modelos.Proveedores"%>
<%@page import="sistemacomecial.modelos.Clientes"%>
<%@page import="sistemacomecial.controladores.ComprasControlador"%>
<%@page import="sistemacomecial.modelos.Compras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compra = 0;
    if (request.getParameter("id_compra") != "") {
        id_compra = Integer.parseInt(request.getParameter("id_compra"));

    }

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Compras compras = ComprasControlador.buscarId(id_compra);
    if (id_compra != 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        compras = new Compras();
        compras.setId_compra(0);
        compras.setEstado_compra("ACTIVO");
        compras.setNumero_factura_compra("");
        java.sql.Date fecha_compra = new java.sql.Date(new java.util.Date().getTime());
        compras.setFecha_compra(fecha_compra);
        java.sql.Date fecha_emitida = new java.sql.Date(new java.util.Date().getTime());
        compras.setFecha_emitida(fecha_emitida);

        Proveedores proveedor = new Proveedores();
        proveedor.setId_proveedor(0);
        proveedor.setNombre_proveedor("");
        compras.setProveedor(proveedor);

        ModosPagos modo = new ModosPagos();
        modo.setId_modopago(0);
        modo.setNombre_modopago("");
        compras.setModo(modo);
    }

    String contenido_detalle = DetallesComprasControlador.buscarIdCompra(id_compra);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_compra", String.valueOf(compras.getId_compra()));
    obj.put("numero_factura_compra", compras.getNumero_factura_compra());
    obj.put("fecha_compra", String.valueOf(compras.getFecha_compra()));
    System.out.println(String.valueOf(compras.getFecha_compra()));
    obj.put("fecha_emitida", String.valueOf(compras.getFecha_emitida()));
    System.out.println(String.valueOf(compras.getFecha_emitida()));
    //obj.put("timbrado_compra", compras.getTimbrado_compra());
    obj.put("estado_compra", compras.getEstado_compra());
    obj.put("id_proveedor", String.valueOf(compras.getProveedor().getId_proveedor()));
    obj.put("nombre_proveedor", compras.getProveedor().getNombre_proveedor());
    obj.put("ruc_proveedor", compras.getProveedor().getRuc_proveedor());
    obj.put("id_modopago", String.valueOf(compras.getModo().getId_modopago()));
    obj.put("nombre_modopago", compras.getModo().getNombre_modopago());
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>