package com.auto.crud;

import com.auto.model.Model;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class ModelDAO {

    @PersistenceContext
    public EntityManager entityManager;

    public Model add(Model model) {
        return entityManager.merge(model);
    }

    public void delete(long id) {
        entityManager.remove(entityManager.getReference(Model.class, id));
    }

    public Model get(long id) {
        return entityManager.find(Model.class, id);
    }

    public void update(Model model) {
        entityManager.merge(model);
    }

    public List<Model> getModelListByBrand(long id) {
        TypedQuery<Model> query = entityManager.createQuery(
                "select m from Model m where m.brand=" + id + " order by m.name", Model.class
        );
        List<Model> result = query.getResultList();
        return result;
    }

    public long getModelIdByModelName(String modelName, long brandId) {
        Query query = entityManager.createQuery(
                "select m.id from Model m where m.name='" + modelName + "' and m.brand='" + brandId + "'"
        );
        long result = (Long)query.getSingleResult();
        return result;
    }

}
