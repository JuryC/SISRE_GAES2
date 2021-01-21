/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Material;
import entidades.Usuario;
import entidades.CitaMaterial;
import facade.MaterialFacade;
import facade.CitaMaterialFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Jury
 */
@Named(value = "citaMaterialControlador")
@SessionScoped
public class Cita_MaterialControlador implements Serializable {

    /**
     * Creates a new instance of UsuarioMaterialControlador
     */
    public Cita_MaterialControlador() {
    }
    CitaMaterial citamaterial;
    Material material;
    Usuario usuario;

@PostConstruct
public void init(){
citamaterial = new CitaMaterial();
material = new Material();
}

@EJB
CitaMaterialFacade citamaterialFacade;
@EJB
MaterialFacade materialFacade;

    public CitaMaterial getUsuariomaterial() {
        return citamaterial;
    }

    public void setUsuariomaterial(CitaMaterial usuariomaterial) {
        this.citamaterial = usuariomaterial;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

public String registrar(){
    citamaterial.setIdMaterial(material);
    citamaterialFacade.create(citamaterial);
    
    return "ListarUsuarioMaterial";
}
public List<CitaMaterial> consultarTodos(){
    return citamaterialFacade.findAll();
}
public String preActualizar(CitaMaterial usuariomaterialActualizar){
    material = usuariomaterialActualizar.getIdMaterial();
    citamaterial = usuariomaterialActualizar;
    return "ActualizarUsuarioMaterial";
}
public String actualizar(){
    citamaterial.setIdMaterial(material);
    citamaterialFacade.edit(citamaterial);
    return "ListarUsuarioMaterial";
}
public void eliminar(CitaMaterial usuariomaterialEliminar){
    citamaterialFacade.remove(usuariomaterialEliminar);
}
}