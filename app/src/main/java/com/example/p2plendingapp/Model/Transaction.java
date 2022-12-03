package com.example.p2plendingapp.Model;

public class Transaction {

    private Integer tId;
    private String tDesc;

    public Transaction() {

    }

    public Transaction(Integer tId, String tDesc) {
        this.tId = tId;
        this.tDesc = tDesc;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettDesc() {
        return tDesc;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc;
    }
}
