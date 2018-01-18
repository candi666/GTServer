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
public class PiezaDataException extends Exception {

    /**
     * Creates a new instance of <code>PiezaDataException</code> without
     * detail message.
     */
    public PiezaDataException() {
        super("Invalid pieza data.");
    }

    /**
     * Constructs an instance of <code>PiezaDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PiezaDataException(String msg) {
        super(msg);
    }
}
