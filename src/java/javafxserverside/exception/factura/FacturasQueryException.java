package javafxserverside.exception.factura;

/**
 * Manage query exceptions for Facturas
 * @author Carlos
 */
public class FacturasQueryException extends Exception {

    /**
     * Creates a new instance of <code>QueryException</code> without detail
     * message.
     */
    public FacturasQueryException() {
        super("Ha ocurrido un error al realizar la consulta.");
    }

    /**
     * Constructs an instance of <code>QueryException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FacturasQueryException(String msg) {
        super(msg);
    }
}
