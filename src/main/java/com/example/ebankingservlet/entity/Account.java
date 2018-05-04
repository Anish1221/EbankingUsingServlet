/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.entity;

/**
 *
 * @author acer
 */
public class Account {
    private double balance;
    private String accountNo;

    public Account() {
    }

    public Account(double balance, String accountNo) {
        this.balance = balance;
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    
       
    
}
