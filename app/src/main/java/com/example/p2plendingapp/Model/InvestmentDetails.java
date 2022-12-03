package com.example.p2plendingapp.Model;

import com.example.p2plendingapp.Model.Opportunities;

public class InvestmentDetails {

    private int investmentId;
    private double investmentA;
    private double mREarning;
    private double sFee;
    Opportunities aOpportunity = new Opportunities();
    //Investor aInvestor = new Investor();
    //The investor class must be instantiated to get the risk preference level.


    public InvestmentDetails(int iId, double iA, double mRE) {
        this.investmentId = iId;
        this.investmentA = iA;
        this.mREarning = mRE;
        this.sFee = getsFee();
    }

    public int getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(int investmentId) {
        this.investmentId = investmentId;
    }

    public double getInvestmentA() {
        return investmentA;
    }

    public void setInvestmentA(double investmentA) {
        this.investmentA = investmentA;
    }

    public double getmEarning() {
        return mREarning;
    }

    public void setmEarning(double mEarnings) {
        this.mREarning = mEarnings;
    }

    public double getsFee() {
        return sFee;
    }

    public void setsFee(double sFee) {
        //for now constant is used.
        this.sFee = 0.00125;
    }

    public double calEarningsAcc() {
        return this.mREarning * aOpportunity.getBorrowPeriod();
    }

    public double calProfitRatio() {
        return ((this.calEarningsAcc() - investmentA) / investmentA);
    }

    public double calmNEarning() {
        return this.mREarning - (this.sFee * this.mREarning);
    }
}
