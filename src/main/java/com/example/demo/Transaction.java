package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String action;
    @NotNull
    private long amount;
    private String accountNum;
    @NotNull
    @Size(min=2, max=30)
    private String reason;
    private long balance;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }


}
