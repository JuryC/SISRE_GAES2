/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.CitaMaterial;
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
public class CitaMaterialFacade extends AbstractFacade<CitaMaterial> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaMaterialFacade() {
        super(CitaMaterial.class);
    }

    public List<CitaMaterial> obtenerMateriales(int id) {
        Query query = em.createQuery("SELECT c FROM CitaMaterial c WHERE c.idCita.idCita=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public CitaMaterial validarSolicitud(int idCita, int idMaterial) {
        CitaMaterial CM = null;
        try {
            Query query = em.createQuery("SELECT c FROM CitaMaterial c WHERE c.idCita.idCita=:idCita AND c.idMaterial.idMaterial=:idMaterial");
            query.setParameter("idMaterial", idMaterial);
            query.setParameter("idCita", idCita);
            CM = (CitaMaterial) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return CM;
    }

}
