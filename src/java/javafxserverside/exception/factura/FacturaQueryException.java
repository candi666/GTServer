package javafxserverside.exception.factura;

/**
 * Manage query exceptions for Facturas
 * @author Carlos
 */
public class FacturaQueryException extends Exception {

    /**
     * Creates a new instance of <code>QueryException</code> without detail
     * message.
     */
    public FacturaQueryException() {
        super("Ha ocurrido un error al realizar la consulta.");
    }

    /**
     * Constructs an instance of <code>QueryException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FacturaQueryException(String msg) {
        super(msg);
    }
}
