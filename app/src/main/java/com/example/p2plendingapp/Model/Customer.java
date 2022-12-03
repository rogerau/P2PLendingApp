package com.example.p2plendingapp.Model;

public class Customer {

    //    private Integer cId;
    private String dOB;
    private String fName;
    private String lName;

    public Customer() {

    }

    public Customer(String dOB, String fName, String lName) {
//        this.cId = cId;
        this.dOB = dOB;
        this.fName = fName;
        this.lName = lName;
    }

//    public Integer getcId() {
//        return cId;
//    }
//
//    public void setcId(Integer cId) {
//        this.cId = cId;
//    }

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
