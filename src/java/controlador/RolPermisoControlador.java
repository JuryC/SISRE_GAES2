/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Permiso;
import entidades.Rol;
import entidades.Rolpermiso;
import facade.PermisoFacade;
import facade.RolFacade;
import facade.RolpermisoFacade;
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
@Named (value="rolpermisoControlador")
@ManagedBean
@SessionScoped
public class RolPermisoControlador implements Serializable{

    /**
     * Creates a new instance of RolPermisoControlador
     */
    public RolPermisoControlador() {
    }
    Rolpermiso rolpermiso;
    Permiso permiso;
    Rol rol;
    
    @PostConstruct
    public void init(){
    rolpermiso = new Rolpermiso();
    rol = new Rol();
    permiso = new Permiso();
}
    @Inject
    MensajeControlador mensaje;
    
    @EJB
    RolpermisoFacade rolpermisoFacade;
    
    @EJB
    RolFacade rolFacade;
    
    @EJB
    PermisoFacade permisoFacade;

    public Rolpermiso getRolpermiso() {
        return rolpermiso;
    }

    public void setRolpermiso(Rolpermiso rolpermiso) {
        this.rolpermiso = rolpermiso;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public void registrar(){
        rolpermiso.setIdRol(rol);
        rolpermiso.setIdPermiso(permiso);
        rolpermiso.setEstado(1);
        rolpermisoFacade.create(rolpermiso);
        mensaje.setMensaje("MensajeRedirect('ListarRolPermiso.xhtml','Registro','El registro ha sido exitoso','success');");
    
       this.rolpermiso = new Rolpermiso();
       this.rol = null;
       this.permiso = null;
    
    }
    public List<Rolpermiso> consultarTodos(){
        return rolpermisoFacade.consultarTodos(1);
    }
    public String preActualizar(Rolpermiso rolpermisoActualizar){
        rol = rolpermisoActualizar.getIdRol();
        permiso = rolpermisoActualizar.getIdPermiso();
        rolpermiso = rolpermisoActualizar;
        return "update_rolpermiso";
    }
    public void actualizar(){
        rolpermiso.setIdPermiso(permiso);
        rolpermiso.setIdRol(rol);
        rolpermisoFacade.edit(rolpermiso);
        mensaje.setMensaje("MensajeRedirect('ListarRolPermiso.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }
    public void eliminar(Rolpermiso rolpermisoEliminar){
        rolpermiso = rolpermisoEliminar;
        rolpermiso.setEstado(2);
        rolpermisoFacade.edit(rolpermiso);
    }
}