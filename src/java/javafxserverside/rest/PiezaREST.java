/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.rest;

import java.util.List;
import java.util.logging.Logger;
import javafxserverside.ejb.pieza.PiezaManagerLocal;
import javafxserverside.entity.Pieza;
import javafxserverside.exception.pieza.PiezaCreateException;
import javafxserverside.exception.pieza.PiezaDataException;
import javafxserverside.exception.pieza.PiezaDeleteException;
import javafxserverside.exception.pieza.PiezaQueryException;
import javafxserverside.exception.pieza.PiezaUpdateException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
 * REST Class for Piezas
 *
 * @author Carlos
 */
@Path("pieza")
public class PiezaREST {

    private static final Logger logger = Logger.getLogger(PiezaREST.class.getName());

    @EJB
    private PiezaManagerLocal ejb;

    /**
     * Creates pieza
     *
     * @param pieza pieza to create.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Pieza pieza) {
        try {
            logger.info("PiezaREST: creating pieza.");
            ejb.createPieza(pieza);
        } catch (PiezaCreateException | PiezaDataException ex) {
            logger.severe("Error creating pieza.\n" + ex.getMessage());
        }
        //logger.info("Pieza id: < "+pieza.getId().toString() + " > created.");
    }

    /**
     * Updates pieza
     *
     * @param pieza pieza to update
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(Pieza pieza) {
        try {
            logger.info("PiezaREST: updating pieza.");
            ejb.updatePieza(pieza);
        } catch (PiezaUpdateException ex) {
            logger.severe("Error updating pieza.\n" + ex.getMessage());
        }
        logger.info("Pieza id: < " + pieza.getId().toString() + " > updated.");
    }

    /**
     * Delete pieza by id
     *
     * @param id pieza to delete id
     */
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        try {
            logger.info("PiezaREST: deleting pieza.");
            Pieza pieza = ejb.getPiezaById(id);

            ejb.deletePieza(pieza);
        } catch (PiezaDeleteException | PiezaQueryException ex) {
            logger.severe("Error deleting pieza.\n" + ex.getMessage());
        }
        logger.info("Pieza id: < " + id + " > deleted.");
    }

    /**
     * Find pieza by id.
     *
     * @param id pieza id
     * @return pieza matching the given id.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Pieza find(@PathParam("id") Integer id) {
        Pieza pieza = null;
        try {
            logger.info("PiezaREST: Finding pieza by id.");
            pieza = ejb.getPiezaById(id);
        } catch (PiezaQueryException ex) {
            logger.severe("Error finding pieza.\n" + ex.getMessage());
        }

        if (pieza != null) {
            logger.info("PiezaREST: pieza with id:<" + id + "> not found.");
        } else {
            logger.info("PiezaREST: pieza found.");
        }

        return pieza;
    }

    /**
     * Finds all piezaes.
     *
     * @return complete piezaes list
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pieza> findAll() {
        List piezaesList = null;
        try {
            logger.info("PiezaREST: Finding all piezaes.");
            piezaesList = ejb.getAllPiezas();
        } catch (PiezaQueryException ex) {
            logger.severe("Error finding piezaes.\n" + ex.getMessage());
        }

        logger.info("PiezaREST: <" + piezaesList.size() + "> records found.");
        return piezaesList;
    }

}
