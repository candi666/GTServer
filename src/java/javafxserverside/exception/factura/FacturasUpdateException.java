/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.exception.factura;

/**
 *
 * @author Carlos
 */
public class FacturasUpdateException extends Exception {

    /**
     * Creates a new instance of <code>FacturasUpdateException</code> without
     * detail message.
     */
    public FacturasUpdateException() {
        super("Ha ocurrido un error al intentar modificar la factura.");
    }

    /**
     * Constructs an instance of <code>FacturasUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FacturasUpdateException(String msg) {
        super(msg);
    }
}
