package javafxserverside.ejb.factura;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javafxserverside.entity.Factura;
import javafxserverside.entity.Reparacion;
import javafxserverside.exception.factura.FacturaCreateException;
import javafxserverside.exception.factura.FacturaDataException;
import javafxserverside.exception.factura.FacturaDeleteException;
import javafxserverside.exception.factura.FacturaQueryException;
import javafxserverside.exception.factura.FacturaUpdateException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Define EJB for Facturas
 *
 * @author Carlos
 */
@Stateless
public class FacturaManager implements FacturaManagerLocal {

    private static final Logger logger = Logger.getLogger(FacturaManager.class.getName());

    @PersistenceContext
    private EntityManager em;

    /**
     * Get all facturas
     *
     * @return facturas collection
     */
    @Override
    public List<Factura> getAllFacturas() throws FacturaQueryException {
        logger.info("FacturasManager: Finding all facturas.");
        return em.createNamedQuery("getAllFacturas").getResultList();
    }

    /**
     * Create new factura.
     *
     * @param factura new factura
     * @throws javafxserverside.exception.factura.FacturaCreateException
     * @throws javafxserverside.exception.factura.FacturaDataException
     */
    @Override
    public void createFactura(Factura factura) throws FacturaCreateException, FacturaDataException {
        logger.info("Facturas Manager: creating factura.");

        try {
            if (!isValid(factura)) {
                logger.info("FacturasManager: FacturasDataException creating factura.");
                throw new FacturaDataException("Couldn't create factura, invalid factura data.");
            }
                Factura newFactura = new Factura();
                newFactura.setFecha(factura.getFecha());
                newFactura.setFechavenc(factura.getFechavenc());
                newFactura.setReparacion(factura.getReparacion());
                newFactura.setCliente(factura.getCliente());
                newFactura.setTotal(factura.getTotal());
                newFactura.setPagada(factura.getPagada());
                em.persist(newFactura);
            
            
        } catch (Exception ex) {
            logger.info("FacturasManager: FacturasCreateException creating factura");
            throw new FacturaCreateException("Error creating factura.\n" + ex.getMessage());
        }
        logger.info("Factura id: < " + factura.getId() + " > created.");
    }

    /**
     * Get facturas by date range
     *
     * @return facturas collection
     */
    @Override
    public List<Factura> getFacturasByDate(String fromDate, String toDate) throws FacturaQueryException {
        logger.info("Facturas Manager: finding factura by date.");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fd = null;
        Date td = null;
        
        try {

            fd = formatter.parse(fromDate);
            td = formatter.parse(toDate);

        } catch (ParseException e) {
            logger.info("Facturas Manager: invalid date format.(Correct format: yyyy-MM-dd)");
            e.printStackTrace();
        }

        return em.createNamedQuery("getFacturasByDate").setParameter("fromdate", new java.sql.Date(fd.getTime())).setParameter("todate", new java.sql.Date(td.getTime())).getResultList();
    }

    /**
     * Get Facturas by associated Cliente.
     * @param id
     * @return Factura List
     * @throws FacturaQueryException
     */
    @Override
    public List<Factura> getFacturasByCliente(int id) throws FacturaQueryException {
        logger.info("Facturas Manager: finding factura by cliente.");

        return em.createNamedQuery("getFacturasByCliente").setParameter("id", id).getResultList();
        //return new ArrayList<Factura>();
    }
    
    /**
     * Get Facturas by associated Cliente.
     * @param id
     * @return Factura List
     * @throws FacturaQueryException
     */
    @Override
    public List<Factura> getFacturasByPagada(boolean status) throws FacturaQueryException {
        logger.info("Facturas Manager: finding factura by pagada.");

        return em.createNamedQuery("getFacturasByPagada").setParameter("status", status).getResultList();
        //return new ArrayList<Factura>();
    }
    
    /**
     * Get Facturas by associated Reparacion.
     * @param id
     * @return Factura List
     * @throws FacturaQueryException
     */
    @Override
    public Factura getFacturasByReparacion(int id) throws FacturaQueryException {
        logger.info("Facturas Manager: finding factura by reparacion.");
        
        /**
         * For Testing mockdata (facturas filled with mockdata, one reparacion might be associated to many facturas).
         * This will work also when the data is right. (One reparation only associated with one factura)
         **/
        return (Factura)em.createNamedQuery("getFacturaByReparacion").setParameter("id", id).getResultList().get(0);
        
        // Only works when the data is 1:1
        //return (Factura)em.createNamedQuery("getFacturaByReparacion").setParameter("id", id).getSingleResult();

    }

    /**
     * Get factura by id
     *
     * @param id id number
     * @return factura with matching id
     * @throws FacturaQueryException
     */
    @Override
    public Factura getFacturaById(int id) throws FacturaQueryException {
        logger.info("FacturasManager: finding factura by id.");
        return em.find(Factura.class, id);
    }

    /**
     * Update factura values
     *
     * @param factura factura to update (Same id, new values)
     * @throws FacturaUpdateException
     */
    @Override
    public void updateFactura(Factura factura) throws FacturaUpdateException {
        try {
            if (!em.contains(factura)&& isValid(factura)) {
                em.merge(factura);
            }
        } catch (Exception ex) {
            logger.info("FacturasManager: FacturasDataException updating factura.");
            throw new FacturaUpdateException("Error updating factura.\n" + ex.getMessage());
        }
    }

    /**
     * Delete factura by id.
     *
     * @param id factura id
     */
    @Override
    public void deleteFactura(Factura factura) throws FacturaDeleteException {
        logger.info("FacturasManager: Deleting user.");
        try {
            factura = em.merge(factura);
            em.remove(factura);
        } catch (Exception ex) {
            logger.severe("FacturasManager: FacturasDeleteException deleting factura.");
            throw new FacturaDeleteException("Error deleting factura.\n" + ex.getMessage());
        }
        logger.info("Factura id: < " + factura.getId() + " > deleted.");
    }

    /**
     * Utility to check if factura data is valid
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
