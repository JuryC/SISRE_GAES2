/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Contrato;
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
public class ContratoFacade extends AbstractFacade<Contrato> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoFacade() {
        super(Contrato.class);
    }
    
    public List<Contrato> contratosPropios(int id){
        Query query = em.createQuery("SELECT c FROM Contrato c WHERE c.estado=1 AND c.creadoPor.idUsuario=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public Contrato obtenerContrato(int id){
        Contrato contrato = null;
        try {
            Query query = em.createQuery("SELECT c FROM Contrato c WHERE c.estado=1 AND c.idContrato=:id");
            query.setParameter("id", id);
            contrato = (Contrato) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
        return contrato;
    }
    
}
