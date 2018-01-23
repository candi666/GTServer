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
public class ReparacionCreateException extends Exception {

    /**
     * Creates a new instance of <code>ReparacionCreateException</code> without
     * detail message.
     */
    public ReparacionCreateException() {
        super("Ha ocurrido un error al intentar crear la reparacion.");
    }

    /**
     * Constructs an instance of <code>ReparacionCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ReparacionCreateException(String msg) {
        super(msg);
    }
}
