package com.example.p2plendingapp.Model;

import com.example.p2plendingapp.Investor.MarketPlace_Java.MarketPlaceAccess;

public class Loan {

    private Integer lId;
    private String pOBorrowing;
    private double oFee;
    private double iRate;
    private double uPFee;
    private double lPFee;
    private double mPAmount;
    private Boolean aTerms;
    private double lAmount;
    private Integer pPeriod;
    private String sDOAgreement;
    private Integer cId;

    public Loan() {

    }

    public Loan(Integer lId, String pOBorrowing, double oFee, double uPFee, double lPFee, double mPAmount,
                Boolean aTerms, double lAmount, Integer pPeriod, String sDOAgreement, Integer cId) {
        this.lId = lId;
        this.pOBorrowing = pOBorrowing;
        this.oFee = oFee;
        this.uPFee = uPFee;
        this.lPFee = lPFee;
        this.mPAmount = mPAmount;
        this.aTerms = aTerms;
        this.lAmount = lAmount;
        this.pPeriod = pPeriod;
        this.sDOAgreement = sDOAgreement;
        this.cId = cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getcId() {
        return cId;
    }

    public Integer getlId() {
        return lId;
    }

    public void setlId(Integer lId) {
        this.lId = lId;
    }

    public String getpOBorrowing() {
        return pOBorrowing;
    }

    public void setpOBorrowing(String pOBorrowing) {
        this.pOBorrowing = pOBorrowing;
    }

    public double getoFee() {
        return oFee;
    }

    public void setoFee(double lAmount) {
        if (lAmount >= 1000 && lAmount < 5000) {
            this.oFee = 0.02;
        } else if (lAmount >= 5000 && lAmount < 15000) {
            this.oFee = 0.03;
        } else {
            this.oFee = 0.04;
        }

    }

    public double getiRate() {
        return iRate;
    }

    public void setiRate(double lAmount, double pPeriod, String riskLvl) {
        //Max annual interest rate in Canada 60%
        //Get the equivalent monthly according to loan amount and payment period
        //If loan amount is between 1000 and 5000, and the loan period is greater than
        //12 months assign 60% annual (high risk), 55% annual (medium risk) and 45% (lower risk) , otherwise
        //40% annual (high risk), 35% annual (medium risk) and 25% (lower risk).
        if (lAmount >= 1000 && lAmount < 5000) {
            if (pPeriod >= 12) {
                if ((riskLvl == "High risk" || riskLvl == "Moderate high risk" || riskLvl == "Low high risk")) {
                    this.iRate = 0.60 / 12;
                } else if ((riskLvl == "Medium risk" || riskLvl == "Moderate medium risk" || riskLvl == "Low medium risk")) {
                    this.iRate = 0.55 / 12;
                } else {
                    this.iRate = 0.45 / 12;
                }
            } else {
                if ((riskLvl == "High risk" || riskLvl == "Moderate high risk" || riskLvl == "Low high risk")) {
                    this.iRate = 0.40 / 12;
                } else if ((riskLvl == "Medium risk" || riskLvl == "Moderate medium risk" || riskLvl == "Low medium risk")) {
                    this.iRate = 0.35 / 12;
                } else {
                    this.iRate = 0.25 / 12;
                }
            }
            //If loan amount is between 5000 and 15000, and the loan period is greater than
            //12 months assign 50% annual (high risk), 45% annual (medium risk) and 35% (lower risk) , otherwise
            //30% annual (high risk), 25% annual (medium risk) and 15% (lower risk).
        } else if (lAmount >= 5000 && lAmount < 15000) {
            if (pPeriod >= 12) {
                if ((riskLvl == "High risk" || riskLvl == "Moderate high risk" || riskLvl == "Low high risk")) {
                    this.iRate = 0.50 / 12;
                } else if ((riskLvl == "Medium risk" || riskLvl == "Moderate medium risk" || riskLvl == "Low medium risk")) {
                    this.iRate = 0.45 / 12;
                } else {
                    this.iRate = 0.35 / 12;
                }
            } else {
                if ((riskLvl == "High risk" || riskLvl == "Moderate high risk" || riskLvl == "Low high risk")) {
                    this.iRate = 0.30 / 12;
                } else if ((riskLvl == "Medium risk" || riskLvl == "Moderate medium risk" || riskLvl == "Low medium risk")) {
                    this.iRate = 0.25 / 12;
                } else {
                    this.iRate = 0.15 / 12;
                }
            }
            //If loan amount is between 15000 and 25000, and the loan period is greater than
            //12 months assign 40% annual (high risk), 35% annual (medium risk) and 25% (lower risk) , otherwise
            //20% annual (high risk), 15% annual (medium risk) and 5% (lower risk).
        } else {
            if (pPeriod >= 12) {
                if ((riskLvl == "High risk" || riskLvl == "Moderate high risk" || riskLvl == "Low high risk")) {
                    this.iRate = 0.40 / 12;
                } else if ((riskLvl == "Medium risk" || riskLvl == "Moderate medium risk" || riskLvl == "Low medium risk")) {
                    this.iRate = 0.35 / 12;
                } else {
                    this.iRate = 0.25 / 12;
                }
            } else {
                if ((riskLvl == "High risk" || riskLvl == "Moderate high risk" || riskLvl == "Low high risk")) {
                    this.iRate = 0.20 / 12;
                } else if ((riskLvl == "Medium risk" || riskLvl == "Moderate medium risk" || riskLvl == "Low medium risk")) {
                    this.iRate = 0.15 / 12;
                } else {
                    this.iRate = 0.05 / 12;
                }
            }
        }

    }

    public double getoFeeAmount() {
        return this.oFee * this.lAmount;
    }

    public double getuPFee() {
        return uPFee;
    }

    public void setuPFee() {
        this.uPFee = 50;
    }

    public double getlPFee() {
        return lPFee;
    }

    public void setlPFee() {

        this.lPFee = this.mPAmount * .10;
    }

    public double getmPAmount() {
        return mPAmount;
    }

    public void setmPAmount(double lAmount) {
        this.mPAmount = ((lAmount + this.oFee) / this.pPeriod) * (1 + this.iRate);
    }

    public Boolean getaTerms() {
        return aTerms;
    }

    public void setaTerms(Boolean aTerms) {
        this.aTerms = aTerms;
    }

    public double getlAmount() {
        return lAmount;
    }

    public void setlAmount(double lAmount) {
        this.lAmount = lAmount + this.oFee * lAmount;
    }

    public String getsDOAgreement() {
        return sDOAgreement;
    }

    public void setsDOAgreement(String sDOAgreement) {
        this.sDOAgreement = sDOAgreement;
    }

    public Integer getpPeriod() {
        return pPeriod;
    }

    public void setpPeriod(Integer pPeriod) {
        this.pPeriod = pPeriod;
    }

}
