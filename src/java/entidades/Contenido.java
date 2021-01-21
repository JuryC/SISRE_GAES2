/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "contenido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contenido.findAll", query = "SELECT c FROM Contenido c")
    , @NamedQuery(name = "Contenido.findByIdParrafo", query = "SELECT c FROM Contenido c WHERE c.idParrafo = :idParrafo")
    , @NamedQuery(name = "Contenido.findByTitulo", query = "SELECT c FROM Contenido c WHERE c.titulo = :titulo")
    , @NamedQuery(name = "Contenido.findByOrden", query = "SELECT c FROM Contenido c WHERE c.orden = :orden")
    , @NamedQuery(name = "Contenido.findByItemLista", query = "SELECT c FROM Contenido c WHERE c.itemLista = :itemLista")})
public class Contenido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idParrafo")
    private Integer idParrafo;
    @Size(max = 80)
    @Column(name = "Titulo")
    private String titulo;
    @Lob
    @Size(max = 65535)
    @Column(name = "Contenido")
    private String contenido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Orden")
    private int orden;
    @Size(max = 150)
    @Column(name = "itemLista")
    private String itemLista;
    @JoinColumn(name = "idContrato", referencedColumnName = "idContrato")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Contrato idContrato;
    @OneToMany(mappedBy = "idTitulo", fetch = FetchType.LAZY)
    private List<Contenido> contenidoList;
    @JoinColumn(name = "idTitulo", referencedColumnName = "idParrafo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Contenido idTitulo;

    public Contenido() {
    }

    public Contenido(Integer idParrafo) {
        this.idParrafo = idParrafo;
    }

    public Contenido(Integer idParrafo, int orden) {
        this.idParrafo = idParrafo;
        this.orden = orden;
    }

    public Integer getIdParrafo() {
        return idParrafo;
    }

    public void setIdParrafo(Integer idParrafo) {
        this.idParrafo = idParrafo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getItemLista() {
        return itemLista;
    }

    public void setItemLista(String itemLista) {
        this.itemLista = itemLista;
    }

    public Contrato getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Contrato idContrato) {
        this.idContrato = idContrato;
    }

    @XmlTransient
    public List<Contenido> getContenidoList() {
        return contenidoList;
    }

    public void setContenidoList(List<Contenido> contenidoList) {
        this.contenidoList = contenidoList;
    }

    public Contenido getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(Contenido idTitulo) {
        this.idTitulo = idTitulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParrafo != null ? idParrafo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contenido)) {
            return false;
        }
        Contenido other = (Contenido) object;
        if ((this.idParrafo == null && other.idParrafo != null) || (this.idParrafo != null && !this.idParrafo.equals(other.idParrafo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Contenido[ idParrafo=" + idParrafo + " ]";
    }
    
}
