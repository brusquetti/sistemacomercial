/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacomecial.modelos;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class Clientes {

    private int id_cliente;
    private String ruc_cliente;
    private String nombre_cliente;
    private String apellido_cliente;
    private int cedula_cliente;
    private Date fecha_nacimiento_cliente;
    private String direccion_cliente;
    private String correo_cliente;
    private Ciudades ciudad;
    private String telefono_cliente;

    public Clientes() {
    }

    public Clientes(int id_cliente, String ruc_cliente, String nombre_cliente, String apellido_cliente, int cedula_cliente, Date fecha_nacimiento_cliente, String direccion_cliente, String correo_cliente, Ciudades ciudad, String telefono_cliente) {
        this.id_cliente = id_cliente;
        this.ruc_cliente = ruc_cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.cedula_cliente = cedula_cliente;
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
        this.direccion_cliente = direccion_cliente;
        this.correo_cliente = correo_cliente;
        this.ciudad = ciudad;
        this.telefono_cliente = telefono_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getRuc_cliente() {
        return ruc_cliente;
    }

    public void setRuc_cliente(String ruc_cliente) {
        this.ruc_cliente = ruc_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public int getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(int cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public Date getFecha_nacimiento_cliente() {
        return fecha_nacimiento_cliente;
    }

    public void setFecha_nacimiento_cliente(Date fecha_nacimiento_cliente) {
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getCorreo_cliente() {
        return correo_cliente;
    }

    public void setCorreo_cliente(String correo_cliente) {
        this.correo_cliente = correo_cliente;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

}
