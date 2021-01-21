/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Tipomaterial;
import facade.TipomaterialFacade;
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
@Named(value = "tipomaterialControlador")
@ManagedBean
@SessionScoped
public class TipoMaterialControlador implements Serializable {

    /**
     * Creates a new instance of TipoMaterialControlador
     */
    public TipoMaterialControlador() {
    }
    Tipomaterial tipomaterial;

    @PostConstruct
    public void init() {
        tipomaterial = new Tipomaterial();

    }
    @Inject
    MensajeControlador mensaje;
    @EJB
    TipomaterialFacade tipomaterialFacade;

    public Tipomaterial getTipomaterial() {
        return tipomaterial;
    }

    public void setTipomaterial(Tipomaterial tipomaterial) {
        this.tipomaterial = tipomaterial;
    }

    public void registrar() {
        tipomaterialFacade.create(tipomaterial);
        mensaje.setMensaje("MensajeRedirect('ListarTipoMaterial.xhtml','Registro','El registro ha sido exitoso','success');");
    
        this.tipomaterial = new Tipomaterial();
    
    }

    public List<Tipomaterial> consultarTodos() {
        return tipomaterialFacade.findAll();
    }

    public String preActualizar(Tipomaterial tipomaterialActualizar) {
        tipomaterial = tipomaterialActualizar;
        return "actualizarTipoMaterial";
    }

    public void actualizar() {
        tipomaterialFacade.edit(tipomaterial);
        mensaje.setMensaje("MensajeRedirect('ListarTipoMaterial.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }

    public void eliminar(Tipomaterial tipomaterialEliminar) {
        tipomaterialFacade.remove(tipomaterialEliminar);
    }
}
