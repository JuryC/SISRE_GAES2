/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Tiposervicio;
import facade.TiposervicioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Jury
 */
@Named(value = "tipoServicioControlador")
@SessionScoped
public class TipoServicioControlador implements Serializable {

    /**
     * Creates a new instance of TipoServicioControlador
     */
    public TipoServicioControlador() {
    }
    private Tiposervicio tiposervicio;

    @PostConstruct
    public void init() {
        tiposervicio = new Tiposervicio();
    }
    @Inject
    MensajeControlador mensaje;
    @EJB
    TiposervicioFacade tiposervicioFacade;

    public Tiposervicio getTiposervicio() {
        return tiposervicio;
    }

    public void setTiposervicio(Tiposervicio tiposervicio) {
        this.tiposervicio = tiposervicio;
    }

    public void registrar() {
        tiposervicioFacade.create(tiposervicio);
        mensaje.setMensaje("MensajeRedirect('ListarTipoServicio.xhtml','Registro','El registro ha sido exitoso','success');");

        this.tiposervicio = new Tiposervicio();
    }

    public List<Tiposervicio> consultarTodos() {
        return tiposervicioFacade.consultarTodos(1);
    }

    public String preActualizar(Tiposervicio tiposervicioActualizar) {
        tiposervicio = tiposervicioActualizar;
        return "ActualizarTipoServicio";
    }

    public void actualizar() {
        tiposervicioFacade.edit(tiposervicio);
        mensaje.setMensaje("MensajeRedirect('ListarTipoServicio.xhtml','Registro','El registro ha sido exitoso','success');");

    }

    public void eliminar(Tiposervicio tiposervicioEliminar){
        tiposervicio = tiposervicioEliminar;
        tiposervicio.setEstado(2);
        tiposervicioFacade.edit(tiposervicio);
    }
    
}
