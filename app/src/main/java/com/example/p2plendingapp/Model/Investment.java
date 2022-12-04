package com.example.p2plendingapp.Model;

public class Investment {

    private int iId;
    private double sFee;
    private double mEarnings;
    private Boolean aDeal;
    private Boolean aTerms;
    private double tLAmount;
    private Integer cId;

    public Investment() {

    }

    public Investment(int iId, String pRL, Boolean aDeal, Boolean aTerms, double tLAmount, int cId) {
        this.iId = iId;
        this.sFee = setsFee(pRL);
        this.mEarnings = setmEarnings();
        this.aDeal = aDeal;
        this.aTerms = aTerms;
        this.tLAmount = tLAmount;
        this.cId = cId;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public Integer getcId() {
        return cId;
    }

    public double getsFee() {
        return sFee;
    }

    public double setsFee(String pRLevel) {
        double sFee;
        if (pRLevel.equals("High Risk")) {
            sFee = 0.0125;
        } else if (pRLevel.equals("Moderate high risk")) {
            sFee = 0.0138;
        } else if (pRLevel.equals("Low high risk")) {
            sFee = 0.0151;
        } else if (pRLevel.equals("Medium risk")) {
            sFee = 0.0166;
        } else if (pRLevel.equals("Moderate medium risk")) {
            sFee = 0.0183;
        } else if (pRLevel.equals("Low medium risk")) {
            sFee = 0.0201;
        } else if (pRLevel.equals("Low risk")) {
            sFee = 0.0221;
        } else if (pRLevel.equals("Moderate low risk")) {
            sFee = 0.0244;
        } else {
            sFee = 0.0268;
        }
        return sFee;
    }

    public double getmEarnings() {
        return mEarnings;
    }

    public double setmEarnings() {
        return mEarnings * (1 - this.mEarnings * this.sFee);
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

    public void setcId(Integer cId) {
        this.cId = cId;
    }
}
