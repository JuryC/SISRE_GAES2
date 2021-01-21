/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Inventario;
import entidades.Tipomaterial;
import entidades.Usuario;
import facade.InventarioFacade;
import facade.TipomaterialFacade;
import facade.UsuarioFacade;
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
@Named(value = "inventarioControlador")
@ManagedBean
@SessionScoped
public class InventarioControlador implements Serializable {

    /**
     * Creates a new instance of InventarioControlador
     */
    public InventarioControlador() {
    }
    Inventario inventario;
    Usuario usuario;

    @PostConstruct
    public void init() {
        inventario = new Inventario();
        usuario = new Usuario();
    }
    @Inject
    MensajeControlador mensaje;
    @EJB
    InventarioFacade inventarioFacade;

    @EJB
    UsuarioFacade usuarioFacade;

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void registrar() {
        inventario.setIdUsuario(usuario);
        inventario.setEstado(1);
        inventarioFacade.create(inventario);
         mensaje.setMensaje("MensajeRedirect('ListarInventario.xhtml','Registro','El registro ha sido exitoso','success');");
    
         this.inventario = new Inventario();
         this.usuario = null;
    }
    
    public Double obtenerValor(int id){
        return inventarioFacade.obtenerValor(id).getValor();
    }
    
    public Integer obtenerExistencias(int id){
        return inventarioFacade.obtenerValor(id).getExistencia();
    }

    public String preActualizar(Inventario inventarioActualizar) {
        inventario = inventarioActualizar;
        usuario = inventarioActualizar.getIdUsuario();
        return "ActualizarInventario";
    }

    public void actualizar() {
        try {
            inventario.setIdUsuario(usuarioFacade.find(usuario.getIdUsuario()));
            inventarioFacade.edit(inventario);
        } catch (Exception e) {
        }
         mensaje.setMensaje("MensajeRedirect('ListarInventario.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");


    }

    public void eliminar(Inventario inventarioEliminar) {
        inventario = inventarioEliminar;
        inventario.setEstado(2);
        inventarioFacade.edit(inventario);
        mensaje.setMensaje("MensajeEliminar;");
    }

    public List<Inventario> consultarTodos() {
        return inventarioFacade.consultarTodos(1);
    }
    public List<Inventario> consultarExistencias() {
        return inventarioFacade.consultarExistencias();
    }

}