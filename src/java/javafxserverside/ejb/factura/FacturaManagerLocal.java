package javafxserverside.ejb.factura;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javafxserverside.entity.Factura;
import javafxserverside.exception.factura.FacturaCreateException;
import javafxserverside.exception.factura.FacturaDataException;
import javafxserverside.exception.factura.FacturaDeleteException;
import javafxserverside.exception.factura.FacturaQueryException;
import javafxserverside.exception.factura.FacturaUpdateException;
import javax.ejb.Local;

/**
 * Facturas Local Interface
 * @author Carlos
 */
@Local
public interface FacturaManagerLocal {
    
    // Insert
    public void createFactura(Factura factura) throws FacturaCreateException, FacturaDataException;
    
    // Get 
    public List<Factura> getAllFacturas() throws FacturaQueryException;
    public List<Factura> getFacturasByDate(Date fromDate, Date toDate) throws FacturaQueryException;
    public List<Factura> getFacturasByCliente(int id) throws FacturaQueryException;
    public Factura getFacturaById(int id) throws FacturaQueryException;
    
    // Update
    public void updateFactura(Factura factura) throws FacturaUpdateException;
    
    // Delete
    public void deleteFactura(Factura factura) throws FacturaDeleteException;
    
    
    
    
    
}
