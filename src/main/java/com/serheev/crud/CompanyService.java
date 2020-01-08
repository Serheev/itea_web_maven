package com.serheev.crud;

import com.serheev.model.CompanyEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CompanyService {
    private EntityManager em = Persistence.createEntityManagerFactory("DEVCOMPANY").createEntityManager();

    public CompanyEntity add(CompanyEntity company) {
        em.getTransaction().begin();
        CompanyEntity companyFromDB = em.merge(company);
        em.getTransaction().commit();
        return companyFromDB;
    }

    public CompanyEntity get(long id) {
        return em.find(CompanyEntity.class, id);
    }

    public void update(CompanyEntity company) {
        em.getTransaction().begin();
        em.merge(company);
        em.getTransaction().commit();
    }

    public void delete(long id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public List<CompanyEntity> getAll() {
        TypedQuery<CompanyEntity> namedQuery = em.createNamedQuery("CompanyEntity.getAll", CompanyEntity.class);
        return namedQuery.getResultList();
    }
}
