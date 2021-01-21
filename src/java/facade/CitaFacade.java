/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Cita;
import java.util.Date;
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
public class CitaFacade extends AbstractFacade<Cita> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaFacade() {
        super(Cita.class);
    }

    public List<Cita> consultarTodos(int estado) {
        Query query = em.createQuery("SELECT ci FROM Cita ci WHERE ci.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    //Consulta para buscar solo las citas de un usuario en espécifico
    public List<Cita> consultarCitasUsuario(int id) {
        Query query = em.createQuery("SELECT ci FROM Cita ci WHERE ci.estado=1 AND ci.idCliente.idCliente=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    //Consulta para buscar solo las citas de un operario en espécifico
    public List<Cita> consultarCitasOp(int id, Date fecha) {
        Query query = em.createQuery("SELECT ci FROM Cita ci WHERE ci.estado=1 AND ci.operarioCargo.idUsuario=:id AND ci.fechaCita!=:fecha");
        query.setParameter("id", id);
        query.setParameter("fecha", fecha);
        return query.getResultList();
    }

    //Consulta para buscar solo las citas a las cuales no se les ha asignado un operario
    public List<Cita> consultarCitasOpNull() {
        Query query = em.createQuery("SELECT ci FROM Cita ci WHERE ci.estado=1 AND ci.operarioCargo=null");
        return query.getResultList();
    }

    public List<Cita> consultarCitasOpNotNull() {
        Query query = em.createQuery("SELECT ci FROM Cita ci WHERE ci.estado=1 AND ci.operarioCargo!=null");
        return query.getResultList();
    }

    public int contarCitaxOp(int id) {
        Query q = em.createQuery("SELECT COUNT(ci) FROM Cita ci WHERE ci.estado=1 AND ci.operarioCargo.idUsuario=:id");
        q.setParameter("id", id);
        return ((Long) q.getSingleResult()).intValue();

    }
    public Cita validarCita(int id) {
        Cita cita = null;
        try {
            Query query = em.createQuery("SELECT ci FROM Cita ci WHERE u.estado=1 AND ci.id=:id");
            query.setParameter("id", id);
            cita = (Cita) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return cita;
    }

}
