

<%@page import="sistemacomecial.modelos.ModosPagos"%>
<%@page import="sistemacomecial.modelos.TiposPagos"%>
<%@page import="sistemacomecial.modelos.Usuarios"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="sistemacomecial.modelos.Proveedores"%>
<%@page import="sistemacomecial.controladores.ComprasControlador"%>
<%@page import="sistemacomecial.modelos.Compras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    String numero_factura_compra = request.getParameter("numero_factura_compra");
    String sfecha_compra = request.getParameter("fecha_compra");
    java.sql.Date fecha_compra = Utiles.stringToSqlDate(sfecha_compra);
    String sfecha_emitida = request.getParameter("fecha_emitida");
    java.sql.Date fecha_emitida = Utiles.stringToSqlDate(sfecha_emitida);
    //String timbrado_compra = request.getParameter("timbrado_compra");
    String estado_compra = request.getParameter("estado_compra");
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    int id_modopago = Integer.parseInt(request.getParameter("id_modopago"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    Compras compra = new Compras();
    compra.setId_compra(id_compra);
    compra.setNumero_factura_compra(numero_factura_compra);
    compra.setFecha_compra(fecha_compra);
    compra.setFecha_emitida(fecha_emitida);
    //compra.setTimbrado_compra(timbrado_compra);

    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);

    ModosPagos modo = new ModosPagos();
    modo.setId_modopago(id_modopago);
    compra.setModo(modo);

    compra.setEstado_compra(estado_compra);
    compra.setProveedor(proveedor);
    compra.setUsuario(usuario);

    if (ComprasControlador.agregar(compra)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_compra", String.valueOf(compra.getId_compra()));
    out.print(obj);
    out.flush();

%>