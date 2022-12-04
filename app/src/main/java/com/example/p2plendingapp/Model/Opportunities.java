package com.example.p2plendingapp.Model;

public class Opportunities {
    private int opId;
    private double borrowAmount;
    private double mEarnings;
    private int borrowPeriod;
    private String riskLvl;
    private Boolean checked;


    public Opportunities() {

    }

    public Opportunities(int opId, double bA, double mE, int bP, String rL, Boolean c) {
        this.opId = opId;
        this.borrowAmount = bA;
        this.mEarnings = mE;
        this.borrowPeriod = bP;
        this.riskLvl = rL;
        this.checked = c;
    }



    public int getOpId() {
        return opId;
    }

    public double getBorrowAmount() {
        return borrowAmount;
    }


    public int getBorrowPeriod() {
        return borrowPeriod;
    }


    public String getRiskLvl() {
        return riskLvl;
    }


    public double seteEarnings() {
        return this.borrowPeriod * this.mEarnings;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

}
