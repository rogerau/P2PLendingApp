package com.example.p2plendingapp.Model;

public class Investor extends Customer {

    private int inId;
    private String sOFunds;
    private String pRLevel;
    private String cBAccount;

    public Investor() {

    }

    public Investor(int inId, String sOFunds, String pRLevel, String cBAccount) {
        this.inId = inId;
        this.sOFunds = sOFunds;
        this.pRLevel = pRLevel;
        this.cBAccount = cBAccount;
    }

    public int getInId() {
        return inId;
    }

    public void setInId(int inId) {
        this.inId = inId;
    }

    public String getcBAccount() {
        return cBAccount;
    }

    public void setcBAccount(String cBAccount) {
        this.cBAccount = cBAccount;
    }

    public String getsOFunds() {
        return sOFunds;
    }

    public void setsOFunds(String sOFunds) {
        this.sOFunds = sOFunds;
    }

    public String getpRLevel() {
        return pRLevel;
    }

    public void setpRLevel(String pRLevel) {
        this.pRLevel = pRLevel;
    }
}
