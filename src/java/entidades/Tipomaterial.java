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
@Table(name = "tipomaterial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomaterial.findAll", query = "SELECT t FROM Tipomaterial t")
    , @NamedQuery(name = "Tipomaterial.findByIdTipoMaterial", query = "SELECT t FROM Tipomaterial t WHERE t.idTipoMaterial = :idTipoMaterial")
    , @NamedQuery(name = "Tipomaterial.findByNombre", query = "SELECT t FROM Tipomaterial t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tipomaterial.findByEstado", query = "SELECT t FROM Tipomaterial t WHERE t.estado = :estado")})
public class Tipomaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoMaterial")
    private Integer idTipoMaterial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMaterial", fetch = FetchType.LAZY)
    private List<Material> materialList;

    public Tipomaterial() {
    }

    public Tipomaterial(Integer idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
    }

    public Tipomaterial(Integer idTipoMaterial, String nombre, int estado) {
        this.idTipoMaterial = idTipoMaterial;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdTipoMaterial() {
        return idTipoMaterial;
    }

    public void setIdTipoMaterial(Integer idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
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
    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMaterial != null ? idTipoMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomaterial)) {
            return false;
        }
        Tipomaterial other = (Tipomaterial) object;
        if ((this.idTipoMaterial == null && other.idTipoMaterial != null) || (this.idTipoMaterial != null && !this.idTipoMaterial.equals(other.idTipoMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tipomaterial[ idTipoMaterial=" + idTipoMaterial + " ]";
    }
    
}
