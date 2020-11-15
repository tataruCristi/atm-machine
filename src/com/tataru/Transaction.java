package com.tataru;

import java.util.Date;

public class Transaction {
    private double amount;
    private Date timestamp;
    private Account inAccount;
    private TransactionType type;

    public Transaction(double amount, Account inAccount, TransactionType type) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    enum TransactionType {
        DEPOSIT, WITHDRAW
    }
}

