package com.project.dao;

import com.project.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


import java.util.List;
/**
 * @author saajanjoshi
 */
public class UserDAO extends BaseDAO{
    public UserDAO() {
        super("TaxFilerPU");
    }
    public boolean registerUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            // Handle registration failure (e.g., username or email already exists)
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public User loginUser(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<User> resultList = query.getResultList();

            if (!resultList.isEmpty()) {
                return resultList.get(0);
            } else {
                return null; // User not found
            }
        } finally {
            em.close();
        }
    }
}
