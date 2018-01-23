package javafxserverside.ejb.pieza;

import javafxserverside.ejb.pieza.*;
import java.util.Date;
import java.util.List;
import javafxserverside.entity.Pieza;
import javafxserverside.exception.pieza.PiezaCreateException;
import javafxserverside.exception.pieza.PiezaDataException;
import javafxserverside.exception.pieza.PiezaDeleteException;
import javafxserverside.exception.pieza.PiezaQueryException;
import javafxserverside.exception.pieza.PiezaUpdateException;
import javax.ejb.Local;

/**
 * Pieza Local Interface
 * @author Carlos
 */
@Local
public interface PiezaManagerLocal {
    
    // Insert
    public void createPieza(Pieza pieza) throws PiezaCreateException, PiezaDataException;
    
    // Get 
    public List<Pieza> getAllPiezas() throws PiezaQueryException;
    public Pieza getPiezaById(int id) throws PiezaQueryException;
    
    // Update
    public void updatePieza(Pieza pieza) throws PiezaUpdateException;
    
    // Delete
    public void deletePieza(Pieza pieza) throws PiezaDeleteException;
    
    
    
    
    
}
