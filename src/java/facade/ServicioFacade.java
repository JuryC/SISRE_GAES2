/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Servicio;
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
public class ServicioFacade extends AbstractFacade<Servicio> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicioFacade() {
        super(Servicio.class);
    }
    public List<Servicio> consultarTodos(int estado){
        Query query =em.createQuery("SELECT se FROM Servicio se WHERE se.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    public List<Servicio> consultarEstado(String estado){
        Query query =em.createQuery("SELECT se FROM Servicio se WHERE se.estado=1 AND se.estadoServicio=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }
}
