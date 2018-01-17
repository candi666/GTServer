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
public class FacturasDeleteException extends Exception {

    /**
     * Creates a new instance of <code>FacturasDeleteException</code> without
     * detail message.
     */
    public FacturasDeleteException() {
        super("Ha ocurrido un error al intentar eliminar la factura.");
    }

    /**
     * Constructs an instance of <code>FacturasDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FacturasDeleteException(String msg) {
        super(msg);
    }
}
