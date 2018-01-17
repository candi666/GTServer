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
public class ClienteCreateException extends Exception {

    /**
     * Creates a new instance of <code>ClienteCreateException</code> without
     * detail message.
     */
    public ClienteCreateException() {
    }

    /**
     * Constructs an instance of <code>ClienteCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ClienteCreateException(String msg) {
        super(msg);
    }
}
