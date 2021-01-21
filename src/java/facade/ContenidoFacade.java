/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Contenido;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author kesgr
 */
@Stateless
public class ContenidoFacade extends AbstractFacade<Contenido> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContenidoFacade() {
        super(Contenido.class);
    }

    public List<Contenido> obtenerTitulos(int idContrato) {
        Query query = em.createQuery("SELECT c FROM Contenido c WHERE c.idContrato.idContrato=:id");
        query.setParameter("id", idContrato);
        return query.getResultList();
    }

    public Contenido obtenerTitulo(int id) {
        Contenido contenido = null;
        try {
            Query query = em.createQuery("SELECT c FROM Contenido c WHERE c.idParrafo=:id");
            query.setParameter("id", id);
            contenido = (Contenido) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return contenido;
    }

}
