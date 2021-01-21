/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Transporte;
import entidades.Usuario;
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
public class TransporteFacade extends AbstractFacade<Transporte> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransporteFacade() {
        super(Transporte.class);
    }

    public List<Transporte> consultarTodos(int estado) {
        Query query = em.createQuery("SELECT t FROM Transporte t WHERE t.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    public List<Transporte> consultarDisponibles() {
        Query query = em.createQuery("SELECT t FROM Transporte t WHERE t.estado=1 AND t.disponibilidad='Disponible'");
        return query.getResultList();
    }

    public Transporte obtenerTransporte(int id) {
        Transporte transporte = null;
        try {
            Query query = em.createQuery("SELECT t FROM Transporte t WHERE t.estado=1");
            query.setParameter("id", id);
            transporte = (Transporte) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
        }
        return transporte;
    }
}
