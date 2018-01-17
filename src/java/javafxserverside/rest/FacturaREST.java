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
import javafxserverside.ejb.factura.FacturasManagerLocal;
import javafxserverside.entity.Factura;
import javafxserverside.exception.factura.FacturasCreateException;
import javafxserverside.exception.factura.FacturasDataException;
import javafxserverside.exception.factura.FacturasDeleteException;
import javafxserverside.exception.factura.FacturasQueryException;
import javafxserverside.exception.factura.FacturasUpdateException;
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

/**
 * REST Class for Facturas
 * @author Carlos
 */
@Path("factura")
public class FacturaREST {
    private static final Logger logger = Logger.getLogger(FacturaREST.class.getName());
    
    @EJB
    private FacturasManagerLocal ejb;

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
        }catch(FacturasCreateException | FacturasDataException ex){
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
        }catch(FacturasUpdateException ex){
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
        }catch(FacturasDeleteException | FacturasQueryException ex){
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
        }catch(FacturasQueryException ex){
            logger.severe("Error finding factura.\n"+ex.getMessage());
        }
        
        if(factura!=null){
            logger.info("FacturaREST: factura with id:<"+id+"> not found.");
        }else{
            logger.info("FacturaREST: factura found.");
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
        }catch(FacturasQueryException ex){
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
//    @GET
//    @Path("factura/{fromDate}/{toDate}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Factura> findByDate(
//            @PathParam("fromDate") Date fromDate, @PathParam("toDate") Date toDate) {
//        List facturasList= new ArrayList();
//        try{
//            logger.info("FacturaREST: Finding factura by date range.");
//            facturasList=ejb.getFacturasByDate(fromDate, toDate);
//        }catch(FacturasQueryException ex){
//            logger.severe("Error finding facturas by date range.\n"+ex.getMessage());
//        }
//        
//        logger.info("FacturaREST: <"+facturasList.size()+"> records found.");
//        return facturasList;
//    }
    
//    @GET
//    @Path("/{fromDate}/{toDate}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Factura> findByDate(@PathParam("fromDate") Date fromDate, @PathParam("toDate") Date toDate) {
//        List facturasList= new ArrayList();
//        try{
//            logger.info("FacturaREST: Finding factura by date range.");
//            facturasList=ejb.getFacturasByDate(fromDate, toDate);
//        }catch(FacturasQueryException ex){
//            logger.severe("Error finding facturas by date range.\n"+ex.getMessage());
//        }
//        
//        logger.info("FacturaREST: <"+facturasList.size()+"> records found.");
//        return facturasList;
//    }
    
    /**
     * Find facturas by Cliente id.
     * @param id Client id
     * @return Facturas list associated to client.
     */
    @GET
    @Path("factura/cliente/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Factura> findByCliente(@PathParam("id") int id) {
        List facturasList= null;
        try{
            logger.info("FacturaREST: Finding factura by id cliente.");
            facturasList=ejb.getFacturasByCliente(id);
        }catch(FacturasQueryException ex){
            logger.severe("Error finding facturas by id cliente.\n"+ex.getMessage());
        }
        
        logger.info("FacturaREST: <"+facturasList.size()+"> records found.");
        return facturasList;
    }
    
//    @GET
//    @Path("{from}/{to}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Factura> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return null;
//    }
//
//    @GET
//    @Path("count")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String countREST() {
//        return null;
//    }

    
}
