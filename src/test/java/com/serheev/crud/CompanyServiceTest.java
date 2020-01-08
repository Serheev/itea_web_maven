package com.serheev.crud;

import com.serheev.model.CompanyEntity;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CompanyServiceTest {
    private static Logger log = Logger.getLogger(CompanyServiceTest.class);

    private CompanyService service;
    private CompanyEntity newRecord;
    private CompanyEntity company;
    private CompanyEntity recordFromDB;

    @Before
    public void initDataBeforeTest() {
        service = new CompanyService();
        newRecord = new CompanyEntity();
        newRecord.setName("Mirohost");
        newRecord.setCountry("Ukraine");
        company = service.add(newRecord);
        recordFromDB = service.get(company.getId());
    }

    @After
    public void clearDataAfterTest() {
        recordFromDB = service.get(company.getId());
        if (recordFromDB != null) {
            service.delete(company.getId());
        }
    }

    @Test
    public void newRecordShouldBeAdded() {
//        log.info(company.getId() + " : " + company.getName() + " : " + company.getCountry());

        assertTrue(company.getId() == recordFromDB.getId());
        assertTrue(company.getName() == recordFromDB.getName());
        assertTrue(company.getCountry() == recordFromDB.getCountry());

//        log.info(recordFromDB.getId() + " : " + recordFromDB.getName() + " : " + recordFromDB.getCountry());
    }

    @Test
    public void newRecordShouldBeDeleted() {
        assertNotNull(recordFromDB);

        service.delete(recordFromDB.getId());

        recordFromDB = service.get(company.getId());
        assertNull(recordFromDB);
    }

    @Test
    public void recordShouldBeRead() {
        assertTrue(recordFromDB.getName() == "Mirohost");
        assertTrue(recordFromDB.getCountry() == "Ukraine");
    }

    @Test
    public void recordShouldBeUpdated() {
        long entryIdBeforeUpdate = recordFromDB.getId();

        recordFromDB.setName("Microsoft");
        recordFromDB.setCountry("Moldova");
        service.update(recordFromDB);

        long entryIdAfterUpdate = recordFromDB.getId();
        assertTrue(entryIdBeforeUpdate == entryIdAfterUpdate);
        assertTrue(recordFromDB.getName() == "Microsoft");
        assertTrue(recordFromDB.getCountry() == "Moldova");
    }

    @Test
    public void allRecordsShouldBeRead() {
        List<CompanyEntity> companies = service.getAll();
        companies.stream().map(r -> r.getId() + " : " + r.getName() + " : " + r.getCountry() + ";").forEach(log::info);
        assertTrue(companies.stream().count() == 5);
    }

}