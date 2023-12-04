package com.project.beans;

import com.project.dao.UserDAO;
import com.project.model.User;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * @author saajanjoshi
 */
@ManagedBean
@SessionScoped
public class RegisterBean implements Serializable {
    private String firstName;
    private String username;
    private String password;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters and Setters

    public String register() {
        // Implement registration logic using UserDAO
        // For simplicity, let's assume you have a UserDAO.registerUser(user) method
        // that adds a new user to the database.

        // Example usage:
        UserDAO userDAO = new UserDAO();
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        if (userDAO.registerUser(newUser)) {
            return "success"; // Redirect to a success page
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed", null));
            return null;
        }
    }
}
