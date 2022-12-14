package com.example.p2plendingapp.Model;

public class Account {

    private int accId;
    private String username, password, email;

    public Account() {

    }

    public Account(int accId, String username, String password, String email) {
        this.accId = accId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
