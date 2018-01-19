package javafxserverside.ejb.reparacion;

import java.util.Date;
import java.util.List;
import javafxserverside.entity.Reparacion;
import javafxserverside.exception.reparacion.ReparacionCreateException;
import javafxserverside.exception.reparacion.ReparacionDataException;
import javafxserverside.exception.reparacion.ReparacionDeleteException;
import javafxserverside.exception.reparacion.ReparacionQueryException;
import javafxserverside.exception.reparacion.ReparacionUpdateException;
import javax.ejb.Local;

/**
 * Reparacion Local Interface
 * @author Carlos
 */
@Local
public interface ReparacionManagerLocal {
    
    // Insert
    public void createReparacion(Reparacion reparacion) throws ReparacionCreateException, ReparacionDataException;
    
    // Get 
    public List<Reparacion> getAllReparaciones() throws ReparacionQueryException;
    public List<Reparacion> getReparacionesByDate(String fromDate, String toDate) throws ReparacionQueryException;
    public List<Reparacion> getReparacionesByCliente(int id) throws ReparacionQueryException;
    public Reparacion getReparacionById(int id) throws ReparacionQueryException;
    
    // Update
    public void updateReparacion(Reparacion reparacion) throws ReparacionUpdateException;
    
    // Delete
    public void deleteReparacion(Reparacion reparacion) throws ReparacionDeleteException;
    
    
    
    
    
}
