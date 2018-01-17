/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxserverside.ejb.cliente.ClienteManagerLocal;
import javafxserverside.entity.Cliente;
import javafxserverside.exception.cliente.ClienteQueryException;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("cliente")
public class ClienteREST {
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    @EJB
    private ClienteManagerLocal ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cliente cliente) {
        try{
            LOGGER.log(Level.INFO,"ClienteREST: create {0}.",cliente);
            ejb.createClient(cliente);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(Cliente cliente) {
        try{
            LOGGER.log(Level.INFO,"ClienteREST: update {0}.",cliente);
            ejb.updateClient(cliente);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        try{
            LOGGER.log(Level.INFO,"ClienteREST: buscar por id");
            Cliente cliente=ejb.getClientesById(id);
            LOGGER.log(Level.INFO,"ClienteREST: delete {0}.",id);
            ejb.deleteClient(cliente);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cliente find(@PathParam("id") Integer id) {
        Cliente cliente=null;
        try{
            LOGGER.log(Level.INFO,"ClienteREST: find {0}.",id);
            cliente=ejb.getClientesById(id);
        }catch(ClienteQueryException e){
            LOGGER.log(Level.SEVERE, null, e);
        }
        return cliente;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findAll() {
        List <Cliente> clientes=null;
        try{
            LOGGER.log(Level.INFO,"ClienteREST: find all clientes");
            clientes=ejb.getAllClientes();
        }catch(ClienteQueryException e){
            LOGGER.log(Level.SEVERE, null, e);
        }
        return clientes;
    }
    
    @GET
    @Path("nombre/{nombre}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findByName(@PathParam("nombre") String nombre) {
        List <Cliente> clientes=null;
        try{
            LOGGER.log(Level.INFO,"ClienteREST: find clientes by name");
            clientes=ejb.getClientesByNombre(nombre);
        }catch(ClienteQueryException e){
            LOGGER.log(Level.SEVERE, null, e);
        }
        return clientes;
    }
    
    @GET
    @Path("dni/{dni}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findByDni(@PathParam("dni") String dni) {
        List <Cliente> clientes=null;
        try{
            LOGGER.log(Level.INFO,"ClienteREST: find clientes by dni");
            clientes=ejb.getClientesByDni(dni);
        }catch(ClienteQueryException e){
            LOGGER.log(Level.SEVERE, null, e);
        }
        return clientes;
    }
    
}