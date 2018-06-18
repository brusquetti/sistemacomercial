/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.modelos;

import java.sql.Date;

/**
 *
 * @author ALUMNO
 */
public class Compras {
    private int id_compra;
    private String numero_factura_compra;
    private Date fecha_compra;
    //private String timbrado_compra;
    private int total_pagar;
    private Proveedores proveedor;
    private String estado_compra;
    private Date fecha_emitida;
    private Usuarios usuario;
    private TiposPagos pago;
    private ModosPagos modo;
    private int total;

    public Compras() {
    }

    public Compras(int id_compra, String numero_factura_compra, Date fecha_compra, int total_pagar, Proveedores proveedor, String estado_compra, Date fecha_emitida, Usuarios usuario, TiposPagos pago, ModosPagos modo, int total) {
        this.id_compra = id_compra;
        this.numero_factura_compra = numero_factura_compra;
        this.fecha_compra = fecha_compra;
        this.total_pagar = total_pagar;
        this.proveedor = proveedor;
        this.estado_compra = estado_compra;
        this.fecha_emitida = fecha_emitida;
        this.usuario = usuario;
        this.pago = pago;
        this.modo = modo;
        this.total = total;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getNumero_factura_compra() {
        return numero_factura_compra;
    }

    public void setNumero_factura_compra(String numero_factura_compra) {
        this.numero_factura_compra = numero_factura_compra;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(int total_pagar) {
        this.total_pagar = total_pagar;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstado_compra() {
        return estado_compra;
    }

    public void setEstado_compra(String estado_compra) {
        this.estado_compra = estado_compra;
    }

    public Date getFecha_emitida() {
        return fecha_emitida;
    }

    public void setFecha_emitida(Date fecha_emitida) {
        this.fecha_emitida = fecha_emitida;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public TiposPagos getPago() {
        return pago;
    }

    public void setPago(TiposPagos pago) {
        this.pago = pago;
    }

    public ModosPagos getModo() {
        return modo;
    }

    public void setModo(ModosPagos modo) {
        this.modo = modo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

  
}
