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
public class DetallesCuentas {
    private int id_detallecuenta;
    private Compras compra;
    private ModosPagos modo;
    private Cuentas cuenta;
    private Usuarios usuario;
    private int importe;
    private int vuelto;

    public DetallesCuentas() {
    }

    public DetallesCuentas(int id_detallecuenta, Compras compra, ModosPagos modo, Cuentas cuenta, Usuarios usuario, int importe, int vuelto) {
        this.id_detallecuenta = id_detallecuenta;
        this.compra = compra;
        this.modo = modo;
        this.cuenta = cuenta;
        this.usuario = usuario;
        this.importe = importe;
        this.vuelto = vuelto;
    }

    public int getId_detallecuenta() {
        return id_detallecuenta;
    }

    public void setId_detallecuenta(int id_detallecuenta) {
        this.id_detallecuenta = id_detallecuenta;
    }

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public ModosPagos getModo() {
        return modo;
    }

    public void setModo(ModosPagos modo) {
        this.modo = modo;
    }

    public Cuentas getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getVuelto() {
        return vuelto;
    }

    public void setVuelto(int vuelto) {
        this.vuelto = vuelto;
    }

}
