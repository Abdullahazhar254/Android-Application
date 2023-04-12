package com.example.bankapp;

public class userTransaction {
    private int id;
    private int amount;
    private int sender;
    private int receiver;

    public userTransaction(int id, int amount, int receiver) {
        this.id = id;
        this.amount = amount;
        this.receiver = receiver;
    }

    public userTransaction(int amount, int sender) {
        this.amount = amount;
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }
}
