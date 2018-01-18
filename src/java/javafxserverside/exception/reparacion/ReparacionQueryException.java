package javafxserverside.exception.reparacion;

import javafxserverside.exception.reparacion.*;

/**
 * Manage query exceptions for Reparacion
 * @author Carlos
 */
public class ReparacionQueryException extends Exception {

    /**
     * Creates a new instance of <code>QueryException</code> without detail
     * message.
     */
    public ReparacionQueryException() {
        super("Ha ocurrido un error al realizar la consulta.");
    }

    /**
     * Constructs an instance of <code>QueryException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ReparacionQueryException(String msg) {
        super(msg);
    }
}
