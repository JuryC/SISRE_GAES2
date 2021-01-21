/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Rolpermiso;
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
public class RolpermisoFacade extends AbstractFacade<Rolpermiso> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolpermisoFacade() {
        super(Rolpermiso.class);
    }
    public List<Rolpermiso> consultarTodos(int estado){
        Query query =em.createQuery("SELECT rp FROM Rolpermiso re WHERE re.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }
}
