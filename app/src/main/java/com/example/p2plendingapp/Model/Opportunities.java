package com.example.p2plendingapp.Model;

public class Opportunities {
    private int id;
    private String borrowerId;
    private double borrowAmount;
    private int borrowPeriod;
    private String riskLvl;
    private double eEarnings;
    private Boolean checked;


    public Opportunities() {

    }

    public Opportunities(int id, String bId, double bA, int bP, String rL, double e, Boolean c) {
        this.id = id;
        this.borrowerId = bId;
        this.borrowAmount = bA;
        this.borrowPeriod = bP;
        this.riskLvl = rL;
        this.eEarnings = e;
        this.checked = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public double getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(double borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public int getBorrowPeriod() {
        return borrowPeriod;
    }

    public void setBorrowPeriod(int borrowPeriod) {
        this.borrowPeriod = borrowPeriod;
    }

    public String getRiskLvl() {
        return riskLvl;
    }

    public void setRiskLvl(String riskLvl) {
        this.riskLvl = riskLvl;
    }

    public double geteEarnings() {
        return eEarnings;
    }

    public void seteEarnings(double eEarnings) {
        this.eEarnings = eEarnings;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

}
