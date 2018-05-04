/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.controller;

import com.example.ebankingservlet.dao.AdminDAO;
import com.example.ebankingservlet.dao.CustomerDAO;
import com.example.ebankingservlet.dao.SystemDAO;
import com.example.ebankingservlet.dao.impl.AdminDAOImpl;
import com.example.ebankingservlet.dao.impl.CustomerDAOImpl;
import com.example.ebankingservlet.dao.impl.SystemDAOImpl;
import com.example.ebankingservlet.entity.Account;
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
@WebServlet(urlPatterns = "/add-account")
public class AdminAddAccountServlet extends HttpServlet {

    private AdminDAO adminDAO = new AdminDAOImpl();
    private CustomerDAO custDAO = new CustomerDAOImpl();
    private SystemDAO sysDAO = new SystemDAOImpl();
    private Account account = new Account();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/admin/add_account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int id = (Integer) request.getSession().getAttribute("customerId");
            String accountNo = request.getParameter("account_no");
            double balance = Double.parseDouble(request.getParameter("balance"));
            account.setAccountNo(accountNo);
            account.setBalance(balance);

            if (adminDAO.insertAccount(account, id) > 0) {
                response.sendRedirect("info");
            } else {
                custDAO.delete(sysDAO.getId());
                request.setAttribute("error", "Some Error Occured");
                request.getRequestDispatcher("WEB-INF/views/admin/add_customer.jsp").forward(request, response);
            }
        } catch (Exception e) {

        }
    }

}
