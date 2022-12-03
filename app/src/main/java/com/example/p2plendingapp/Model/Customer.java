package com.example.p2plendingapp.Model;

public class Customer {

    private Integer cId;
    private String dOB;
    private String fName;
    private String lName;
    private String cbAcc;

    public Customer() {

    }

    public Customer(Integer cId, String dOB, String fName, String lName, String cbAcc) {
        this.cId = cId;
        this.dOB = dOB;
        this.fName = fName;
        this.lName = lName;
        this.cbAcc = cbAcc;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
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

    public String getCbAcc() {
        return cbAcc;
    }

    public void setCbAcc(String cbAcc) {
        this.cbAcc = cbAcc;
    }
}
