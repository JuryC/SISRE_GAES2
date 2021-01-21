/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author Jury
 */

import entidades.Permiso;
import entidades.Rol;
import entidades.Rolpermiso;
import entidades.Usuario;
import facade.RolFacade;
import facade.UsuarioFacade;
import facade.PermisoFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author Ismael
 */
@Named(value = "sesionControlador")
@SessionScoped
public class sesionControlador implements Serializable {

    @EJB
    RolFacade rolFacade;
    
    @EJB
    PermisoFacade permisoFacade;
    
    @EJB
    UsuarioFacade usuarioFacade;
    
    @Inject
    MensajeControlador mensaje;
    
    private Long documento;
    private String clave;
    private Rol rolSeleccionado;
    private Usuario usuario;




   

    /**
     * Creates a new instance of SessionController
     */
    public sesionControlador() {
        FacesContext fc = FacesContext.getCurrentInstance();
          rolSeleccionado=new Rol();
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public String loginUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        usuario = usuarioFacade.loginUser(documento, clave);
        if (usuario != null) {
            if(usuario.getEstado()==1){
                
            rolSeleccionado = usuario.getIdRol();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogueado", usuario);
            mensaje.setMensaje("MensajeRedirect,'Bienvenido','Inicio de sesión de manera correcta','success');");
            return "manage/dashboard?faces-redirect=true";
        } else {
                mensaje.setMensaje("MensajeRedirect('Usuario Removido','El usuario <b>"+usuario.getNombre()+" "+usuario.getApellido()+"</b> ha sido eliminado o bloqueado. Mayor información comuniquese a sistemasdesoluciones@gmail.com','warning');");
            return "login?faces-redirect=true";
        }
        }else{
            mensaje.setMensaje("MensajeRedirect('Error','Documento o contraseña incorrecta','Warning');");
            return "";
        }
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.usuario = null;
        this.documento = null;
        this.clave = "";
        return "/index.html?faces-redirect=true";
    }

    public Boolean inicioSesion() {
        return (usuario != null);
    }
    
    public Boolean validarPermiso(){
        FacesContext fc = FacesContext.getCurrentInstance();
        String urlRecurso = fc.getExternalContext().getRequestPathInfo();
        for (Rolpermiso p : rolSeleccionado.getRolpermisoList()) {
            if(p.getIdPermiso().getUrl() != null && (p.getIdPermiso().getUrl().equals(urlRecurso)
                    || p.getIdPermiso().getUrl().equals("index?faces-redirect=true")
                    )){
                return true;
            }
        }
        return false;
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        System.out.println("Rol - " + rolSeleccionado.getNombre());
        this.rolSeleccionado = rolSeleccionado;
    }
    
    public List<Permiso> hijos(int h){
        return permisoFacade.consultarHijos(h);
    }
    

}
