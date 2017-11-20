package com.auto.crud;

import com.auto.model.Brand;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.List;

@Transactional
public class BrandDAO {

    @PersistenceContext
    public EntityManager entityManager;

    public Brand add(Brand brand) {
        return entityManager.merge(brand);
    }

    public void delete(long id) {
        entityManager.remove(entityManager.getReference(Brand.class, id));
    }

    public Brand get(long id) {
        return entityManager.find(Brand.class, id);
    }

    public void update(Brand brand) {
        entityManager.merge(brand);
    }

    public List<Brand> getBrandList() {
        TypedQuery<Brand> query = entityManager.createQuery(
                "select b from Brand b order by b.name", Brand.class
        );
        List<Brand> result = query.getResultList();
        return result;
    }

    public long getBrandIdByBrandName(String brandName) {
        Query query = entityManager.createQuery(
                "select b.id from Brand b where b.name= :brandName"
        ).setParameter("brandName", brandName);
        long result = (Long)query.getSingleResult();
        return result;
    }

}
