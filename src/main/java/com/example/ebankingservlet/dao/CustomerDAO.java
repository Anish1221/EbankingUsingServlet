/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.dao;

import com.example.ebankingservlet.entity.Customer;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author acer
 */
public interface CustomerDAO {
    List<Customer> getAll()throws ClassNotFoundException, SQLException;
    int insert(Customer c) throws ClassNotFoundException, SQLException;
    boolean delete(int id) throws ClassNotFoundException, SQLException;
}
