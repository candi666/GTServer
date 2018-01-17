/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.exception.cliente;

/**
 *
 * @author 2dam
 */
public class ClienteQueryException extends Exception {

    /**
     * Creates a new instance of <code>ClienteQueryException</code> without detail
     * message.
     */
    public ClienteQueryException() {
    }

    /**
     * Constructs an instance of <code>ClienteQueryException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ClienteQueryException(String msg) {
        super(msg);
    }
}
