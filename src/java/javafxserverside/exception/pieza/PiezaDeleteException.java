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
public class PiezaDeleteException extends Exception {

    /**
     * Creates a new instance of <code>PiezaDeleteException</code> without
     * detail message.
     */
    public PiezaDeleteException() {
        super("Ha ocurrido un error al intentar eliminar la pieza.");
    }

    /**
     * Constructs an instance of <code>PiezaDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PiezaDeleteException(String msg) {
        super(msg);
    }
}
