/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.dao;

import com.example.ebankingservlet.entity.Login;
import java.sql.SQLException;


/**
 *
 * @author acer
 */
public interface SystemDAO {
    Boolean checkByAccountNo(String accountNo, String table)throws ClassNotFoundException, SQLException;
    Boolean checkByUsername(String username)throws ClassNotFoundException, SQLException;
    String checkLogin(String username, String password)throws ClassNotFoundException, SQLException;
    String checkSession(String username) throws ClassNotFoundException, SQLException;
    int getId() throws ClassNotFoundException, SQLException;
    int getBalance(String accountNo) throws ClassNotFoundException, SQLException;
    String generateAccount() throws ClassNotFoundException, SQLException;
    String getAccountNo(String username) throws ClassNotFoundException, SQLException;
    int insertLogin(Login l) throws ClassNotFoundException, SQLException;
    
}
