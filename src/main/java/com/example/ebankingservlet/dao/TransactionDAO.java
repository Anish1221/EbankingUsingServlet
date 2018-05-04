/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.dao;

import com.example.ebankingservlet.entity.Account;
import com.example.ebankingservlet.entity.Transaction;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author acer
 */
public interface TransactionDAO {
    
    int insertTransation(Transaction t) throws ClassNotFoundException, SQLException;
    int updateBalance(Account a, double balance) throws ClassNotFoundException, SQLException;
    List<Transaction> getByAccountNo(String accountNo) throws ClassNotFoundException, SQLException;
}
