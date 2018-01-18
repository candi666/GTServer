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
public class FacturaDataException extends Exception {

    /**
     * Creates a new instance of <code>FacturasDataException</code> without
     * detail message.
     */
    public FacturaDataException() {
        super("Invalid factura data.");
    }

    /**
     * Constructs an instance of <code>FacturasDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FacturaDataException(String msg) {
        super(msg);
    }
}
