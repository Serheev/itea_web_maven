package com.serheev.crud;

import com.serheev.annotation.InjectRandomValue;
import com.serheev.model.DeveloperEntity;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DeveloperServiceTest {
    private static Logger log = Logger.getLogger(DeveloperServiceTest.class);

    private DeveloperService service;
    private DeveloperEntity newRecord;
    private DeveloperEntity developer;
    private DeveloperEntity recordFromDB;

    @Before
    public void initDataBeforeTest() {
        service = new DeveloperService();
        newRecord = new DeveloperEntity();
        newRecord.setName("Sarah Connor");
        newRecord.setAge(25);
        newRecord.setSex('w');
        newRecord.setSalary(33333);
        newRecord.setOnLeave(true);
        developer = service.add(newRecord);
        recordFromDB = service.get(developer.getId());
    }

    @After
    public void clearDataAfterTest() {
        recordFromDB = service.get(developer.getId());
        if (recordFromDB != null) {
            service.delete(developer.getId());
        }
    }

    @Test
    public void newRecordShouldBeAdded() {
        assertTrue(developer.getId() == recordFromDB.getId());
        assertTrue(developer.getName() == recordFromDB.getName());
        assertTrue(developer.getAge() == recordFromDB.getAge());
        assertTrue(developer.getSex() == recordFromDB.getSex());
    }

    @Test
    public void newRecordShouldBeDeleted() {
        assertNotNull(recordFromDB);

        service.delete(recordFromDB.getId());

        recordFromDB = service.get(developer.getId());
        assertNull(recordFromDB);
    }

    @Test
    public void recordShouldBeRead() {
        assertTrue(recordFromDB.getName() == "Sarah Connor");
        assertTrue(recordFromDB.getAge() == 25);
    }

    @Test
    public void recordShouldBeUpdated() {
        long entryIdBeforeUpdate = recordFromDB.getId();

        recordFromDB.setName("Anna Karenina");
        recordFromDB.setAge(30);
        service.update(recordFromDB);

        long entryIdAfterUpdate = recordFromDB.getId();
        assertTrue(entryIdBeforeUpdate == entryIdAfterUpdate);
        assertTrue(recordFromDB.getName() == "Anna Karenina");
        assertTrue(recordFromDB.getAge() == 30);
    }

    @Test
    public void allRecordsShouldBeRead() {
        List<DeveloperEntity> developers = service.getAll();
        developers.stream().map(r -> r.getId() + " : " + r.getName() + " : " + r.getAge() + ";").forEach(log::info);
        assertTrue(developers.stream().count() == 7);
    }

    @Test
    public void shouldMakeRandomMinMaxValue() {
        try {
            Class clazz = Class.forName("com.serheev.model.DeveloperEntity");
            for (Field field : clazz.getDeclaredFields()) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().equals(InjectRandomValue.class) && annotation instanceof InjectRandomValue) {
                        if (field.getType() == Integer.TYPE) {
                            InjectRandomValue randomValue = (InjectRandomValue) annotation;
                            int min = randomValue.min();
                            int max = randomValue.max();
                            field.setAccessible(true);
                            int beforeRandom = (int) field.get(developer);
                            field.set(developer, (int) ((Math.random() * ((max - min) + 1)) + min));
                            int afterRandom = (int) field.get(developer);
                            log.info(beforeRandom + " : " + afterRandom);

                            assertFalse(beforeRandom == afterRandom);
                        }
                        if (field.getType() == String.class) {
                            field.setAccessible(true);
                            String beforeString = (String) field.get(developer);
                            String afterString = (String) field.get(developer);
                            log.info(beforeString + " : " + afterString);

                            assertTrue(beforeString == afterString);
                        }
                        if (field.getType() == Boolean.TYPE) {
                            field.setAccessible(true);
                            Boolean beforeBoolean = (Boolean) field.get(developer);
                            field.set(developer, (beforeBoolean) ? false : true);
                            Boolean afterBoolean = (Boolean) field.get(developer);
                            log.info(beforeBoolean + " : " + afterBoolean);

                            assertFalse(beforeBoolean == afterBoolean);
                            assertFalse(afterBoolean);
                        }
                    }
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException e) {
            log.error("Error " + e);
        }
    }

}