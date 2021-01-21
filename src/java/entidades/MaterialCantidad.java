/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jury
 */
@Entity
@Table(name = "materialcantidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialCantidad.findAll", query = "SELECT m FROM MaterialCantidad m")
    , @NamedQuery(name = "MaterialCantidad.findByIdMatCant", query = "SELECT m FROM MaterialCantidad m WHERE m.idMatCant = :idMatCant")
    , @NamedQuery(name = "MaterialCantidad.findByCantidad", query = "SELECT m FROM MaterialCantidad m WHERE m.cantidad = :cantidad")
    , @NamedQuery(name = "MaterialCantidad.findByFechaIngreso", query = "SELECT m FROM MaterialCantidad m WHERE m.fechaIngreso = :fechaIngreso")})
public class MaterialCantidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMatCant")
    private Integer idMatCant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @JoinColumn(name = "Material", referencedColumnName = "idMaterial")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Material material;

    public MaterialCantidad() {
    }

    public MaterialCantidad(Integer idMatCant) {
        this.idMatCant = idMatCant;
    }

    public MaterialCantidad(Integer idMatCant, int cantidad, Date fechaIngreso) {
        this.idMatCant = idMatCant;
        this.cantidad = cantidad;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdMatCant() {
        return idMatCant;
    }

    public void setIdMatCant(Integer idMatCant) {
        this.idMatCant = idMatCant;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatCant != null ? idMatCant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialCantidad)) {
            return false;
        }
        MaterialCantidad other = (MaterialCantidad) object;
        if ((this.idMatCant == null && other.idMatCant != null) || (this.idMatCant != null && !this.idMatCant.equals(other.idMatCant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MaterialCantidad[ idMatCant=" + idMatCant + " ]";
    }
    
}
