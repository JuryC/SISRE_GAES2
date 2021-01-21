/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario loginUser(Long documento, String password) {
        Usuario usuario = null;
        try {
            Query query;
            query = em.createQuery("SELECT u FROM Usuario u WHERE u.documento=:doc AND u.password=:pass AND u.estado=1");
            query.setParameter("doc", documento);
            query.setParameter("pass", password);
            usuario = (Usuario) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return usuario;
    }

    public List<Usuario> consultarTodos(int estado) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    //Consulta para obterner un usuario según sus Id
    public List<Usuario> obtenerUsuario(int id) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=1 AND u.idUsuario=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Usuario> obtenerUsuarioRol(int rol) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=1 AND u.idRol.idRol=:rol");
        query.setParameter("rol", rol);
        return query.getResultList();
    }

    public Usuario validarDocumento(int docu) {
        Usuario usuario = null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=1 AND u.documento=:docu");
            query.setParameter("docu", docu);
            usuario = (Usuario) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return usuario;
    }
    
    public int contarUsuario(int id) {
        Query q = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.idRol.idRol=:id");
        q.setParameter("id", id);
        return ((Long) q.getSingleResult()).intValue();
    }
}
