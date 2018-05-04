/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.entity;

import java.util.Date;


/**
 *
 * @author acer
 */
public class Transaction {

    int id;
    String accountNo;    
    Date transactionDate;
    String transactionType;
    double transactionAmount;
    double availableBalance;

    public Transaction() {
    }

    public Transaction(int id, String accountNo, String transactionType, double transactionAmount, double availableBalance) {
        this.id = id;
        this.accountNo = accountNo;                
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.availableBalance = availableBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }
  
}
