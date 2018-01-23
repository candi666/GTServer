/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.exception.pieza;

import javafxserverside.exception.pieza.*;

/**
 *
 * @author Carlos
 */
public class PiezaCreateException extends Exception {

    /**
     * Creates a new instance of <code>PiezaCreateException</code> without
     * detail message.
     */
    public PiezaCreateException() {
        super("Ha ocurrido un error al intentar crear la pieza.");
    }

    /**
     * Constructs an instance of <code>PiezaCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PiezaCreateException(String msg) {
        super(msg);
    }
}
