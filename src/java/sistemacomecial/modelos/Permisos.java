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
public class Permisos {
   private int id_permiso;
   private Roles rol;
   private Formularios formulario;
   private String orden_permiso;

    public Permisos() {
    }

    public Permisos(int id_permiso, Roles rol, Formularios formulario, String orden_permiso) {
        this.id_permiso = id_permiso;
        this.rol = rol;
        this.formulario = formulario;
        this.orden_permiso = orden_permiso;
    }

    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Formularios getFormulario() {
        return formulario;
    }

    public void setFormulario(Formularios formulario) {
        this.formulario = formulario;
    }

    public String getOrden_permiso() {
        return orden_permiso;
    }

    public void setOrden_permiso(String orden_permiso) {
        this.orden_permiso = orden_permiso;
    }
   
}
