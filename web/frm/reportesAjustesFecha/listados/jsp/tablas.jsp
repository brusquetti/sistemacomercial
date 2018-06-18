<%@page import="sistemacomecial.utiles.Utiles"%>
<%@page import="sistemacomecial.utiles.Conexion"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page contentType="application/pdf" %>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%
    String dirPath = "/rpt";
    String realPath = this.getServletContext().getRealPath(dirPath);
    String ajusteporfecha = request.getParameter("l");
    //int DESDE = Integer.parseInt(request.getParameter("d"));
     String sDESDE = request.getParameter("d");
    java.sql.Date d = Utiles.stringToSqlDate(sDESDE);
    
    String sHASTA = request.getParameter("h");
    java.sql.Date h = Utiles.stringToSqlDate(sHASTA);
    
    String jasperReport = ajusteporfecha +".jasper" ;
    
    
    
    JasperPrint print = null;
    Connection conn = null;

  
   
    try {
      
              
        Conexion.conectar();
        conn = Conexion.getConn();
        Map parameters = new HashMap();
        parameters.put("DESDE", d);
        parameters.put("HASTA", h);
        print = JasperFillManager.fillReport(realPath + "//" + jasperReport, parameters, conn);
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
      
      
        
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    } finally {
        if (conn != null) {
            conn.close();
        }
    }
   
    
%>



<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page contentType="application/pdf" %>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>


