/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Permiso;
import java.util.ArrayList;
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
public class PermisoFacade extends AbstractFacade<Permiso> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisoFacade() {
        super(Permiso.class);
    }
     public List<Permiso> consultarHijos(int idPadre){
        List<Permiso> listaPerPadre = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT p FROM Permiso p WHERE p.permisoPadre.idPermiso=:per");
            q.setParameter("per", idPadre);
            listaPerPadre = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
            return listaPerPadre;
    }
     public List<Permiso> consultarTodos(int estado){
         Query query =em.createQuery("SELECT p FROM Permiso WHERE p.estado=:estado");
         query.setParameter("estado", estado);
         return query.getResultList();
     }
}
