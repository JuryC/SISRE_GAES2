/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.MaterialCantidad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kesgr
 */
@Stateless
public class MaterialCantidadFacade extends AbstractFacade<MaterialCantidad> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaterialCantidadFacade() {
        super(MaterialCantidad.class);
    }
    
    public List<MaterialCantidad> obtenerxMaterial(int id){
        Query query = em.createQuery("SELECT m FROM MaterialCantidad m WHERE m.material.idMaterial=:id ORDER BY m.fechaIngreso DESC");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
}
