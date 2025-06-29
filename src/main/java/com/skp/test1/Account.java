package com.skp.test1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account implements AccountService {

    private List<Transaction> transactions;
    private int balance;

    public Account(){
        this.transactions = new ArrayList<>();
        this.balance = 0;
    }

    @Override
    public void deposit(int amount) {
        if (amount < 0){
            throw new IllegalArgumentException("Invalid amount, it must be positive!");
        }
        String date = getDate();
        this.balance += amount;
        this.transactions.add(new Transaction(amount, balance, date));
    }

    @Override
    public void withdraw(int amount) {
        if (amount < 0){
            throw new IllegalArgumentException("Invalid amount, it must be positive!");
        }

        if (amount > this.balance) {
            throw new IllegalArgumentException("Cannot withdraw this amount, insufficient balance!");
        }

        String date = getDate();
        this.balance -= amount;
        this.transactions.add(new Transaction(-amount, balance, date));
    }

    @Override
    public void printStatement() {
        System.out.println("DATE       | AMOUNT | BALANCE");
        transactions.sort((t1, t2) -> t2.date.compareTo(t1.date));
        for (Transaction transaction : this.transactions){
            System.out.println(transaction);
        }
    }

    protected String getDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
}
