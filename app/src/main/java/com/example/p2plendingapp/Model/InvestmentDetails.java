package com.example.p2plendingapp.Model;

import com.example.p2plendingapp.Model.Opportunities;

public class InvestmentDetails {

    private int investmentId;
    private double investmentA;
    private double mREarning;
    private int bPeriod;


    public InvestmentDetails(int iId, double iA, double mRE, int bP) {
        this.investmentId = iId;
        this.investmentA = iA;
        this.mREarning = mRE;
        this.bPeriod = bP;
    }

    public int getInvestmentId() {
        return investmentId;
    }


    public double getInvestmentA() {
        return investmentA;
    }

    public double getmREarning() {
        return mREarning;
    }

    public double calEarningsAcc() {

        return this.mREarning * this.bPeriod;
    }

    public double calProfitRatio() {
        return ((this.calEarningsAcc() - investmentA) / investmentA);
    }

}
