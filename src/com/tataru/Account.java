package com.tataru;

import java.util.ArrayList;

public class Account {
    private String name;
    private double balance;
    private User holder;
    private ArrayList<Transaction> transactions;


    public Account(String name, User holder) {
        this.name = name;
        this.balance = 0.0;
        this.holder = holder;
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public User getHolder() {
        return holder;
    }

    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }

    public void depositMoney(Account account, double amount){
        Transaction transaction = new Transaction(amount, account, Transaction.TransactionType.DEPOSIT);
        transactions.add(transaction);
        this.balance += amount;
    }

    public void withdrawMoney(Account account, double amount) {
        Transaction transaction = new Transaction(amount, account, Transaction.TransactionType.WITHDRAW);
        transactions.add(transaction);
        this.balance -= amount;
    }
}
