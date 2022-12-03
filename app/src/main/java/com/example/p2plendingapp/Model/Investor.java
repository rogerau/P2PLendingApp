package com.example.p2plendingapp.Model;

public class Investor {

    private Integer inId;
    private String sOFunds;
    private String pRLevel;
    private String cBAccount;

    public Investor() {

    }

    public Investor(Integer inId, String sOFunds, String pRLevel, String cBAccount) {
        this.inId = inId;
        this.sOFunds = sOFunds;
        this.pRLevel = pRLevel;
        this.cBAccount = cBAccount;
    }

    public String getcBAccount() {
        return cBAccount;
    }

    public void setcBAccount(String cBAccount) {
        this.cBAccount = cBAccount;
    }

    public Integer getInId() {
        return inId;
    }

    public void setInId(Integer inId) {
        this.inId = inId;
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
