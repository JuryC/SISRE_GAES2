/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Material;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Jury
 */
@Stateless
public class MaterialFacade extends AbstractFacade<Material> {

    @PersistenceContext(unitName = "SISREPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaterialFacade() {
        super(Material.class);
    }

    public List<Material> consultarTodos(int estado) {
        Query query = em.createQuery("SELECT m FROM Material m WHERE m.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    public List<Material> findByName(String name) {
        String $nombre = "%" + name.replaceAll(" ", "%") + "%";
        int $estado = 1;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Material> criteriaQuery = criteriaBuilder.createQuery(Material.class);
        Root<Material> material = criteriaQuery.from(Material.class);
        criteriaQuery.select(material);
        criteriaQuery.where(criteriaBuilder.like(material.get("nombre").as(String.class), $nombre),
                criteriaBuilder.equal(material.get("estado"), $estado));
        List<Material> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }

    public Material obtenerxNombre(String nombre) {
        Material material = null;
        try {
            Query query = em.createQuery("SELECT m FROM Material m WHERE m.estado=1 AND m.nombre=:nombre");
            query.setParameter("nombre", nombre);
            material = (Material) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
        return  material;
    }
    public Material obtenerxId(int id) {
        Material material = null;
        try {
            Query query = em.createQuery("SELECT m FROM Material m WHERE m.estado=1 AND m.idMaterial=:id");
            query.setParameter("id", id);
            material = (Material) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error Material: "+e.getMessage());
        }
        return  material;
    }
}
