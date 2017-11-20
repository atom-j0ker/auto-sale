package com.auto.crud;

import com.auto.model.Users;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Transactional
public class UsersDAO {
    @PersistenceContext
    public EntityManager entityManager;

    public Users add(Users users) {
        return entityManager.merge(users);
    }

    public void delete(long id) {
        entityManager.remove(entityManager.getReference(Users.class, id));
    }

    public Users get(long id) {
        return entityManager.find(Users.class, id);
    }

    public void update(Users users) {
        entityManager.merge(users);
    }

    public long getUserIdByUsername(String username) {
        Query query = entityManager.createQuery(
                "select u.id from Users u where u.userEmbeddable.username= :username").setParameter("username", username);
        long result = (Long)query.getSingleResult();
        return result;
    }

}
