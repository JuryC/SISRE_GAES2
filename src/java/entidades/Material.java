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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m")
    , @NamedQuery(name = "Material.findByIdMaterial", query = "SELECT m FROM Material m WHERE m.idMaterial = :idMaterial")
    , @NamedQuery(name = "Material.findByNombre", query = "SELECT m FROM Material m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Material.findByDescripcion", query = "SELECT m FROM Material m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Material.findByEstado", query = "SELECT m FROM Material m WHERE m.estado = :estado")})
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMaterial")
    private Integer idMaterial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaterial", fetch = FetchType.LAZY)
    private List<CitaMaterial> citaMaterialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material", fetch = FetchType.LAZY)
    private List<MaterialCantidad> materialCantidadList;
    @JoinColumn(name = "idTipoMaterial", referencedColumnName = "idTipoMaterial")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipomaterial idTipoMaterial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo", fetch = FetchType.LAZY)
    private List<Inventario> inventarioList;

    public Material() {
    }

    public Material(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Material(Integer idMaterial, String nombre, String descripcion, int estado) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<CitaMaterial> getCitaMaterialList() {
        return citaMaterialList;
    }

    public void setCitaMaterialList(List<CitaMaterial> citaMaterialList) {
        this.citaMaterialList = citaMaterialList;
    }

    @XmlTransient
    public List<MaterialCantidad> getMaterialCantidadList() {
        return materialCantidadList;
    }

    public void setMaterialCantidadList(List<MaterialCantidad> materialCantidadList) {
        this.materialCantidadList = materialCantidadList;
    }

    public Tipomaterial getIdTipoMaterial() {
        return idTipoMaterial;
    }

    public void setIdTipoMaterial(Tipomaterial idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
    }

    @XmlTransient
    public List<Inventario> getInventarioList() {
        return inventarioList;
    }

    public void setInventarioList(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaterial != null ? idMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Material[ idMaterial=" + idMaterial + " ]";
    }
    
}
