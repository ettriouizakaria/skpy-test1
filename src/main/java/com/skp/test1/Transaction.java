package com.skp.test1;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Transaction {
    int amount;
    int balance;
    String date;

    public Transaction(){
        this.amount = 0;
        this.balance = 0;
        this.date = new SimpleDateFormat("dd/mm/yyyy").format(new Date());
    }

    public Transaction(int amount, int balance, String date){
        this.amount = amount;
        this.balance = balance;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String transactionDetails = date + " | " + amount + " | " + balance;
        return transactionDetails;
    }
}
