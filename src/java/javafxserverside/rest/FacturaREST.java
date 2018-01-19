/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javafxserverside.entity.Factura;
import javafxserverside.exception.factura.FacturaCreateException;
import javafxserverside.exception.factura.FacturaDataException;
import javafxserverside.exception.factura.FacturaDeleteException;
import javafxserverside.exception.factura.FacturaQueryException;
import javafxserverside.exception.factura.FacturaUpdateException;
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
import javafxserverside.ejb.factura.FacturaManagerLocal;

/**
 * REST Class for Facturas
 * @author Carlos
 */
@Path("factura")
public class FacturaREST {
    private static final Logger logger = Logger.getLogger(FacturaREST.class.getName());
    
    @EJB
    private FacturaManagerLocal ejb;

    /**
     * Creates factura
     * @param factura factura to create.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Factura factura){
        try{
            logger.info("FacturaREST: creating factura.");
            ejb.createFactura(factura);
        }catch(FacturaCreateException | FacturaDataException ex){
            logger.severe("Error creating factura.\n"+ex.getMessage());
        }
        //logger.info("Factura id: < "+factura.getId().toString() + " > created.");
    }

    /**
     * Updates factura
     * @param factura factura to update
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(Factura factura){
        try{
            logger.info("FacturaREST: updating factura.");
            ejb.updateFactura(factura);
        }catch(FacturaUpdateException ex){
            logger.severe("Error updating factura.\n"+ex.getMessage());
        }
        logger.info("Factura id: < "+factura.getId().toString() + " > updated.");
    }

    /**
     * Delete factura by id
     * @param id factura to delete id
     */
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        try{
            logger.info("FacturaREST: deleting factura.");
            Factura factura = ejb.getFacturaById(id);
            
            ejb.deleteFactura(factura);
        }catch(FacturaDeleteException | FacturaQueryException ex){
            logger.severe("Error deleting factura.\n"+ex.getMessage());
        }
        logger.info("Factura id: < "+ id + " > deleted.");
    }

    /**
     * Find factura by id.
     * @param id factura id
     * @return factura matching the given id.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Factura find(@PathParam("id") Integer id) {
        Factura factura=null;
        try{
            logger.info("FacturaREST: Finding factura by id.");
            factura=ejb.getFacturaById(id);
        }catch(FacturaQueryException ex){
            logger.severe("Error finding factura.\n"+ex.getMessage());
        }
        
        if(factura!=null){
            logger.info("FacturaREST: factura id:<"+id+"> not found.");
        }else{
            logger.info("FacturaREST: factura id:<"+id+"> found.");
        }
        
        return factura;
    }

    /**
     * Finds all facturas.
     * @return complete facturas list
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Factura> findAll() {
        List facturasList= null;
        try{
            logger.info("FacturaREST: Finding all facturas.");
            facturasList=ejb.getAllFacturas();
        }catch(FacturaQueryException ex){
            logger.severe("Error finding facturas.\n"+ex.getMessage());
        }
        
        logger.info("FacturaREST: <"+facturasList.size()+"> records found.");
        return facturasList;
    }

    /**
     * Find facturas between a given date range.
     * @param fromDate from Date.
     * @param toDate to Date.
     * @return Facturas list matching the date range.
     */
    @GET
    @Path("rango/{fromDate}/{toDate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Factura> findByDate(
            @PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
        List facturasList= new ArrayList();
        try{
            logger.info("FacturaREST: Finding factura by date range.");
            facturasList=ejb.getFacturasByDate(fromDate, toDate);
        }catch(FacturaQueryException ex){
            logger.severe("Error finding facturas by date range.\n"+ex.getMessage());
        }
        
        logger.info("FacturaREST: <"+facturasList.size()+"> records found.");
        return facturasList;
    }
    
    /**
     * Find facturas by Cliente id.
     * @param id Client id
     * @return Facturas list associated to client.
     */
    @GET
    @Path("cliente/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Factura> findByCliente(@PathParam("id") int id) {
        List facturasList= null;
        try{
            logger.info("FacturaREST: Finding factura by id cliente.");
            facturasList=ejb.getFacturasByCliente(id);
        }catch(FacturaQueryException ex){
            logger.severe("Error finding facturas by id cliente.\n"+ex.getMessage());
        }
        
        logger.info("FacturaREST: <"+facturasList.size()+"> records found.");
        return facturasList;
    }
    
    /**
     * Find facturas by Reparacion id.
     * @param id Reparacion id
     * @return Facturas list associated to reparacion.
     */
    @GET
    @Path("reparacion/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Factura findByReparacion(@PathParam("id") int id) {
        Factura factura= null;
        try{
            logger.info("FacturaREST: Finding factura by id reparacion.");
            factura=ejb.getFacturasByReparacion(id);
        }catch(FacturaQueryException ex){
            logger.severe("Error finding facturas by id reparacion.\n"+ex.getMessage());
        }
        
        logger.info("FacturaREST: Factura id <"+factura.getId()+">, reparacion <"+factura.getReparacion().getId()+"> found.");
        return factura;
    }
    
    /**
     * Find facturas by Reparacion id.
     * @param id Reparacion id
     * @return Facturas list associated to reparacion.
     */
    @GET
    @Path("pagada/{status}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Factura> findByPagada(@PathParam("status") boolean status) {
        List facturasList= null;
        try{
            logger.info("FacturaREST: Finding factura by id reparacion.");
            facturasList=ejb.getFacturasByPagada(status);
        }catch(FacturaQueryException ex){
            logger.severe("Error finding facturas by id reparacion.\n"+ex.getMessage());
        }
        
        logger.info("FacturaREST: <"+facturasList.size()+"> records found.");
        return facturasList;
    }

    
}
