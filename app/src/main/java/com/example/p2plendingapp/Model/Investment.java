package com.example.p2plendingapp.Model;

public class Investment  {

    private Integer iId;
    private double sFee;
    private double mEarnings;
    private Boolean aDeal;
    private Boolean aTerms;
    private double tLAmount;
    private Integer cId;

    public Investment() {

    }

    public Investment(Integer iId, double sFee, double mEarnings, Boolean aDeal,Boolean aTerms, double tLAmount,Integer cId) {
        this.iId = iId;
        this.sFee = sFee;
        this.mEarnings = mEarnings;
        this.aDeal = aDeal;
        this.aTerms = aTerms;
        this.tLAmount = tLAmount;
        this.cId = cId;
    }

    public Integer getiId() {
        return iId;
    }

    public void setiId(Integer iId) {
        this.iId = iId;
    }

    public double getsFee() {
        return sFee;
    }

    public void setsFee(double sFee) {
        this.sFee = sFee;
    }

    public double getmEarnings() {
        return mEarnings;
    }

    public void setmEarnings(double mEarnings) {
        this.mEarnings = mEarnings;
    }

    public Boolean getaDeal() {
        return aDeal;
    }

    public void setaDeal(Boolean aDeal) {
        this.aDeal = aDeal;
    }

    public Boolean getaTerms() {
        return aTerms;
    }

    public void setaTerms(Boolean aTerms) {
        this.aTerms = aTerms;
    }

    public double gettLAmount() {
        return tLAmount;
    }

    public void settLAmount(double tLAmount) {
        this.tLAmount = tLAmount;
    }
}
