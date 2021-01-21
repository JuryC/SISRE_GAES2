/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Servicio;
import entidades.Solicitud;
import facade.ServicioFacade;
import facade.SolicitudFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Jury
 */
@Named(value = "solicitudControlador")
@SessionScoped
public class SolicitudControlador implements Serializable {

    /**
     * Creates a new instance of SolicitudControlador
     */
    public SolicitudControlador() {
    }
    Solicitud solicitud;
    Servicio servicio;
    private Date fecha = new Date();

    @PostConstruct
    public void init() {
        solicitud = new Solicitud();
        servicio = new Servicio();
    }
    @Inject
    MensajeControlador mensaje;
    @EJB
    SolicitudFacade solicitudFacade;

    @EJB
    ServicioFacade servicioFacade;

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public void registrar() {
        solicitud.setIdServicio(servicio);
        solicitud.setEstado(1);
        solicitudFacade.create(solicitud);
        solicitud.setFechaSolicitud(fecha(solicitud.getFechaSolicitud()));
        solicitud.setFechaResolucion(fecha(solicitud.getFechaResolucion()));
        mensaje.setMensaje("MensajeRedirect('ListarSolicitud.xhtml','Registro','El registro ha sido exitoso','success');");

        this.solicitud = new Solicitud();
        this.servicio = null;

    }

    public Date fecha(Date fecha) {
        Calendar f = Calendar.getInstance();
        f.setTime(fecha);
        f.add(Calendar.DATE, 1);
        return f.getTime();
    }

    public String convertirf(Date fecha) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(fecha);
    }

    public List<Solicitud> consultarTodos() {
        return solicitudFacade.consultarTodos(1);
    }

    public String preActualizar(Solicitud solicitudActualizar) {
        solicitud = solicitudActualizar;
        servicio = solicitudActualizar.getIdServicio();
        return "ActualizarSolicitud";
    }

    public void actualizar() {
        solicitud.setIdServicio(servicio);
        solicitudFacade.edit(solicitud);
        mensaje.setMensaje("MensajeRedirect('ListarSolicitud.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");

    }

    public void eliminar(Solicitud solicitudEliminar) {
        solicitud = solicitudEliminar;
        solicitud.setEstado(2);
        solicitudFacade.edit(solicitud);
    }
}
