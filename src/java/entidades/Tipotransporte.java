/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jury
 */
@Entity
@Table(name = "tipotransporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipotransporte.findAll", query = "SELECT t FROM Tipotransporte t")
    , @NamedQuery(name = "Tipotransporte.findByIdTipoTransporte", query = "SELECT t FROM Tipotransporte t WHERE t.idTipoTransporte = :idTipoTransporte")
    , @NamedQuery(name = "Tipotransporte.findByNombre", query = "SELECT t FROM Tipotransporte t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tipotransporte.findByEstado", query = "SELECT t FROM Tipotransporte t WHERE t.estado = :estado")})
public class Tipotransporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoTransporte")
    private Integer idTipoTransporte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoTransporte", fetch = FetchType.LAZY)
    private List<Transporte> transporteList;

    public Tipotransporte() {
    }

    public Tipotransporte(Integer idTipoTransporte) {
        this.idTipoTransporte = idTipoTransporte;
    }

    public Tipotransporte(Integer idTipoTransporte, String nombre, int estado) {
        this.idTipoTransporte = idTipoTransporte;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdTipoTransporte() {
        return idTipoTransporte;
    }

    public void setIdTipoTransporte(Integer idTipoTransporte) {
        this.idTipoTransporte = idTipoTransporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Transporte> getTransporteList() {
        return transporteList;
    }

    public void setTransporteList(List<Transporte> transporteList) {
        this.transporteList = transporteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoTransporte != null ? idTipoTransporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipotransporte)) {
            return false;
        }
        Tipotransporte other = (Tipotransporte) object;
        if ((this.idTipoTransporte == null && other.idTipoTransporte != null) || (this.idTipoTransporte != null && !this.idTipoTransporte.equals(other.idTipoTransporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tipotransporte[ idTipoTransporte=" + idTipoTransporte + " ]";
    }
    
}
