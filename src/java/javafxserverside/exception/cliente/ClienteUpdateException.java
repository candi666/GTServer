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
public class ClienteUpdateException extends Exception {

    /**
     * Creates a new instance of <code>ClienteUpdateException</code> without
     * detail message.
     */
    public ClienteUpdateException() {
    }

    /**
     * Constructs an instance of <code>ClienteUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ClienteUpdateException(String msg) {
        super(msg);
    }
}