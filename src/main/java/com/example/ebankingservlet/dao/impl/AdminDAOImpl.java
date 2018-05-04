/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.dao.impl;

import com.example.ebankingservlet.dao.AdminDAO;
import com.example.ebankingservlet.dbutil.DbConnection;
import com.example.ebankingservlet.entity.Account;
import com.example.ebankingservlet.entity.Customer;
import com.example.ebankingservlet.entity.CustomerInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class AdminDAOImpl implements AdminDAO {

    DbConnection db = new DbConnection();

    @Override
    public int insertAccount(Account a, int id) throws ClassNotFoundException, SQLException {

        db.open();
        String sql = "INSERT INTO tbl_account(account_no, customer_id, balance)"
                + "VALUES(?,?,?)";
        PreparedStatement stmt = db.initStatement(sql);
        stmt.setString(1, a.getAccountNo());
        stmt.setInt(2, id);
        stmt.setDouble(3, a.getBalance());
        int result = db.executeUpdate();

        db.close();

        return result;
    }

    @Override
    public List<CustomerInfo> getInfo() throws ClassNotFoundException, SQLException {
        List<CustomerInfo> customerInfoList = new ArrayList();

        db.open();
        String sql = "SELECT a.account_no, c.first_name, c.last_name, c.gender, c.bday, c.email, c.contact_no, c.address, c.status, a.balance FROM tbl_account AS a, tbl_customer AS c WHERE a.customer_id = c.customer_id";
        db.initStatement(sql);
        ResultSet rs = db.executeQuery();
        while (rs.next()) {
            Account a = new Account();
            Customer c = new Customer();
            CustomerInfo customerInfo = new CustomerInfo();
            a.setAccountNo(rs.getString("account_no"));
            c.setFirstName(rs.getString("first_name"));
            c.setLastName(rs.getString("last_name"));
            c.setGender(rs.getString("gender"));
            c.setDob(rs.getString("bday"));
            c.setEmail(rs.getString("email"));
            c.setContactNo(rs.getString("contact_no"));
            c.setAddress(rs.getString("address"));
            c.setStatus(rs.getBoolean("status"));
            a.setBalance(rs.getDouble("balance"));
                        
            customerInfo.setAccount(a);
            customerInfo.setCustomer(c);            
            customerInfoList.add(customerInfo);
        }
        db.close();
         return customerInfoList;
    }

}
