package com.example.p2plendingapp.Model;

public abstract class Customer {

    private int cId;
    private String dOB;
    private String fName;
    private String lName;

    public Customer() {

    }

    public Customer(int cid, String dOB, String fName, String lName) {
        this.cId = cid;
        this.dOB = dOB;
        this.fName = fName;
        this.lName = lName;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getdOB() {
        return dOB;
    }

    public void setdOB(String dOB) {
        this.dOB = dOB;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
