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
public class ReparacionDeleteException extends Exception {

    /**
     * Creates a new instance of <code>ReparacionDeleteException</code> without
     * detail message.
     */
    public ReparacionDeleteException() {
        super("Ha ocurrido un error al intentar eliminar la reparacion.");
    }

    /**
     * Constructs an instance of <code>ReparacionDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ReparacionDeleteException(String msg) {
        super(msg);
    }
}
