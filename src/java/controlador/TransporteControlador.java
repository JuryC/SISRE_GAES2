/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Servicio;
import entidades.Tipotransporte;
import entidades.Transporte;
import facade.ServicioFacade;
import facade.TipotransporteFacade;
import facade.TransporteFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jury
 */
@Named(value = "transporteControlador")
@ManagedBean
@SessionScoped
public class TransporteControlador implements Serializable {

    /**
     * Creates a new instance of TransporteControlador
     */
    public TransporteControlador() {
    }
    Transporte transporte;
    Tipotransporte tipotransporte;
    

    @PostConstruct
    public void init() {
        transporte = new Transporte();
        tipotransporte = new Tipotransporte();
    }
    @Inject
    MensajeControlador mensaje;
    @EJB
    TransporteFacade transporteFacade;

    @EJB
    TipotransporteFacade tipotransporteFacade;


    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Tipotransporte getTipotransporte() {
        return tipotransporte;
    }

    public void setTipotransporte(Tipotransporte tipotransporte) {
        this.tipotransporte = tipotransporte;
    }


    public void registrar() {
        transporte.setIdTipoTransporte(tipotransporte);
        transporte.setEstado(1);
        transporteFacade.create(transporte);
        mensaje.setMensaje("MensajeRedirect('ListarTransporte.xhtml','Registro','El registro ha sido exitoso','success');");
    
        this.transporte = new Transporte();
    
    }

    public List<Transporte> consultarTodos() {
        return transporteFacade.consultarTodos(1);
    }

    public List<Transporte> consultarDisponibles(){
        return transporteFacade.consultarDisponibles();
    }
    
    public String preActualizar(Transporte transporteActualizar) {
        transporte = transporteActualizar;
        tipotransporte = transporteActualizar.getIdTipoTransporte();
        return "ActualizarTransporte";
    }

    public void actualizar() {
        transporte.setIdTipoTransporte(tipotransporte);
        transporteFacade.edit(transporte);
        mensaje.setMensaje("MensajeRedirect('ListarCita.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }

    public void eliminar(Transporte transporteEliminar) {
        transporte = transporteEliminar;
        transporte.setEstado(2);
        transporteFacade.edit(transporte);
    }
}
