

<%@page import="sistemacomecial.modelos.Timbrados"%>
<%@page import="sistemacomecial.modelos.Usuarios"%>
<%@page import="sistemacomecial.modelos.TiposPagos"%>
<%@page import="sistemacomecial.modelos.Puestos"%>
<%@page import="sistemacomecial.modelos.Establecimientos"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="sistemacomecial.modelos.Clientes"%>
<%@page import="sistemacomecial.controladores.VentasControlador"%>
<%@page import="sistemacomecial.modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int numero_factura_venta = Integer.parseInt(request.getParameter("numero_factura_venta"));
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    String sfecha_venta = request.getParameter("fecha_venta");
    java.sql.Date fecha_venta = Utiles.stringToSqlDate(sfecha_venta);
    String estado_venta = request.getParameter("estado_venta");
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    //int id_tipopago = Integer.parseInt(request.getParameter("id_tipopago"));
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);

    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    venta.setFecha_venta(fecha_venta);
    venta.setEstado_venta(estado_venta);
    venta.setNumero_factura_venta(numero_factura_venta);

    venta.setCliente(cliente);

    //venta.setPago(pago);
    venta.setEstablecimiento(establecimiento);
    venta.setPuesto(puesto);
    venta.setUsuario(usuario);
    venta.setTimbrado(timbrado);

    

    if (VentasControlador.agregar(venta)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_venta", String.valueOf(venta.getId_venta()));
  //  obj.put("numero_factura_venta", String.valueOf(venta.getNumero_factura_venta()));
  //  obj.put("id_establecimiento", String.valueOf(venta.getEstablecimiento().getId_establecimiento()));
  //  obj.put("id_puesto", String.valueOf(venta.getPuesto().getId_puesto()));
    //obj.put("id_timbrado", String.valueOf(venta.getTimbrado().getId_timbrado()));

    out.print(obj);
    out.flush();

%>