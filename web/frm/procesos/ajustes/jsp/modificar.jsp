<%@page import="sistemacomecial.controladores.AjustesControlador"%>
<%@page import="sistemacomecial.modelos.Ajustes"%>
<%@page import="sistemacomecial.modelos.Proveedores"%>
<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ajuste = Integer.parseInt(request.getParameter("id_ajuste"));
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));

     //String numero_factura = request.getParameter("numero_factura");
    // String timbrado_ajuste = request.getParameter("timbrado_ajuste");
   
     String sFecha_ajuste = request.getParameter("fecha_ajuste");
    java.sql.Date Fecha_ajuste = Utiles.stringToSqlDate(sFecha_ajuste);
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);

    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste(id_ajuste);
    //ajuste.setNumero_factura(numero_factura);
    //ajuste.setTimbrado_ajuste(timbrado_ajuste);
    ajuste.setFecha_ajuste(Fecha_ajuste);
    //ajuste.setProveedor(proveedor);
   
    if (AjustesControlador.modificar(ajuste)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>