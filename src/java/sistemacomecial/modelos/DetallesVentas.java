/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.modelos;

/**
 *
 * @author ALUMNO
 */
public class DetallesVentas {
    private int id_detalleventa;
    private int cantidad_productoventa;
    private int total;
    private int totaldetalle;
    private Productos producto;
    private Ventas venta;

    public DetallesVentas() {
    }

    public DetallesVentas(int id_detalleventa, int cantidad_productoventa, int total, int totaldetalle, Productos producto, Ventas venta) {
        this.id_detalleventa = id_detalleventa;
        this.cantidad_productoventa = cantidad_productoventa;
        this.total = total;
        this.totaldetalle = totaldetalle;
        this.producto = producto;
        this.venta = venta;
    }

    public int getId_detalleventa() {
        return id_detalleventa;
    }

    public void setId_detalleventa(int id_detalleventa) {
        this.id_detalleventa = id_detalleventa;
    }

    public int getCantidad_productoventa() {
        return cantidad_productoventa;
    }

    public void setCantidad_productoventa(int cantidad_productoventa) {
        this.cantidad_productoventa = cantidad_productoventa;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotaldetalle() {
        return totaldetalle;
    }

    public void setTotaldetalle(int totaldetalle) {
        this.totaldetalle = totaldetalle;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }
    
    
}
