/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cita;
import entidades.Cliente;
import entidades.Envio;
import entidades.Servicio;
import entidades.Tiposervicio;
import entidades.Tipotransporte;
import entidades.Transporte;
import facade.CitaFacade;
import facade.EnvioFacade;
import facade.ServicioFacade;
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
@Named (value="servicioControlador")
@ManagedBean
@SessionScoped
public class ServicioControlador implements Serializable{

    /**
     * Creates a new instance of ServicioControlador
     */
    public ServicioControlador() {
    }
    private Servicio servicio;
    private Envio envio;
    private Cita cita;
    private Transporte transporte;
    private Tiposervicio Tservicio;
    private int idTra;
    private Tipotransporte tipoTransporte;

    @PostConstruct
    public void init(){
        servicio = new Servicio();
        envio = new Envio();
        cita = new Cita();
        transporte = new Transporte();
        Tservicio = new Tiposervicio();
    }
    @Inject
    MensajeControlador mensaje;
    
    @EJB
    ServicioFacade servicioFacade;
    
    @EJB
    EnvioFacade envioFacade;
    
    @EJB
    CitaFacade citaFacade;
    
    @EJB
    TransporteFacade transporteFacade;
    
    
    public List<Servicio> consultarTodos(){
        return servicioFacade.consultarTodos(1);
    }
    public List<Servicio> consultarEstado(String estado){
        return servicioFacade.consultarEstado(estado);
    }
    
    public List<Transporte> consultarDisponibles(){
        return transporteFacade.consultarDisponibles();
    }
    
    public String asignarTransporte(Servicio servicioActualizar){
        servicio = servicioActualizar;
        envio = servicioActualizar.getIdEnvio();
        cita = servicioActualizar.getIdCita();
        Tservicio = servicioActualizar.getTipoServicio();
        return "AsignarTransporte";
    }
    
    public String actualizar(){
        servicio.setIdEnvio(envio);
        servicio.setIdCita(cita);
        transporte = transporteFacade.obtenerTransporte(idTra);
        servicio.setIdTransporte(transporte);
        servicio.setTipoServicio(Tservicio);
        servicio.setEstadoServicio("Verificado");
        servicioFacade.edit(servicio);
       
        
        
        mensaje.setMensaje("MensajeRedirect('ListarServicio.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
        return "ListarServicio";
    }
    
    public void eliminar(Servicio servicioEliminar){
        servicio = servicioEliminar;
        servicio.setEstado(2);
        servicioFacade.edit(servicio);
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
    
    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Tiposervicio getTservicio() {
        return Tservicio;
    }

    public void setTservicio(Tiposervicio Tservicio) {
        this.Tservicio = Tservicio;
    }

    public int getIdTra() {
        return idTra;
    }

    public void setIdTra(int idTra) {
        this.idTra = idTra;
    }

   
}
