package javafxserverside.ejb.cliente;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxserverside.entity.Cliente;
import javafxserverside.exception.cliente.ClienteQueryException;
import javafxserverside.exception.cliente.ClienteCreateException;
import javafxserverside.exception.cliente.ClienteUpdateException;
import javafxserverside.exception.cliente.ClienteDeleteException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class ClienteManager implements ClienteManagerLocal {
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cliente> getAllClientes() throws ClienteQueryException{
        return em.createNamedQuery("findAllClientes").getResultList();
    }
    
    @Override
    public Cliente getClientesById(Integer id) throws ClienteQueryException{
        return em.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> getClientesByDni(String dni) throws ClienteQueryException{
        return em.createNamedQuery("findClienteByDni").setParameter("dni",dni).getResultList();
    }

    @Override
    public List<Cliente> getClientesByNombre(String nombre) throws ClienteQueryException{
        return em.createNamedQuery("findClienteByNombre").setParameter("nombre",nombre).getResultList();
    }

    @Override
    public void createClient(Cliente cliente) throws ClienteCreateException {
        LOGGER.info("ClienteManager: Creating cliente.");
        try{
            if(cliente.getId()== null || cliente.getDni().equals("")|| cliente.getNombre()==null ||  cliente.getApellidos()==null 
                || cliente.getTelefono().equals("") || cliente.getEmail().equals("")){
            }
            em.persist(cliente);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ClienteManager: Exception creating cliente.{0}",e.getMessage());
            throw new ClienteCreateException("Error creating cliente.\n" + e.getMessage());
        }
        LOGGER.info("ClienteCreateException: Cliente created");
    }

    @Override
    public void updateClient(Cliente cliente) throws ClienteUpdateException {
        LOGGER.info("ClienteManager: Updating cliente.");
        try{
            if(!em.contains(cliente))em.merge(cliente);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ClienteManager: Exception updating cliente.{0}",e.getMessage());
            throw new ClienteUpdateException("Error updating cliente.\n" + e.getMessage());
        }
        LOGGER.info("ClienteUpdateException: Cliente updated");
    }

    @Override
    public void deleteClient(Cliente cliente) throws ClienteDeleteException {
        LOGGER.info("ClienteManager: Deleting cliente.");
        try{
            cliente=em.merge(cliente);
            em.remove(cliente);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ClienteManager: Exception deleting cliente.{0}",e.getMessage());
            throw new ClienteDeleteException("Error deleting cliente.\n" + e.getMessage());
        }
        LOGGER.info("ClienteDeleteException: Cliente deleted");
    }
    
}