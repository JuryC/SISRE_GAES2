/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Tipotransporte;
import facade.TipotransporteFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jury
 */
@Named (value="tipotransporteControlador")
@ManagedBean
@SessionScoped
public class TipoTransporteControlador {

    /**
     * Creates a new instance of TipoTransporteControlador
     */
    public TipoTransporteControlador() {
    }
    Tipotransporte tipotransporte;
    
    @Inject
    MensajeControlador mensaje;
    @EJB
    TipotransporteFacade tipotransporteFacade;

    public Tipotransporte getTipotransporte() {
        return tipotransporte;
    }

    public void setTipotransporte(Tipotransporte tipotransporte) {
        this.tipotransporte = tipotransporte;
    }
    public void registrar(){
        tipotransporte.setEstado(1);
        tipotransporteFacade.create(tipotransporte);
        mensaje.setMensaje("MensajeRedirect('ListarTipoTransporte.xhtml','Registro','El registro ha sido exitoso','success');");
    }
    public String preActualizar(Tipotransporte tipotransporteActualizar){
        tipotransporte = tipotransporteActualizar;
        return "actualizarTipotransporte";
    }
    public void actualizar(){
        tipotransporteFacade.edit(tipotransporte);
        mensaje.setMensaje("MensajeRedirect('ListarTipoTransporte.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }
    public void eliminar(Tipotransporte tipotransporteEliminar){
       tipotransporte = tipotransporteEliminar;
       tipotransporte.setEstado(2);
       tipotransporteFacade.edit(tipotransporte);
       
    }
    public List<Tipotransporte> consultarTodos(){
        return tipotransporteFacade.consultarTodos(1);
    }
}
