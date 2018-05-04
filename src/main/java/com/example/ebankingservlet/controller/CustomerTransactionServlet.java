/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.controller;

import com.example.ebankingservlet.dao.SystemDAO;
import com.example.ebankingservlet.dao.TransactionDAO;
import com.example.ebankingservlet.dao.impl.SystemDAOImpl;
import com.example.ebankingservlet.dao.impl.TransactionDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acer
 */
@WebServlet(urlPatterns = "/transactions")
public class CustomerTransactionServlet extends HttpServlet {
        
    private TransactionDAO transDAO = new TransactionDAOImpl();
    private SystemDAO sysDAO = new SystemDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        
        try{            
            String username = request.getSession().getAttribute("username").toString();            
            String accountNo = sysDAO.getAccountNo(username);
            
            request.setAttribute("transaction", transDAO.getByAccountNo(accountNo));            
            request.getRequestDispatcher("WEB-INF/views/customer/activity.jsp").forward(request, response);
        
        }catch(Exception e){
            
        }                
    }

}
