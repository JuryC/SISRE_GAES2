/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Jury
 */
@Named(value = "mensajeControlador")
@RequestScoped
public class MensajeControlador {

    /**
     * Creates a new instance of MensajeControlador
     */
    public MensajeControlador() {
    }
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
        
    
}
