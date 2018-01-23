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
public class PiezaUpdateException extends Exception {

    /**
     * Creates a new instance of <code>PiezaUpdateException</code> without
     * detail message.
     */
    public PiezaUpdateException() {
        super("Ha ocurrido un error al intentar modificar la pieza.");
    }

    /**
     * Constructs an instance of <code>PiezaUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PiezaUpdateException(String msg) {
        super(msg);
    }
}
