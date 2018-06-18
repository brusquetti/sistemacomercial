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
public class Ventas {

    private int id_venta;
    private Date fecha_venta;
    private String estado_venta;
    private Clientes cliente;
    //private TiposPagos pago;
    private Establecimientos establecimiento;
    private Puestos puesto;
    private int numero_factura_venta;
    private Timbrados timbrado;
    private Usuarios usuario;
    private int total;

    public Ventas() {
    }

    public Ventas(int id_venta, Date fecha_venta, String estado_venta, Clientes cliente, Establecimientos establecimiento, Puestos puesto, int numero_factura_venta, Timbrados timbrado, Usuarios usuario, int total) {
        this.id_venta = id_venta;
        this.fecha_venta = fecha_venta;
        this.estado_venta = estado_venta;
        this.cliente = cliente;
        this.establecimiento = establecimiento;
        this.puesto = puesto;
        this.numero_factura_venta = numero_factura_venta;
        this.timbrado = timbrado;
        this.usuario = usuario;
        this.total = total;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getEstado_venta() {
        return estado_venta;
    }

    public void setEstado_venta(String estado_venta) {
        this.estado_venta = estado_venta;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Establecimientos getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimientos establecimiento) {
        this.establecimiento = establecimiento;
    }

    public Puestos getPuesto() {
        return puesto;
    }

    public void setPuesto(Puestos puesto) {
        this.puesto = puesto;
    }

    public int getNumero_factura_venta() {
        return numero_factura_venta;
    }

    public void setNumero_factura_venta(int numero_factura_venta) {
        this.numero_factura_venta = numero_factura_venta;
    }

    public Timbrados getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(Timbrados timbrado) {
        this.timbrado = timbrado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    
}
