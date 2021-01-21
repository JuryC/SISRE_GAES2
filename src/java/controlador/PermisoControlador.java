/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Permiso;
import facade.PermisoFacade;
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
@Named (value="permisoControlador")
@ManagedBean
@SessionScoped
public class PermisoControlador implements Serializable{

    /**
     * Creates a new instance of PermisoControlador
     */
    public PermisoControlador() {
    }
    Permiso permiso;
    
    @PostConstruct
    public void init(){
        permiso = new Permiso();
    }
    @Inject
    MensajeControlador mensaje;
    @EJB
    PermisoFacade permisoFacade;

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
    public void registrar(){
        permiso.setEstado(1);
        permisoFacade.create(permiso);
        mensaje.setMensaje("MensajeRedirect('ListarPermiso.xhtml','Registro','El registro ha sido exitoso','success');");
    
        this.permiso = new Permiso();
    }
    public List<Permiso> consultarTodos(){
        return permisoFacade.consultarTodos(1);
    }
    public String preActualizar(Permiso permisoActualizar){
        permiso = permisoActualizar;
        return "ActualizarPermiso";
    }
    public void actualizar(){
        permisoFacade.edit(permiso);
         mensaje.setMensaje("MensajeRedirect('ListarPermiso.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }
    public void eliminar(Permiso permisoEliminar){
        permiso = permisoEliminar;
        permiso.setEstado(2);
        permisoFacade.edit(permiso);
    }
}
