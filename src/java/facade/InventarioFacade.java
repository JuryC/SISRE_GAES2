/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Inventario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jury
 */
@Stateless
public class InventarioFacade extends AbstractFacade<Inventario> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioFacade() {
        super(Inventario.class);
    }

    public List<Inventario> consultarTodos(int estado) {
        Query query = em.createQuery("SELECT i FROM Inventario i WHERE i.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }

     public List<Inventario> consultarExistencias() {
        Query query = em.createQuery("SELECT i FROM Inventario i WHERE i.estado=1 AND i.existencia>0");
        return query.getResultList();
    }
    
    public Inventario obtenerValor(int id) {
        Inventario inventario = null;
        try {
            Query query = em.createQuery("SELECT i FROM Inventario i WHERE i.estado=1 AND i.articulo.idMaterial=:id");
            query.setParameter("id", id);
            inventario = (Inventario) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error Inventario: " + e.getMessage());
        }
        return inventario;
    }

}
