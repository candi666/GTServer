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
public class ReparacionUpdateException extends Exception {

    /**
     * Creates a new instance of <code>ReparacionUpdateException</code> without
     * detail message.
     */
    public ReparacionUpdateException() {
        super("Ha ocurrido un error al intentar modificar la reparacion.");
    }

    /**
     * Constructs an instance of <code>ReparacionUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ReparacionUpdateException(String msg) {
        super(msg);
    }
}
