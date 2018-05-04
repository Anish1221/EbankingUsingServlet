/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.controller;

import com.example.ebankingservlet.dao.SystemDAO;
import com.example.ebankingservlet.dao.impl.SystemDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author acer
 */
@WebServlet(urlPatterns = "/user")
public class CustomerServlet extends HttpServlet {

    private SystemDAO systemDAO = new SystemDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        try {            
            String username = request.getSession().getAttribute("username").toString();
            String accountNo = systemDAO.getAccountNo(username);
            int balance = systemDAO.getBalance(accountNo);
            
            request.setAttribute("accountNo", accountNo);
            request.setAttribute("balance", balance);

            request.getRequestDispatcher("WEB-INF/views/customer/customer_home.jsp").forward(request, resp);
        } catch (Exception e) {

        }
    }

}
