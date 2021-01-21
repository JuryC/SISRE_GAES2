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
@Table(name = "servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s")
    , @NamedQuery(name = "Servicio.findByIdServicio", query = "SELECT s FROM Servicio s WHERE s.idServicio = :idServicio")
    , @NamedQuery(name = "Servicio.findByEstadoServicio", query = "SELECT s FROM Servicio s WHERE s.estadoServicio = :estadoServicio")
    , @NamedQuery(name = "Servicio.findByEstado", query = "SELECT s FROM Servicio s WHERE s.estado = :estado")
    , @NamedQuery(name = "Servicio.findByTotal", query = "SELECT s FROM Servicio s WHERE s.total = :total")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServicio")
    private Integer idServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "EstadoServicio")
    private String estadoServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Total")
    private double total;
    @JoinColumn(name = "TipoServicio", referencedColumnName = "idTipoServicio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tiposervicio tipoServicio;
    @JoinColumn(name = "idCita", referencedColumnName = "idCita")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cita idCita;
    @JoinColumn(name = "idEnvio", referencedColumnName = "idEnvio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Envio idEnvio;
    @JoinColumn(name = "idTransporte", referencedColumnName = "idTransporte")
    @ManyToOne(fetch = FetchType.LAZY)
    private Transporte idTransporte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicio", fetch = FetchType.LAZY)
    private List<Solicitud> solicitudList;

    public Servicio() {
    }

    public Servicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio(Integer idServicio, String estadoServicio, int estado, double total) {
        this.idServicio = idServicio;
        this.estadoServicio = estadoServicio;
        this.estado = estado;
        this.total = total;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(String estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Tiposervicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(Tiposervicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Cita getIdCita() {
        return idCita;
    }

    public void setIdCita(Cita idCita) {
        this.idCita = idCita;
    }

    public Envio getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Envio idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Transporte getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(Transporte idTransporte) {
        this.idTransporte = idTransporte;
    }

    @XmlTransient
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Servicio[ idServicio=" + idServicio + " ]";
    }
    
}
