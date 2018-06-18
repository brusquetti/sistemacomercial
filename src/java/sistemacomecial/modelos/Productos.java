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
public class Productos {
    private int id_producto;
    private String nombre_producto;
    private int costo_venta;
    private int costo_compra;
    private int iva_producto;
    private Marcas marca;
    private Categorias categoria;
    private int cantidad_existente;

    public Productos() {
    }

    public Productos(int id_producto, String nombre_producto, int costo_venta, int costo_compra, int iva_producto, Marcas marca, Categorias categoria, int cantidad_existente) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.costo_venta = costo_venta;
        this.costo_compra = costo_compra;
        this.iva_producto = iva_producto;
        this.marca = marca;
        this.categoria = categoria;
        this.cantidad_existente = cantidad_existente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getCosto_venta() {
        return costo_venta;
    }

    public void setCosto_venta(int costo_venta) {
        this.costo_venta = costo_venta;
    }

    public int getCosto_compra() {
        return costo_compra;
    }

    public void setCosto_compra(int costo_compra) {
        this.costo_compra = costo_compra;
    }

    public int getIva_producto() {
        return iva_producto;
    }

    public void setIva_producto(int iva_producto) {
        this.iva_producto = iva_producto;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public int getCantidad_existente() {
        return cantidad_existente;
    }

    public void setCantidad_existente(int cantidad_existente) {
        this.cantidad_existente = cantidad_existente;
    }
    
}
