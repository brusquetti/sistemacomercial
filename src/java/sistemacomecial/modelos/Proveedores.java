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
public class Proveedores {
    private int id_proveedor;
    private String ruc_proveedor;
    private String nombre_proveedor;
    private String correo_proveedor;
    private String direccion_proveedor;
    private Ciudades ciudad;
    private String telefono_proveedor;
    private String telefono2_proveedor;

    public Proveedores() {
    }

    public Proveedores(int id_proveedor, String ruc_proveedor, String nombre_proveedor, String correo_proveedor, String direccion_proveedor, Ciudades ciudad, String telefono_proveedor, String telefono2_proveedor) {
        this.id_proveedor = id_proveedor;
        this.ruc_proveedor = ruc_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.correo_proveedor = correo_proveedor;
        this.direccion_proveedor = direccion_proveedor;
        this.ciudad = ciudad;
        this.telefono_proveedor = telefono_proveedor;
        this.telefono2_proveedor = telefono2_proveedor;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getRuc_proveedor() {
        return ruc_proveedor;
    }

    public void setRuc_proveedor(String ruc_proveedor) {
        this.ruc_proveedor = ruc_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getCorreo_proveedor() {
        return correo_proveedor;
    }

    public void setCorreo_proveedor(String correo_proveedor) {
        this.correo_proveedor = correo_proveedor;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public void setTelefono_proveedor(String telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }

    public String getTelefono2_proveedor() {
        return telefono2_proveedor;
    }

    public void setTelefono2_proveedor(String telefono2_proveedor) {
        this.telefono2_proveedor = telefono2_proveedor;
    }
    
}
