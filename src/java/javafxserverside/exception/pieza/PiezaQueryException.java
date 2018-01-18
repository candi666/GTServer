package javafxserverside.exception.pieza;

import javafxserverside.exception.pieza.*;

/**
 * Manage query exceptions for Pieza
 * @author Carlos
 */
public class PiezaQueryException extends Exception {

    /**
     * Creates a new instance of <code>QueryException</code> without detail
     * message.
     */
    public PiezaQueryException() {
        super("Ha ocurrido un error al realizar la consulta.");
    }

    /**
     * Constructs an instance of <code>QueryException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PiezaQueryException(String msg) {
        super(msg);
    }
}
