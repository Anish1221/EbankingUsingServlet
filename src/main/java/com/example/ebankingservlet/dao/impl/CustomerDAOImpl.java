/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.dao.impl;

import com.example.ebankingservlet.dao.CustomerDAO;
import com.example.ebankingservlet.dbutil.DbConnection;
import com.example.ebankingservlet.entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class CustomerDAOImpl implements CustomerDAO {
    
    DbConnection db = new DbConnection();

    @Override
    public List<Customer> getAll() throws ClassNotFoundException, SQLException {
        List<Customer> customerList= new ArrayList<>();
        
        db.open();
        String sql = "SELECT * FROM tbl_customer";
        db.initStatement(sql);
        ResultSet rs = db.executeQuery();
        
        while(rs.next()){
            Customer c = new Customer();
            c.setId(rs.getInt("customer_id"));            
            c.setFirstName(rs.getString("first_name"));
            c.setLastName(rs.getString("last_name"));            
            c.setGender(rs.getString("gender"));
            c.setDob(rs.getString("bday"));
            c.setEmail(rs.getString("email"));
            c.setContactNo(rs.getString("contact_no"));
            c.setAddress(rs.getString("address"));
            c.setStatus(rs.getBoolean("status"));
            customerList.add(c);
        }
        db.close();
        
        return customerList;
    }
    
    @Override
    public int insert(Customer c) throws ClassNotFoundException, SQLException {
        
        db.open();
        
        String sql = "INSERT INTO tbl_customer(first_name, last_name, gender, bday, email, contact_no, address, status)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = db.initStatement(sql);
        
        stmt.setString(1, c.getFirstName());
        stmt.setString(2, c.getLastName());
        stmt.setString(3, c.getGender());
        stmt.setString(4, c.getDob());
        stmt.setString(5, c.getEmail());
        stmt.setString(6, c.getContactNo());
        stmt.setString(7, c.getAddress());
        stmt.setBoolean(8, c.getStatus());

        int result = db.executeUpdate();
        
        db.close();
        
        return result;
    }

    
    @Override
    public boolean delete(int id) throws ClassNotFoundException, SQLException {
        db.open();
        PreparedStatement stmt = db.initStatement("DELETE FROM tbl_customer WHERE id = ?");
        stmt.setInt(1, id);
        int i = stmt.executeUpdate();
        if(i>0) {
            db.close();
            return true;
        }
        db.close();
        return false;
    }

}
