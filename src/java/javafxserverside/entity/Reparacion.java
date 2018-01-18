package javafxserverside.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Reparacion Entity
 * @author Carlos
 */
@Entity
@Table(name="reparacion",schema="dindb")
@NamedQueries({
    @NamedQuery(
        name="findAllReparaciones",
        query="SELECT r FROM Reparacion r"
    ),
    @NamedQuery(
            name = "getReparacionesByCliente",
            query = "SELECT r FROM Reparacion r WHERE r.cliente.id = :id"
    )
})
@XmlRootElement
public class Reparacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date fechainicio;
    private Date fechafin;
    private String coche;
    private String descripcion;
    @ManyToOne
    private Cliente cliente;
    @ManyToMany(mappedBy="reparaciones")
    private Collection<Pieza> piezas;
    

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Date getFechainicio() {
        return fechainicio;
    }
    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }
    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }
    
    public String getCoche() {
        return coche;
    }
    public void setCoche(String coche) {
        this.coche = coche;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @XmlTransient
    public Collection<Pieza> getPiezas() {
        return piezas;
    }
    public void setPiezas(Collection<Pieza> piezas) {
        this.piezas = piezas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reparacion)) {
            return false;
        }
        Reparacion other = (Reparacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxserverside.entity.Reparacion[ id=" + id + " ]";
    }
    
}
