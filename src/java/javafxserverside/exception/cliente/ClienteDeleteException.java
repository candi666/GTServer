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
public class ClienteDeleteException extends Exception {

    /**
     * Creates a new instance of <code>ClienteDeleteException</code> without
     * detail message.
     */
    public ClienteDeleteException() {
    }

    /**
     * Constructs an instance of <code>ClienteDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ClienteDeleteException(String msg) {
        super(msg);
    }
}
