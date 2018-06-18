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
public class Cuentas {
    private int id_cuenta;
    private Date fecha_apertura;
    private String estado_cuenta;
    private Usuarios usuario;

    public Cuentas() {
    }

    public Cuentas(int id_cuenta, Date fecha_apertura, String estado_cuenta, Usuarios usuario) {
        this.id_cuenta = id_cuenta;
        this.fecha_apertura = fecha_apertura;
        this.estado_cuenta = estado_cuenta;
        this.usuario = usuario;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(Date fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public String getEstado_cuenta() {
        return estado_cuenta;
    }

    public void setEstado_cuenta(String estado_cuenta) {
        this.estado_cuenta = estado_cuenta;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    
}
