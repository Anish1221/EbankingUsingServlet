/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.dao;

import com.example.ebankingservlet.entity.Account;
import com.example.ebankingservlet.entity.CustomerInfo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author acer
 */
public interface AdminDAO {
    
    int insertAccount(Account a, int id) throws ClassNotFoundException, SQLException;
    List<CustomerInfo> getInfo() throws ClassNotFoundException, SQLException;;
    
}
