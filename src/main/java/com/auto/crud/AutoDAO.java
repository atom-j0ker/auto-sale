package com.auto.crud;

import com.auto.model.Auto;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class AutoDAO {

    @PersistenceContext
    public EntityManager entityManager;

    public Long add(Auto auto) {
        long id = entityManager.merge(auto).getId();
        return id;
    }

    public void delete(long id) {
        entityManager.remove(entityManager.getReference(Auto.class, id));
    }

    public Auto get(long id) {
        return entityManager.find(Auto.class, id);
    }

    public void update(Auto auto) {
        entityManager.merge(auto);
    }

    public List<Auto> getAutoList() {
        TypedQuery<Auto> query = entityManager.createQuery(
                "select a from Auto a ", Auto.class
        );
        List<Auto> result = query.getResultList();
        return result;
    }

    public List<Auto> getAutoListByUser(long userId) {
        TypedQuery<Auto> query = entityManager.createQuery(
                "select a from Auto a where a.users=" + userId, Auto.class
        );
        List<Auto> result = query.getResultList();
        return result;
    }

    public List<Auto> getAutoListBySearch(String brandId, String modelId, String yearFrom, String yearTo,
                                          String mileageFrom, String mileageTo, String volumeFrom, String volumeTo,
                                          String transmission, String priceFrom, String priceTo, String sortBy) {
        if (brandId.equals("0"))
            brandId = "any(select b from Brand b)";
        if (modelId.equals("0"))
            modelId = "any(select m from Model m)";
        if (yearFrom.equals("0"))
            yearFrom = "1900";
        if (yearTo.equals("0"))
            yearTo = "2016";
        if (mileageFrom.equals(""))
            mileageFrom = "0";
        if (mileageTo.equals(""))
            mileageTo = "2147483647";
        if (volumeFrom.equals(""))
            volumeFrom = "0";
        if (volumeTo.equals(""))
            volumeTo = "2147483647";
        if (transmission.equals("0"))
            transmission = "any(select a.transmission from Auto a)";
        else
            transmission = "'" + transmission + "'";
        if (priceFrom.equals(""))
            priceFrom = "0";
        if (priceTo.equals(""))
            priceTo = "2147483647";
        switch (Integer.parseInt(sortBy)) {
            case 0: sortBy = "auto_id";
                break;
            case 1: sortBy = "auto_price asc";
                break;
            case 2: sortBy = "auto_price desc";
                break;
            default: sortBy = "auto_id";
                break;
        }

        String queryString = "select a from Auto a where a.brand=" + brandId + " and a.model=" + modelId +
                " and a.year between " + yearFrom + " and " + yearTo + " and a.mileage between " + mileageFrom + " and " + mileageTo +
                " and a.volume between " + volumeFrom + " and " + volumeTo + " and a.transmission=" + transmission +
                " and a.price between " + priceFrom + " and " + priceTo + " order by " + sortBy;
        System.out.println(queryString);
        TypedQuery<Auto> query = entityManager.createQuery(queryString, Auto.class);
        List<Auto> result = query.getResultList();
        return result;
    }
}
