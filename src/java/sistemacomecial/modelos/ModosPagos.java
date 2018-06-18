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
public class ModosPagos {
     private int id_modopago;
    private String nombre_modopago;

    public ModosPagos() {
    }

    public ModosPagos(int id_modopago, String nombre_modopago) {
        this.id_modopago = id_modopago;
        this.nombre_modopago = nombre_modopago;
    }

    public int getId_modopago() {
        return id_modopago;
    }

    public void setId_modopago(int id_modopago) {
        this.id_modopago = id_modopago;
    }

    public String getNombre_modopago() {
        return nombre_modopago;
    }

    public void setNombre_modopago(String nombre_modopago) {
        this.nombre_modopago = nombre_modopago;
    }
    
}
