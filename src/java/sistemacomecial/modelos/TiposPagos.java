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
public class TiposPagos {
     private int id_tipopago;
    private String nombre_tipopago;

    public TiposPagos() {
    }

    public TiposPagos(int id_tipopago, String nombre_tipopago) {
        this.id_tipopago = id_tipopago;
        this.nombre_tipopago = nombre_tipopago;
    }

    public int getId_tipopago() {
        return id_tipopago;
    }

    public void setId_tipopago(int id_tipopago) {
        this.id_tipopago = id_tipopago;
    }

    public String getNombre_tipopago() {
        return nombre_tipopago;
    }

    public void setNombre_tipopago(String nombre_tipopago) {
        this.nombre_tipopago = nombre_tipopago;
    }
    
    
}
