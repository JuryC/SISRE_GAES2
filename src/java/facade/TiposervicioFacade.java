/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Tiposervicio;
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
public class TiposervicioFacade extends AbstractFacade<Tiposervicio> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposervicioFacade() {
        super(Tiposervicio.class);
    }
    public List<Tiposervicio> consultarTodos(int estado) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    public Tiposervicio obtenerTiposervicio(int id) {
        Tiposervicio tipo = null;
        try {
            Query q = em.createQuery("SELECT e FROM Tiposervicio e WHERE e.estado=1 AND e.idTipoServicio=:id");
            q.setParameter("id", id);
            tipo = (Tiposervicio) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return tipo;
    }
}
