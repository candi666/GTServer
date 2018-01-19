package javafxserverside.rest;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javafxserverside.ejb.reparacion.ReparacionManagerLocal;
import javafxserverside.entity.Reparacion;
import javafxserverside.exception.reparacion.ReparacionCreateException;
import javafxserverside.exception.reparacion.ReparacionDataException;
import javafxserverside.exception.reparacion.ReparacionDeleteException;
import javafxserverside.exception.reparacion.ReparacionQueryException;
import javafxserverside.exception.reparacion.ReparacionUpdateException;
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
import javax.xml.bind.annotation.XmlElement;

/**
 * REST Class for Reparaciones
 * @author Carlos
 */
@Path("reparacion")
public class ReparacionREST {
    private static final Logger logger = Logger.getLogger(ReparacionREST.class.getName());
    
    @EJB
    private ReparacionManagerLocal ejb;

    /**
     * Creates reparacion
     * @param reparacion reparacion to create.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Reparacion reparacion){
        try{
            logger.info("ReparacionREST: creating reparacion.");
            ejb.createReparacion(reparacion);
        }catch(ReparacionCreateException | ReparacionDataException ex){
            logger.severe("Error creating reparacion.\n"+ex.getMessage());
        }
        //logger.info("Reparacion id: < "+reparacion.getId().toString() + " > created.");
    }

    /**
     * Updates reparacion
     * @param reparacion reparacion to update
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(Reparacion reparacion){
        try{
            logger.info("ReparacionREST: updating reparacion.");
            ejb.updateReparacion(reparacion);
        }catch(ReparacionUpdateException ex){
            logger.severe("Error updating reparacion.\n"+ex.getMessage());
        }
        logger.info("Reparacion id: < "+reparacion.getId().toString() + " > updated.");
    }

    /**
     * Delete reparacion by id
     * @param id reparacion to delete id
     */
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        try{
            logger.info("ReparacionREST: deleting reparacion.");
            Reparacion reparacion = ejb.getReparacionById(id);
            
            ejb.deleteReparacion(reparacion);
        }catch(ReparacionDeleteException | ReparacionQueryException ex){
            logger.severe("Error deleting reparacion.\n"+ex.getMessage());
        }
        logger.info("Reparacion id: < "+ id + " > deleted.");
    }

    /**
     * Find reparacion by id.
     * @param id reparacion id
     * @return reparacion matching the given id.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Reparacion find(@PathParam("id") Integer id) {
        Reparacion reparacion=null;
        try{
            logger.info("ReparacionREST: Finding reparacion by id.");
            reparacion=ejb.getReparacionById(id);
        }catch(ReparacionQueryException ex){
            logger.severe("Error finding reparacion.\n"+ex.getMessage());
        }
        
        if(reparacion!=null){
            logger.info("ReparacionREST: reparacion id:<"+id+"> not found.");
        }else{
            logger.info("ReparacionREST: reparacion id:<"+id+"> found.");
        }
        
        return reparacion;
    }

    /**
     * Finds all reparaciones.
     * @return complete reparaciones list
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @XmlElement(name="reparaciones", type=Reparacion.class)
    public List<Reparacion> findAll() {
        List reparacionesList= null;
        try{
            logger.info("ReparacionREST: Finding all reparaciones.");
            reparacionesList=ejb.getAllReparaciones();
        }catch(ReparacionQueryException ex){
            logger.severe("Error finding reparaciones.\n"+ex.getMessage());
        }
        
        logger.info("ReparacionREST: <"+reparacionesList.size()+"> records found.");
        return reparacionesList;
    }

    /**
     * Find reparaciones by Cliente id.
     * @param id Client id
     * @return Reparaciones list associated to client.
     */
    @GET
    @Path("cliente/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Reparacion> findByCliente(@PathParam("id") int id) {
        List reparacionesList= null;
        try{
            logger.info("ReparacionREST: Finding reparacion by id cliente.");
            reparacionesList=ejb.getReparacionesByCliente(id);
        }catch(ReparacionQueryException ex){
            logger.severe("Error finding reparaciones by id cliente.\n"+ex.getMessage());
        }
        
        logger.info("ReparacionREST: <"+reparacionesList.size()+"> records found.");
        return reparacionesList;
    }
    
    
    /**
     * Find reparaciones between a given date range.
     * @param fromDate from Date.
     * @param toDate to Date.
     * @return Facturas list matching the date range.
     */
    @GET
    @Path("rango/{fromDate}/{toDate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Reparacion> findByDate(
            @PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
        List reparacionesList= null;
        try{
            logger.info("FacturaREST: Finding reparacion by date range.");
            reparacionesList=ejb.getReparacionesByDate(fromDate, toDate);
        }catch(ReparacionQueryException ex){
            logger.severe("Error finding reparaciones by date range.\n"+ex.getMessage());
        }
        
        logger.info("FacturaREST: <"+reparacionesList.size()+"> records found.");
        return reparacionesList;
    }
    
}
