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
@Table(name = "tiposervicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiposervicio.findAll", query = "SELECT t FROM Tiposervicio t")
    , @NamedQuery(name = "Tiposervicio.findByIdTipoServicio", query = "SELECT t FROM Tiposervicio t WHERE t.idTipoServicio = :idTipoServicio")
    , @NamedQuery(name = "Tiposervicio.findByNombre", query = "SELECT t FROM Tiposervicio t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tiposervicio.findByValor", query = "SELECT t FROM Tiposervicio t WHERE t.valor = :valor")
    , @NamedQuery(name = "Tiposervicio.findByEstado", query = "SELECT t FROM Tiposervicio t WHERE t.estado = :estado")})
public class Tiposervicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoServicio")
    private Integer idTipoServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor")
    private double valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoServicio", fetch = FetchType.LAZY)
    private List<Servicio> servicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoServicio", fetch = FetchType.LAZY)
    private List<Contrato> contratoList;

    public Tiposervicio() {
    }

    public Tiposervicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Tiposervicio(Integer idTipoServicio, String nombre, double valor, int estado) {
        this.idTipoServicio = idTipoServicio;
        this.nombre = nombre;
        this.valor = valor;
        this.estado = estado;
    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoServicio != null ? idTipoServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposervicio)) {
            return false;
        }
        Tiposervicio other = (Tiposervicio) object;
        if ((this.idTipoServicio == null && other.idTipoServicio != null) || (this.idTipoServicio != null && !this.idTipoServicio.equals(other.idTipoServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tiposervicio[ idTipoServicio=" + idTipoServicio + " ]";
    }
    
}
