/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Envio;
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
public class EnvioFacade extends AbstractFacade<Envio> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnvioFacade() {
        super(Envio.class);
    }
    public List<Envio> consultarTodos(int estado){
       Query query =em.createQuery("SELECT e FROM Envio e WHERE e.estado=:estado");
       query.setParameter("estado", estado);
       return query.getResultList();
    }
    public Envio obtenerEnvio(int id) {
        Envio envio = null;
        try {
            Query q = em.createQuery("SELECT e FROM Envio e WHERE e.estado=1 AND e.idEnvio=:id");
            q.setParameter("id", id);
            envio = (Envio) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return envio;
    }
}
