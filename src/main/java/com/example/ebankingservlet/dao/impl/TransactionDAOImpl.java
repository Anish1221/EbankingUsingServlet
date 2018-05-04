/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.dao.impl;

import com.example.ebankingservlet.dao.TransactionDAO;
import com.example.ebankingservlet.dbutil.DbConnection;
import com.example.ebankingservlet.entity.Account;
import com.example.ebankingservlet.entity.Transaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class TransactionDAOImpl implements TransactionDAO{
    
    DbConnection db = new DbConnection();
    
    @Override
    public int insertTransation(Transaction t) throws ClassNotFoundException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        db.open();
        String sql = "INSERT INTO tbl_transaction(account_no, transaction_type, transaction_amount, available_balance)"
                + "VALUES(?,?,?,?)";
        PreparedStatement stmt = db.initStatement(sql);
        stmt.setString(1, t.getAccountNo());
        stmt.setString(2, t.getTransactionType());      
        stmt.setDouble(3, t.getTransactionAmount());
        stmt.setDouble(4, t.getAvailableBalance());
        int result = db.executeUpdate();
        db.close();
        return result;
        
    }
    
    @Override
    public int updateBalance(Account a, double balance) throws ClassNotFoundException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        db.open();
        String sql = "UPDATE tbl_account SET balance = ? WHERE account_no=?";
        PreparedStatement stmt = db.initStatement(sql);
        stmt.setDouble(1, balance);
        stmt.setString(2, a.getAccountNo());
        int result = db.executeUpdate();
        db.close();
        return result;
    }

    @Override
    public List<Transaction> getByAccountNo(String accountNo) throws ClassNotFoundException, SQLException {
        
        List<Transaction> transactionList = new ArrayList<>();
        
        db.open();        
        String sql="SELECT account_no, transaction_date, transaction_type, transaction_amount, available_balance FROM tbl_transaction WHERE account_no = ?";
        PreparedStatement stmt = db.initStatement(sql);     
        stmt.setString(1, accountNo);
        ResultSet rs = db.executeQuery();
        while(rs.next()){
            Transaction t = new Transaction();
            t.setAccountNo(rs.getString("account_no"));
            t.setTransactionDate(rs.getDate("transaction_date"));
            t.setTransactionType(rs.getString("transaction_type"));
            t.setTransactionAmount(rs.getDouble("transaction_amount"));
            t.setAvailableBalance(rs.getDouble("available_balance"));
            transactionList.add(t);
            System.out.println(transactionList);
        }
        db.close();
        return transactionList;
    }
   
}
