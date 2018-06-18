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
public class Ajustes {
    private int id_ajuste;
    private Date fecha_ajuste;
    private Usuarios usuario;

    public Ajustes() {
    }

    public Ajustes(int id_ajuste, Date fecha_ajuste, Usuarios usuario) {
        this.id_ajuste = id_ajuste;
        this.fecha_ajuste = fecha_ajuste;
        this.usuario = usuario;
    }

    public int getId_ajuste() {
        return id_ajuste;
    }

    public void setId_ajuste(int id_ajuste) {
        this.id_ajuste = id_ajuste;
    }

    public Date getFecha_ajuste() {
        return fecha_ajuste;
    }

    public void setFecha_ajuste(Date fecha_ajuste) {
        this.fecha_ajuste = fecha_ajuste;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    
}
