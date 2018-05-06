/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.dao.impl;

import com.example.ebankingservlet.dao.SystemDAO;
import com.example.ebankingservlet.dbutil.DbConnection;
import com.example.ebankingservlet.entity.Login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author acer
 */
public class SystemDAOImpl implements SystemDAO {

    private Boolean found = false;
    DbConnection db = new DbConnection();

    @Override
    public Boolean checkByAccountNo(String accountNo, String table) throws ClassNotFoundException, SQLException {

        db.open();
        String sql = "SELECT account_no FROM " + table;
        db.initStatement(sql);
        ResultSet rs = db.executeQuery();
        while (rs.next()) {
            if (rs.getString("account_no").equalsIgnoreCase(accountNo)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public Boolean checkByUsername(String username) throws ClassNotFoundException, SQLException {

        db.open();
        String sql = "SELECT username FROM tbl_login";
        db.initStatement(sql);
        ResultSet rs = db.executeQuery();
        while (rs.next()) {
            if (rs.getString("username").equalsIgnoreCase(username)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public String checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
        String userRole = null;
        db.open();
        String sql = "SELECT username, password, user_role FROM tbl_admin WHERE username= ? and password = ? UNION SELECT username, password, user_role FROM tbl_login WHERE username= ? and password = ?";
        PreparedStatement stmt = db.initStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, username);
        stmt.setString(4, password);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            if (rs.getString("username").equalsIgnoreCase(username) && rs.getString("password").equalsIgnoreCase(password)) {
                userRole = rs.getString("user_role");
                break;
            }
        }
        return userRole;
    }
    
    @Override
    public String checkSession(String username) throws ClassNotFoundException, SQLException {
        String userRole = null;
        db.open();
        String sql = "SELECT username, user_role FROM tbl_admin WHERE username= ? UNION SELECT username, user_role FROM tbl_login WHERE username= ?";
        PreparedStatement stmt = db.initStatement(sql);
        stmt.setString(1, username);        
        stmt.setString(2, username);        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            if (rs.getString("username").equalsIgnoreCase(username)) {
                userRole = rs.getString("user_role");
                break;
            }
        }
        return userRole;
    }

    @Override
    public int getId() throws ClassNotFoundException, SQLException {
        int lastid = 0;
        db.open();
        String sql = "SELECT MAX(customer_id) AS id FROM tbl_customer";
        PreparedStatement stmt = db.initStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            lastid = rs.getInt("id");
        }
        return lastid;

    }

    @Override
    public int getBalance(String accountNo) throws ClassNotFoundException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int balance = 0;
        db.open();
        String sql = "SELECT balance FROM tbl_account WHERE account_no = ?";
        PreparedStatement stmt = db.initStatement(sql);
        stmt.setString(1, accountNo);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            balance = rs.getInt("balance");
        }
        return balance;
    }

    @Override
    public String generateAccount() throws ClassNotFoundException, SQLException{
        StringBuilder sb;
        do{
        char[] chars = "0123456789".toCharArray();
        Random rnd = new Random();
        sb = new StringBuilder((100000 + rnd.nextInt(900000)));
        for (int i = 0; i < 8; i++) {
            sb.append(chars[rnd.nextInt(chars.length)]);
        }
        }while(checkByAccountNo(sb.toString(), "tbl_account"));
        return sb.toString();
    }

    @Override
    public String getAccountNo(String username) throws ClassNotFoundException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String acc = "";
        db.open();
        String sql = "SELECT account_no FROM tbl_login WHERE username = ?";
        PreparedStatement stmt = db.initStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            acc = rs.getString("account_no");
        }
        return acc;
    }

    @Override
    public int insertLogin(Login l) throws ClassNotFoundException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        db.open();
        String sql = "INSERT INTO tbl_login(account_no, username, password) VALUES(?, ?, ?)";
        PreparedStatement stmt = db.initStatement(sql);
        stmt.setString(1, l.getAccountNo());
        stmt.setString(2, l.getUsername());
        stmt.setString(3, l.getPassword());
        
        int result = db.executeUpdate();
        
        db.close();
        
        return result;
        
    }

}
