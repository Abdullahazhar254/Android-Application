package com.example.bankapp;

public class bankUser {
    private int id;
    private String name;
    private String email;
    private String password;
    private String contact;
    private int account;
    private int amount;


    public bankUser(int id, String name, String email, String password, String contact, int account, int amount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.account = account;
        this.amount = amount;
    }

    public bankUser(String name, String email, String contact, int account, int amount) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.account = account;
        this.amount = amount;
    }

    public bankUser(String name, int account, int amount) {
        this.name = name;
        this.account = account;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
