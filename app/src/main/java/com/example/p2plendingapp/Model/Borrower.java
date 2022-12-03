package com.example.p2plendingapp.Model;

public class Borrower {

    private Integer bId;
    private String pStatus;
    private double hmExp;
    private String rStatus;
    private double mNetIncome;
    private double mSNetIncome;
    private String mStatus;
    private String cBAccount;

    public Borrower() {

    }


    public Borrower(Integer bId, String pStatus, double hmExp, String rStatus, double mNetIncome, double mSNetIncome, String mStatus, String cBAccount) {
        this.bId = bId;
        this.pStatus = pStatus;
        this.hmExp = hmExp;
        this.rStatus = rStatus;
        this.mNetIncome = mNetIncome;
        this.mSNetIncome = mSNetIncome;
        this.mStatus = mStatus;
        this.cBAccount = cBAccount;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public Integer getbId() {
        return bId;
    }

    public String getcBAccount() {
        return cBAccount;
    }

    public void setcBAccount(String cBAccount) {
        this.cBAccount = cBAccount;
    }


    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public double getHmExp() {
        return hmExp;
    }

    public void setHmExp(double hmExp) {
        this.hmExp = hmExp;
    }

    public String getrStatus() {
        return rStatus;
    }

    public void setrStatus(String rStatus) {
        this.rStatus = rStatus;
    }

    public double getmNetIncome() {
        return mNetIncome;
    }

    public void setmNetIncome(double mNetIncome) {
        this.mNetIncome = mNetIncome;
    }

    public double getmSNetIncome() {
        return mSNetIncome;
    }

    public void setmSNetIncome(double mSNetIncome) {
        this.mSNetIncome = mSNetIncome;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String setRiskLvl() {
        if (this.pStatus == "rent") {
            if (this.mStatus == "single") {
                if (this.rStatus == "temporary") {
                    if (this.hmExp >= this.mNetIncome) {
                        return "High Risk";
                    } else {
                        return "Moderate high risk";
                    }
                } else {
                    if (this.hmExp >= this.mNetIncome) {
                        return "Moderate high risk";
                    } else {
                        return "Low high risk";
                    }
                }
            } else {
                if (this.rStatus == "temporary") {
                    if (this.hmExp >= this.mNetIncome + this.mSNetIncome) {
                        return "Low high risk";
                    } else {
                        return "Medium risk";
                    }
                } else {
                    if (this.hmExp >= this.mNetIncome + this.mSNetIncome) {
                        return "Medium risk";

                    } else {
                        return "Moderate medium risk";

                    }
                }
            }
        } else {
            if (this.mStatus == "single") {
                if (this.hmExp >= this.mNetIncome) {
                    return "Low medium risk";
                } else {
                    return "Low risk";
                }
            } else {
                if (this.hmExp >= this.mNetIncome + this.mSNetIncome) {
                    return "Moderate low risk";
                } else {
                    return "Lower low risk";
                }
            }
        }
    }
}
