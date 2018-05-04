/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.controller;

import com.example.ebankingservlet.dao.CustomerDAO;
import com.example.ebankingservlet.dao.SystemDAO;
import com.example.ebankingservlet.dao.impl.CustomerDAOImpl;
import com.example.ebankingservlet.dao.impl.SystemDAOImpl;
import com.example.ebankingservlet.entity.Customer;
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
@WebServlet(urlPatterns = "/add-customer")
public class AdminAddCustomerServlet extends HttpServlet {

    private Customer customer = new Customer();
    private CustomerDAO custDAO = new CustomerDAOImpl();
    private SystemDAO sysDAO = new SystemDAOImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/admin/add_customer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {

            customer.setFirstName(request.getParameter("first_name"));
            customer.setLastName(request.getParameter("last_name"));
            customer.setGender(request.getParameter("gender"));
            customer.setDob(request.getParameter("bday"));
            customer.setEmail(request.getParameter("email"));
            customer.setContactNo(request.getParameter("contact_no"));
            customer.setAddress(request.getParameter("address"));
            customer.setStatus(request.getParameter("status") != null);            
            
            if (custDAO.insert(customer)> 0) {
                
                request.getSession().setAttribute("customerId", sysDAO.getId());
                response.sendRedirect("add-account");
            } else {
                request.setAttribute("error", "Some Error Occured");
                doGet(request, response);
            }

        } catch (Exception e) {
            
        }
    }
    
    

}
