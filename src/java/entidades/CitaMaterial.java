/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jury
 */
@Entity
@Table(name = "cita_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CitaMaterial.findAll", query = "SELECT c FROM CitaMaterial c")
    , @NamedQuery(name = "CitaMaterial.findByIdCitaMaterial", query = "SELECT c FROM CitaMaterial c WHERE c.idCitaMaterial = :idCitaMaterial")
    , @NamedQuery(name = "CitaMaterial.findByCantidadSolicitada", query = "SELECT c FROM CitaMaterial c WHERE c.cantidadSolicitada = :cantidadSolicitada")})
public class CitaMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cita_material")
    private Integer idCitaMaterial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CantidadSolicitada")
    private int cantidadSolicitada;
    @JoinColumn(name = "idCita", referencedColumnName = "idCita")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cita idCita;
    @JoinColumn(name = "idMaterial", referencedColumnName = "idMaterial")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Material idMaterial;

    public CitaMaterial() {
    }

    public CitaMaterial(Integer idCitaMaterial) {
        this.idCitaMaterial = idCitaMaterial;
    }

    public CitaMaterial(Integer idCitaMaterial, int cantidadSolicitada) {
        this.idCitaMaterial = idCitaMaterial;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getIdCitaMaterial() {
        return idCitaMaterial;
    }

    public void setIdCitaMaterial(Integer idCitaMaterial) {
        this.idCitaMaterial = idCitaMaterial;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Cita getIdCita() {
        return idCita;
    }

    public void setIdCita(Cita idCita) {
        this.idCita = idCita;
    }

    public Material getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Material idMaterial) {
        this.idMaterial = idMaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCitaMaterial != null ? idCitaMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitaMaterial)) {
            return false;
        }
        CitaMaterial other = (CitaMaterial) object;
        if ((this.idCitaMaterial == null && other.idCitaMaterial != null) || (this.idCitaMaterial != null && !this.idCitaMaterial.equals(other.idCitaMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CitaMaterial[ idCitaMaterial=" + idCitaMaterial + " ]";
    }
    
}
