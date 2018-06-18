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
public class DetallesCompras {
    private int id_detallecompra;
    private int cantidad_productocompra;
    private int total;
    private int totaldetalle;
    private Productos producto;
    private Compras compra;
    private Marcas marca;

    public DetallesCompras() {
    }

    public DetallesCompras(int id_detallecompra, int cantidad_productocompra, int total, int totaldetalle, Productos producto, Compras compra, Marcas marca) {
        this.id_detallecompra = id_detallecompra;
        this.cantidad_productocompra = cantidad_productocompra;
        this.total = total;
        this.totaldetalle = totaldetalle;
        this.producto = producto;
        this.compra = compra;
        this.marca = marca;
    }

    public int getId_detallecompra() {
        return id_detallecompra;
    }

    public void setId_detallecompra(int id_detallecompra) {
        this.id_detallecompra = id_detallecompra;
    }

    public int getCantidad_productocompra() {
        return cantidad_productocompra;
    }

    public void setCantidad_productocompra(int cantidad_productocompra) {
        this.cantidad_productocompra = cantidad_productocompra;
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

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

}
