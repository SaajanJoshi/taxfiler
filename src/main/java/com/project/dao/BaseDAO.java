package com.project.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author saajanjoshi
 */
public class BaseDAO {
    protected EntityManagerFactory emf;

    public BaseDAO(String persistenceUnitName) {
        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
