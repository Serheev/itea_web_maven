package com.serheev.crud;

import com.serheev.model.DeveloperEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DeveloperService {
    private EntityManager em = Persistence.createEntityManagerFactory("DEVCOMPANY").createEntityManager();

    public DeveloperEntity add(DeveloperEntity developer) {
        em.getTransaction().begin();
        DeveloperEntity developerFromDB = em.merge(developer);
        em.getTransaction().commit();
        return developerFromDB;
    }

    public DeveloperEntity get(long id) {
        return em.find(DeveloperEntity.class, id);
    }

    public void update(DeveloperEntity developer) {
        em.getTransaction().begin();
        em.merge(developer);
        em.getTransaction().commit();
    }

    public void delete(long id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public List<DeveloperEntity> getAll() {
        TypedQuery<DeveloperEntity> namedQuery = em.createNamedQuery("DeveloperEntity.getAll", DeveloperEntity.class);
        return namedQuery.getResultList();
    }
}
