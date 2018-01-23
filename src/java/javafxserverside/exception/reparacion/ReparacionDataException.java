/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.exception.reparacion;

import javafxserverside.exception.reparacion.*;

/**
 *
 * @author Carlos
 */
public class ReparacionDataException extends Exception {

    /**
     * Creates a new instance of <code>ReparacionDataException</code> without
     * detail message.
     */
    public ReparacionDataException() {
        super("Invalid reparacion data.");
    }

    /**
     * Constructs an instance of <code>ReparacionDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ReparacionDataException(String msg) {
        super(msg);
    }
}
