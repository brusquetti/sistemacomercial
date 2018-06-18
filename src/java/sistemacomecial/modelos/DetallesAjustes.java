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
public class DetallesAjustes {

    private int id_detalleajuste;
    private Ajustes ajuste;
    private int cantidad_ajuste;
    private Productos producto;
    private String motivo;

    public DetallesAjustes() {
    }

    public DetallesAjustes(int id_detalleajuste, Ajustes ajuste, int cantidad_ajuste, Productos producto, String motivo) {
        this.id_detalleajuste = id_detalleajuste;
        this.ajuste = ajuste;
        this.cantidad_ajuste = cantidad_ajuste;
        this.producto = producto;
        this.motivo = motivo;
    }

    public int getId_detalleajuste() {
        return id_detalleajuste;
    }

    public void setId_detalleajuste(int id_detalleajuste) {
        this.id_detalleajuste = id_detalleajuste;
    }

    public Ajustes getAjuste() {
        return ajuste;
    }

    public void setAjuste(Ajustes ajuste) {
        this.ajuste = ajuste;
    }

    public int getCantidad_ajuste() {
        return cantidad_ajuste;
    }

    public void setCantidad_ajuste(int cantidad_ajuste) {
        this.cantidad_ajuste = cantidad_ajuste;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
}
