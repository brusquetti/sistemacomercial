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
public class DetallesCajas {
    private int id_detallecaja;
    private Ventas venta;
    private TiposPagos pago;
    private Cajas caja;
    private int importe;
    private int vuelto;

    public DetallesCajas() {
    }

    public DetallesCajas(int id_detallecaja, Ventas venta, TiposPagos pago, Cajas caja, int importe, int vuelto) {
        this.id_detallecaja = id_detallecaja;
        this.venta = venta;
        this.pago = pago;
        this.caja = caja;
        this.importe = importe;
        this.vuelto = vuelto;
    }

    public int getId_detallecaja() {
        return id_detallecaja;
    }

    public void setId_detallecaja(int id_detallecaja) {
        this.id_detallecaja = id_detallecaja;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public TiposPagos getPago() {
        return pago;
    }

    public void setPago(TiposPagos pago) {
        this.pago = pago;
    }

    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
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
