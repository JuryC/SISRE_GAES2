/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Envio;
import entidades.Tiposervicio;
import facade.EnvioFacade;
import facade.TiposervicioFacade;
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
@Named(value = "envioControlador")
@ManagedBean
@SessionScoped
public class EnvioControlador implements Serializable {

    /**
     * Creates a new instance of EnvioControlador
     */
    public EnvioControlador() {
    }
    private Envio envio;
    private Tiposervicio tipoS;

    @PostConstruct
    public void init() {
        envio = new Envio();
        tipoS = new Tiposervicio();
    }
    @Inject
    MensajeControlador mensaje;
    
    @EJB
    EnvioFacade envioFacade;

    @EJB
    TiposervicioFacade tipoSFacade;

    public void registrar() {
        envio.setEstado(1);
        envioFacade.create(envio);
        mensaje.setMensaje("MensajeRedirect('ListarEnvio.xhtml','Registro','El registro ha sido exitoso','success');");
    
        this.envio = new Envio();
    }

    public List<Envio> consultarTodos() {
        return envioFacade.consultarTodos(1);
    }
    public List<Tiposervicio> consultAll() {
        return tipoSFacade.findAll();
    }

    public String preActualizar(Envio envioActualizar) {
        envio = envioActualizar;
        return "ActualizarEnvio";
    }

    public void actualizar() {
        envioFacade.edit(envio);
         mensaje.setMensaje("MensajeRedirect('ListarEnvio.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");

    }

    public void eliminar(Envio envioEliminar) {
        envio = envioEliminar;
        envio.setEstado(2);
        envioFacade.edit(envio);
    }
    
    
    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public Tiposervicio getTipoS() {
        return tipoS;
    }

    public void setTipoS(Tiposervicio tipoS) {
        this.tipoS = tipoS;
    }
    
    
}
