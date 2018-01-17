package javafxserverside.ejb.factura;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javafxserverside.entity.Factura;
import javafxserverside.exception.factura.FacturasCreateException;
import javafxserverside.exception.factura.FacturasDataException;
import javafxserverside.exception.factura.FacturasDeleteException;
import javafxserverside.exception.factura.FacturasQueryException;
import javafxserverside.exception.factura.FacturasUpdateException;
import javax.ejb.Local;

/**
 * Facturas Local Interface
 * @author Carlos
 */
@Local
public interface FacturasManagerLocal {
    
    // Insert
    public void createFactura(Factura factura) throws FacturasCreateException, FacturasDataException;
    
    // Get 
    public List<Factura> getAllFacturas() throws FacturasQueryException;
    public List<Factura> getFacturasByDate(Date fromDate, Date toDate) throws FacturasQueryException;;
    public List<Factura> getFacturasByCliente(int id) throws FacturasQueryException;;
    public Factura getFacturaById(int id) throws FacturasQueryException;;
    
    // Update
    public void updateFactura(Factura factura) throws FacturasUpdateException;
    
    // Delete
    public void deleteFactura(Factura factura) throws FacturasDeleteException;
    
    
    
    
    
}
