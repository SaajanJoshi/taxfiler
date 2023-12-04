package com.project.dao;

import com.project.model.TaxFiler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


import java.util.List;

/**
 * @author saajanjoshi
 */
public class TaxFilerDAO extends BaseDAO {
    public TaxFilerDAO() {
        super("TaxFilerPU");
    }

    public List<TaxFiler> getTaxFilesForUser(int userId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT t FROM TaxFiler t WHERE t.userId = :userId");
             query.setParameter("userId", userId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    public List<TaxFiler> getAllTaxFilers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT t FROM TaxFiler t");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void addTaxFiler(TaxFiler taxFiler) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(taxFiler);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void updateTaxFiler(TaxFiler taxFiler) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(taxFiler);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deleteTaxFiler(int filerID) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TaxFiler taxFiler = em.find(TaxFiler.class, filerID);
            if (taxFiler != null) {
                em.remove(taxFiler);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
