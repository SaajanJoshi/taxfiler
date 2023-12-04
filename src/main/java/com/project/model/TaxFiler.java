package com.project.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author saajanjoshi
 */
@Entity
@Table(name = "Taxfilers")
public class TaxFiler implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FilerID")
    private int filerID;

    @Column(name = "Contact")
    private String contact;

    @Column(name = "AnnualIncome")
    private BigDecimal annualIncome;

    @Column(name = "Expenses")
    private BigDecimal expenses;

    @Column(name = "TaxYear")
    private Integer taxYear;

    @Column(name = "UserID")
    private int userId;

    public TaxFiler() {
    }

    public TaxFiler(String contact, BigDecimal annualIncome, BigDecimal expenses, int taxYear, int currentUser) {
        this.contact = contact;
        this.annualIncome = annualIncome;
        this.expenses = expenses;
        this.taxYear = taxYear;
        this.userId = currentUser;
    }

    public int getFilerID() {
        return filerID;
    }

    public void setFilerID(int filerID) {
        this.filerID = filerID;
    }

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

    public Integer getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(Integer taxYear) {
        this.taxYear = taxYear;
    }

    public int getUserId() {
        return userId;
    }

    public void setUser(Integer userId) {
        this.userId = userId;
    }
}
