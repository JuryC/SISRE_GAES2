/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Rol;
import entidades.Usuario;
import facade.RolFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named (value="rolControlador")
@ManagedBean
@SessionScoped
public class RolControlador implements Serializable{

    /**
     * Creates a new instance of RolControlador
     */
    public RolControlador() {
    }
    Rol rol;
    Usuario usuario;
    
    @PostConstruct
    public void init(){
        rol = new Rol();
        usuario = new Usuario();
    }
    @Inject
    MensajeControlador mensaje;
    @EJB
    RolFacade rolFacade;
    
    @EJB
    UsuarioFacade usuarioFacade;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public void registrar(){
        rol.setEstado(1);
        rolFacade.create(rol);
        mensaje.setMensaje("MensajeRedirect('ListarRol.xhtml','Registro','El registro ha sido exitoso','success');");
   
        this.rol = new Rol();
    }
    public String preActualizar(Rol rolActualizar){
        rol = rolActualizar;
        return "ActualizarRol";
    }
    public void actualizar(){
        rolFacade.edit(rol);
        mensaje.setMensaje("MensajeRedirect('ListarRol.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }
    public void eliminar(Rol rolEliminar){
        rol = rolEliminar;
        rol.setEstado(2);
        rolFacade.edit(rol);
        
    }
    public List<Rol> consultarTodos(){
        return rolFacade.consultarTodos(1);
    }
    public List<String> consultarRolNombre() {
        List<String> nombres = new ArrayList<>();
        List<Rol> roleList = rolFacade.findAll();
        for (int i = 0; i < roleList.size(); i++) {
            String nameRol = roleList.get(i).getNombre();
            nombres.add('"' + nameRol + '"');
        }

        return nombres;
    }
    
    public List<Integer> contadorUsuRol() {
        List<Integer> data = new ArrayList<>();
        List<Rol> roleList = rolFacade.findAll();
        for (int i = 0; i < roleList.size(); i++) {
            int dataUsu = usuarioFacade.contarUsuario(roleList.get(i).getIdRol());
            data.add(dataUsu);
        }
        return data;
    }
}
