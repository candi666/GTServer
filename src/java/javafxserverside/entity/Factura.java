/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Factura Entity
 * @author Carlos
 */
@Entity
@Table(name="factura",schema="dindb")
@NamedQueries({
    @NamedQuery(
            name = "getAllFacturas",
            query = "SELECT f FROM Factura f"
    ),
    @NamedQuery(
            name = "getFacturasByCliente",
            query = "SELECT f FROM Factura f WHERE f.cliente.id = :id"
    ),
    @NamedQuery(
            name = "getFacturaByReparacion",
            query = "SELECT f FROM Factura f WHERE f.reparacion.id = :id"
    ),
    @NamedQuery(
            name = "getFacturasByDate",
            query = "SELECT f FROM Factura f WHERE f.fecha BETWEEN :fromdate AND :todate"
    ),
    @NamedQuery(
            name = "getFacturasByPagada",
            query = "SELECT f FROM Factura f WHERE f.pagada=:status"
    )    
})
@XmlRootElement
public class Factura implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fecha;
    private Date fechavenc;
    private Float total;
    private Boolean pagada;
    @OneToOne
    private Reparacion reparacion;
    @ManyToOne
    private Cliente cliente;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechavenc() {
        return fechavenc;
    }

    public void setFechavenc(Date fechavenc) {
        this.fechavenc = fechavenc;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Boolean getPagada() {
        return pagada;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }

    public Reparacion getReparacion() {
        return reparacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setReparacion(Reparacion reparacion) {
        this.reparacion = reparacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compares if factura equals another factura.
     * @param object factura to compare
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns object data as string.
     * @return 
     */
    @Override
    public String toString() {
        return "javafxserverside.entity.Factura[ id=" + id + " ]";
    }

}
