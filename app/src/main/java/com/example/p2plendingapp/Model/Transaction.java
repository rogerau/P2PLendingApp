package com.example.p2plendingapp.Model;

public class Transaction {

    private int tId;
    private String tDesc;

    public Transaction() {

    }

    public Transaction(int tId, String tDesc) {

        this.tId = tId;
        this.tDesc = tDesc;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }


    public String gettDesc() {
        return tDesc;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc;
    }
}
