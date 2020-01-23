package com.serheev.crud;

import com.serheev.model.ProjectEntity;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueriesTest {
    private static org.apache.log4j.Logger log = Logger.getLogger(Queries.class);
    private Queries query;

    @Before
    public void SetUp() {
        query = new Queries();
    }

    @Test
    public void mostExpensiveProjectBySalaries() {
        for (Object[] r : query.mostExpensiveProjectBySalaries()) {
            String name = (String) r[0];
            Long salaries = (Long) r[1];
            log.info("The most expensive project for developer's salaries = " + name + " : " + salaries);
            assertEquals("google.com", name);
        }
    }

    @Test
    public void totalSalaryJavaDevelopers() {
        for (Object[] r : query.totalSalaryJavaDevelopers()) {
            String skill = (String) r[0];
            Long salaries = (Long) r[1];
            log.info("The total salary for Java developers = " + skill + " : " + salaries);
            assertTrue(31500 == salaries);
        }
    }

    @Test
    public void cheapestProjectByCost() {
        for (ProjectEntity r : query.cheapestProjectByCost()) {
            int id = (int) r.getId();
            String name = (String) r.getName();
            int cost = (int) r.getCost();
            log.info("The cheapest project for the price = " + id + " : " + name + " : " + cost);
            assertEquals("ebay.com", name);
        }
    }

    @Test
    public void averageSalaryOfCheapestProject() {
        for (Object[] r : query.averageSalaryOfCheapestProject()) {
            String name = (String) r[0];
            Double salary = (Double) r[1];
            log.info("The average salary of the developer of the cheapest project = " + name + " : " + salary);
            assertTrue(6933.33 == salary);
        }
    }
}