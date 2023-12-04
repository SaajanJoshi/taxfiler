package com.project.beans;

import com.project.dao.TaxFilerDAO;
import com.project.model.TaxFiler;
import com.project.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author saajanjoshi
 */
@ManagedBean
@SessionScoped
public class TaxFileBean {

    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    private String contact;
    private BigDecimal annualIncome;
    private BigDecimal expenses;
    private int taxYear;
    private User currentUser;

    // List to store existing tax files
    private List<TaxFiler> taxFiles = new ArrayList<>();

    // Getters and setters

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(int taxYear) {
        this.taxYear = taxYear;
    }

    public List<TaxFiler> getTaxFiles() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        if (facesContext.getResponseComplete()) {
            return null; // Response is already complete, return null
        }

        TaxFilerDAO taxFilerDAO = new TaxFilerDAO();
        this.currentUser= loginBean.getLoggedInUser();
        if (this.currentUser==null){
            // Redirect to the login page
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception as needed
            }
            return null; // Return null since the redirect has already occurred
        }
        taxFiles = taxFilerDAO.getTaxFilesForUser(this.currentUser.getUserID());
        return taxFiles;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setTaxFiles(List<TaxFiler> taxFiles) {
        this.taxFiles = taxFiles;
    }

    // Method to create a new tax file
    public String createNewTaxFile() {
        // Validate input if necessary

        this.currentUser = loginBean.getLoggedInUser();

        if (this.currentUser == null){
            return "login?faces-redirect=true";
        }
        // Create a new tax file
        TaxFiler newTaxFile = new TaxFiler(contact, annualIncome, expenses, taxYear, this.currentUser.getUserID());

        // Add the new tax file to the list
        taxFiles.add(newTaxFile);

        TaxFilerDAO taxFilerDAO = new TaxFilerDAO();
        taxFilerDAO.addTaxFiler(newTaxFile);

        return "taxfiledetail?faces-redirect=true"; // Redirect to a success
    }
}
