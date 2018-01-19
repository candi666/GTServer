package javafxserverside.ejb.pieza;

import javafxserverside.ejb.pieza.*;
import java.util.List;
import java.util.logging.Logger;
import javafxserverside.entity.Pieza;
import javafxserverside.exception.pieza.PiezaCreateException;
import javafxserverside.exception.pieza.PiezaDataException;
import javafxserverside.exception.pieza.PiezaDeleteException;
import javafxserverside.exception.pieza.PiezaQueryException;
import javafxserverside.exception.pieza.PiezaUpdateException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Define EJB for Pieza
 *
 * @author Carlos
 */
@Stateless
public class PiezaManager implements PiezaManagerLocal {

    private static final Logger logger = Logger.getLogger(PiezaManager.class.getName());

    @PersistenceContext
    private EntityManager em;

    /**
     * Get all piezas
     *
     * @return piezas collection
     */
    @Override
    public List<Pieza> getAllPiezas() throws PiezaQueryException {
        logger.info("PiezaManager: Finding all piezas.");
        return em.createNamedQuery("getAllPiezas").getResultList();
    }

    /**
     * Create new pieza.
     *
     * @param pieza new pieza
     * @throws javafxserverside.exception.pieza.PiezaCreateException
     * @throws javafxserverside.exception.pieza.PiezaDataException
     */
    @Override
    public void createPieza(Pieza pieza) throws PiezaCreateException, PiezaDataException {
        logger.info("Pieza Manager: creating pieza.");

        try {
            if (!isValid(pieza)) {
                logger.info("PiezaManager: PiezaDataException creating pieza.");
                throw new PiezaDataException("Couldn't create pieza, invalid pieza data.");
            }
            em.persist(pieza);
        } catch (Exception ex) {
            logger.info("PiezaManager: PiezaCreateException creating pieza");
            throw new PiezaCreateException("Error creating pieza.\n" + ex.getMessage());
        }
        logger.info("Pieza id: < " + pieza.getId().toString() + " > created.");
    }

    /**
     * Get pieza by id number
     *
     * @param id id number
     * @return pieza with matching id
     * @throws PiezaQueryException
     */
    @Override
    public Pieza getPiezaById(int id) throws PiezaQueryException {
        logger.info("PiezaManager: finding pieza by id.");
        return em.find(Pieza.class, id);
    }

    /**
     * Update pieza values
     *
     * @param pieza pieza to update (Same id, new values)
     * @throws PiezaUpdateException
     */
    @Override
    public void updatePieza(Pieza pieza) throws PiezaUpdateException {
        try {
            if (!em.contains(pieza)&& isValid(pieza)) {
                em.merge(pieza);
            }
        } catch (Exception ex) {
            logger.info("PiezaManager: PiezaDataException updating pieza.");
            throw new PiezaUpdateException("Error updating pieza.\n" + ex.getMessage());
        }
    }

    /**
     * Delete pieza by id.
     *
     * @param id pieza id
     */
    @Override
    public void deletePieza(Pieza pieza) throws PiezaDeleteException {
        logger.info("PiezaManager: Deleting user.");
        try {
            pieza = em.merge(pieza);
            em.remove(pieza);
        } catch (Exception ex) {
            logger.severe("PiezaManager: PiezaDeleteException deleting pieza.");
            throw new PiezaDeleteException("Error deleting pieza.\n" + ex.getMessage());
        }
        logger.info("Pieza id: < " + pieza.getId() + " > deleted.");
    }

    /**
     * Utility to check if pieza data is valid
     *
     * @param f pieza to check
     * @return true if valid
     */
    private boolean isValid(Pieza f) {
        
        /** This validation allows null value for cliente (For Restful Test)
         *  
         **/
        if (f.getId() == null || f.getDescripcion() == null || f.getFabricante() == null) {
            return false;
        } else {
            return true;
        }
    }
}
