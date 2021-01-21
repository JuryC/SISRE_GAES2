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
@Table(name = "rolpermiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolpermiso.findAll", query = "SELECT r FROM Rolpermiso r")
    , @NamedQuery(name = "Rolpermiso.findByIdRolPermiso", query = "SELECT r FROM Rolpermiso r WHERE r.idRolPermiso = :idRolPermiso")
    , @NamedQuery(name = "Rolpermiso.findByEstado", query = "SELECT r FROM Rolpermiso r WHERE r.estado = :estado")})
public class Rolpermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRolPermiso")
    private Integer idRolPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private int estado;
    @JoinColumn(name = "idRol", referencedColumnName = "idRol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol idRol;
    @JoinColumn(name = "idPermiso", referencedColumnName = "idPermiso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Permiso idPermiso;

    public Rolpermiso() {
    }

    public Rolpermiso(Integer idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

    public Rolpermiso(Integer idRolPermiso, int estado) {
        this.idRolPermiso = idRolPermiso;
        this.estado = estado;
    }

    public Integer getIdRolPermiso() {
        return idRolPermiso;
    }

    public void setIdRolPermiso(Integer idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public Permiso getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Permiso idPermiso) {
        this.idPermiso = idPermiso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolPermiso != null ? idRolPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolpermiso)) {
            return false;
        }
        Rolpermiso other = (Rolpermiso) object;
        if ((this.idRolPermiso == null && other.idRolPermiso != null) || (this.idRolPermiso != null && !this.idRolPermiso.equals(other.idRolPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Rolpermiso[ idRolPermiso=" + idRolPermiso + " ]";
    }
    
}
