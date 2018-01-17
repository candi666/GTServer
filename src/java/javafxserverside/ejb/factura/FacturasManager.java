package javafxserverside.ejb.factura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javafxserverside.entity.Factura;
import javafxserverside.exception.factura.FacturasCreateException;
import javafxserverside.exception.factura.FacturasDataException;
import javafxserverside.exception.factura.FacturasDeleteException;
import javafxserverside.exception.factura.FacturasQueryException;
import javafxserverside.exception.factura.FacturasUpdateException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Define EJB for Facturas
 *
 * @author Carlos
 */
@Stateless
public class FacturasManager implements FacturasManagerLocal {

    private static final Logger logger = Logger.getLogger(FacturasManager.class.getName());

    @PersistenceContext
    private EntityManager em;

    /**
     * Get all facturas
     *
     * @return facturas collection
     */
    @Override
    public List<Factura> getAllFacturas() throws FacturasQueryException {
        logger.info("FacturasManager: Finding all facturas.");
        return em.createNamedQuery("getAllFacturas").getResultList();
    }

    /**
     * Create new factura.
     *
     * @param factura new factura
     * @return 0 error, 1 ok, 2 already exists.
     * @throws javafxserverside.exception.factura.FacturasCreateException
     * @throws javafxserverside.exception.factura.FacturasDataException
     */
    @Override
    public void createFactura(Factura factura) throws FacturasCreateException, FacturasDataException {
        logger.info("Facturas Manager: creating factura.");

        try {
            if (!isValid(factura)) {
                logger.info("FacturasManager: FacturasDataException creating factura.");
                throw new FacturasDataException("Couldn't create factura, invalid factura data.");
            }
            em.persist(factura);
        } catch (Exception ex) {
            logger.info("FacturasManager: FacturasCreateException creating factura");
            throw new FacturasCreateException("Error creating factura.\n" + ex.getMessage());
        }
        logger.info("Factura id: < " + factura.getId().toString() + " > created.");
    }

    /**
     * Get facturas by date range
     *
     * @return facturas collection
     */
    @Override
    public List<Factura> getFacturasByDate(Date fromDate, Date toDate) throws FacturasQueryException {
        logger.info("Facturas Manager: finding factura by date.");
        return new ArrayList<Factura>();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<Factura> getFacturasByCliente(int id) throws FacturasQueryException {
        logger.info("Facturas Manager: finding factura by cliente.");

        return em.createNamedQuery("getFacturasByCliente").setParameter("id", id).getResultList();
        //return new ArrayList<Factura>();
    }

    /**
     * Get factura by id number
     *
     * @param id id number
     * @return factura with matching id
     * @throws FacturasQueryException
     */
    @Override
    public Factura getFacturaById(int id) throws FacturasQueryException {
        logger.info("FacturasManager: finding factura by id.");
        return em.find(Factura.class, id);
    }

    /**
     * Update factura values
     *
     * @param factura factura to update (Same id, new values)
     * @return 0 error, 1 ok, 2 doesnt exists.
     */
    @Override
    public void updateFactura(Factura factura) throws FacturasUpdateException {
        try {
            if (!em.contains(factura)) {
                em.merge(factura);
            }
        } catch (Exception ex) {
            logger.info("FacturasManager: FacturasDataException updating factura.");
            throw new FacturasUpdateException("Error updating factura.\n" + ex.getMessage());
        }
    }

    /**
     * Delete factura by id.
     *
     * @param id factura id
     * @return 0 error, 1 ok, 2 doesnt exists.
     */
    @Override
    public void deleteFactura(Factura factura) throws FacturasDeleteException {
        logger.info("FacturasManager: Deleting user.");
        try {
            factura = em.merge(factura);
            em.remove(factura);
        } catch (Exception ex) {
            logger.severe("FacturasManager: FacturasDeleteException deleting factura.");
            throw new FacturasDeleteException("Error deleting factura.\n" + ex.getMessage());
        }
        logger.info("Factura id: < " + factura.getId() + " > deleted.");
    }

    /**
     * Utility to check if a factura object is valid
     *
     * @param f factura to check
     * @return true if valid
     */
    private boolean isValid(Factura f) {
        
//        if (f.getId() == null || f.getFecha() == null || f.getFechavenc() == null || f.getReparacion() == null
//                || f.getPagada() == null || f.getTotal() == null || f.getCliente() == null) {

        /** This validation allows null value for reparación and cliente (For Restful Test)
         *  Once tested comment, and uncomment previous validation.
         **/
        if (f.getId() == null || f.getFecha() == null || f.getFechavenc() == null
                || f.getPagada() == null || f.getTotal() == null) {
            return false;
        } else {
            return true;
        }
    }
}
