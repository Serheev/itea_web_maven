package com.serheev.crud;

import com.serheev.model.ProjectEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Queries {
    private static EntityManager em = Persistence.createEntityManagerFactory("DEVCOMPANY").createEntityManager();

    public List<Object[]> mostExpensiveProjectBySalaries() {
        Query query = em.createQuery("SELECT project.name, SUM(developer.salary) AS salaries_sum " +
                "FROM ProjectEntity project " +
                "LEFT JOIN project.developers developer " +
                "GROUP BY project.name " +
                "ORDER BY salaries_sum DESC");
        return (List<Object[]>) query.setMaxResults(1).getResultList();
    }

    public List<Object[]> totalSalaryJavaDevelopers() {
        Query query = em.createQuery("SELECT skill.industry, SUM(developer.salary) AS salaries_sum " +
                "FROM SkillEntity skill " +
                "LEFT JOIN skill.developers developer " +
                "WHERE skill.industry = 'Java' " +
                "GROUP BY skill.industry");
        return (List<Object[]>) query.getResultList();
    }

    public List<ProjectEntity> cheapestProjectByCost() {
        Query query = em.createQuery("SELECT p FROM ProjectEntity p " +
                "WHERE cost = (SELECT MIN(cost) FROM ProjectEntity)");
        return (List<ProjectEntity>) query.getResultList();
    }

    public List<Object[]> averageSalaryOfCheapestProject() {
        Query query = em.createQuery("SELECT project.name, ROUND(AVG(developer.salary),2) AS average_salary " +
                "FROM ProjectEntity AS project " +
                "LEFT JOIN project.developers developer " +
                "GROUP BY project.name, project.cost " +
                "ORDER BY project.cost ASC");
        return (List<Object[]>) query.setMaxResults(1).getResultList();
    }
}
