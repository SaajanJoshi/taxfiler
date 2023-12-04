package com.project.beans;

import com.project.dao.UserDAO;
import com.project.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author saajanjoshi
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(LoginBean.class.getName());
    private String username;
    private String password;

    private User loggedInUser;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    // Getters and Setters

    public String login() {
        // Implement login logic using UserDAO
        // For simplicity, let's assume you have a UserDAO.loginUser(username, password) method
        // that returns the logged-in user or null if authentication fails.
        LOGGER.log(Level.INFO, "Attempting login for user: {0}", username);
        // Example usage:
        UserDAO userDAO = new UserDAO();
        User user = userDAO.loginUser(username, password);
        System.out.println(user);
        if (user != null) {
            // Set user session information if needed
            // For example, you can store the user object in the session
            // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);

            loggedInUser = user;
            LOGGER.log(Level.INFO, "Loggin in :", loggedInUser);
            return "taxfiledetail?faces-redirect=true"; // x  to a success
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null));
            return null;
        }
    }

    public String logout(){
        loggedInUser = null;
        return "login?faces-redirect=true";
    }
}
