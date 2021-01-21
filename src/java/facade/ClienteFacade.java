/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Cliente;
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
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    public List<Cliente> consultarTodos(int estado){
        Query query =em.createQuery("SELECT c FROM Cliente c WHERE c.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    public List<Cliente> obtenerCliente(int id){
        Query query =em.createQuery("SELECT u FROM Cliente u WHERE u.estado=1 AND u.usuario.idUsuario=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }
}
