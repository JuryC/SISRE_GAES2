/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jury
 */
@Named(value = "idiomaControlador")
@SessionScoped
public class IdiomaControlador implements Serializable {

    /**
     * Creates a new instance of IdiomaControlador
     */
    public IdiomaControlador() {
    }
    private Locale idiomaSeleccionado;
    private Locale espanol;
    private Locale ingles;

    public Locale getEspanol() {
        return espanol;
    }

    public void setEspanol(Locale espanol) {
        this.espanol = espanol;
    }

    public Locale getIngles() {
        return ingles;
    }

    public void setIngles(Locale ingles) {
        this.ingles = ingles;
    }
    @PostConstruct
     public void init(){
         espanol =new Locale("es");
         ingles = new Locale("en");
         idiomaSeleccionado = new Locale("es");
     }

    public Locale getIdiomaSeleccionado() {
        return idiomaSeleccionado;
    }

    public void setIdiomaSeleccionado(Locale idiomaSeleccionado) {
        this.idiomaSeleccionado = idiomaSeleccionado;
    }
     
   public String cambiarIdioma(String idiomas){
       Locale idioma = new Locale(idiomas);
       if(idioma!= null){
           this.idiomaSeleccionado = idioma;
           FacesContext.getCurrentInstance().getViewRoot().setLocale(idiomaSeleccionado);
       }
       return "";
   }
}
