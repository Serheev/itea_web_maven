package com.serheev.crud;

import com.serheev.model.CompanyEntity;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Lifecycle {
    private static Logger log = Logger.getLogger(Lifecycle.class);
    private EntityManager em = Persistence.createEntityManagerFactory("DEVCOMPANY").createEntityManager();

    private CompanyEntity company;

    public CompanyEntity createCompany(String name, String country) {
        company = new CompanyEntity();
        company.setName(name);
        company.setCountry(country);
        log.info("NEW: " + company.toString());
        return company;
    }

    public void addRecordToDB(CompanyEntity c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        log.info("MANAGED (added to a database): " + c.toString());
    }

    public void updateRecord(CompanyEntity c) {
        em.getTransaction().begin();
        em.merge(c);
        log.info("UPDATE: " + c);
        em.getTransaction().commit();
    }

    public CompanyEntity getRecord(long id) {
        log.info("GET RECORD BY ID FROM DB: " + em.find(CompanyEntity.class, id));
        return em.find(CompanyEntity.class, id);
    }

    public void detachRecord(CompanyEntity c) {
        em.detach(c);
        log.info("DETACHED: " + c);
    }

    //    REMOVED
    public void removeRecord(CompanyEntity c) {
        em.getTransaction().begin();
        em.remove(c);
        log.info("REMOVE: " + c);
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        Lifecycle stage = new Lifecycle();
        CompanyEntity tetrapack = stage.createCompany("Tetra Pak", "Switzerland");

        stage.addRecordToDB(tetrapack);
        CompanyEntity companyFromDB = stage.getRecord(tetrapack.getId());
        companyFromDB.setName("Nestle");
        stage.updateRecord(companyFromDB);
        stage.detachRecord(companyFromDB);
        companyFromDB.setName("Rolex");
        stage.updateRecord(companyFromDB);
        companyFromDB = stage.getRecord(companyFromDB.getId());
        companyFromDB.setName("Tetra Pak Again");
        stage.updateRecord(companyFromDB);
        companyFromDB = stage.getRecord(companyFromDB.getId());
        stage.removeRecord(companyFromDB);
    }
}
