package com.project.beans;

import com.project.dao.TaxFilerDAO;
import com.project.model.TaxFiler;
import com.project.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
        TaxFilerDAO taxFilerDAO = new TaxFilerDAO();
        System.out.println(loginBean.getLoggedInUser().getUserID());
        taxFiles = taxFilerDAO.getTaxFilesForUser(loginBean.getLoggedInUser().getUserID());
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
        // Create a new tax file
        TaxFiler newTaxFile = new TaxFiler(contact, annualIncome, expenses, taxYear, this.currentUser.getUserID());

        // Add the new tax file to the list
        taxFiles.add(newTaxFile);

        TaxFilerDAO taxFilerDAO = new TaxFilerDAO();
        taxFilerDAO.addTaxFiler(newTaxFile);

        return "taxfiledetail?faces-redirect=true"; // Redirect to a success
    }
}
