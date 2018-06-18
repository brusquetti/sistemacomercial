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
public class Cobros {
    private int id_cobro;
    private int id_venta;

    public Cobros() {
    }

    public Cobros(int id_cobro, int id_venta) {
        this.id_cobro = id_cobro;
        this.id_venta = id_venta;
    }

    public int getId_cobro() {
        return id_cobro;
    }

    public void setId_cobro(int id_cobro) {
        this.id_cobro = id_cobro;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    
    
}
