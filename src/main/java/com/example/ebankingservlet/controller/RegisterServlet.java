/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.controller;

import com.example.ebankingservlet.dao.SystemDAO;
import com.example.ebankingservlet.dao.impl.SystemDAOImpl;
import com.example.ebankingservlet.entity.Login;
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
@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Login login = new Login();
        SystemDAO systemDAO = new SystemDAOImpl();

        String accountNo = request.getParameter("account_no");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Boolean error1 = systemDAO.checkByAccountNo(accountNo, "tbl_login") != true;
            Boolean error2 = systemDAO.checkByUsername(username) != true;

            login.setAccountNo(accountNo);
            login.setUsername(username);
            login.setPassword(password);

            if (systemDAO.checkByAccountNo(accountNo, "tbl_account") == true) {
                if (error1) {
                    if (error2) {
                        if (systemDAO.insertLogin(login) > 0) {
                            request.setAttribute("message", "Sign up successful!!");
                            request.getRequestDispatcher("WEB-INF/views/index.jsp");
                        } else {
                            request.setAttribute("error", "Sign up failed!!!!");
                            doGet(request, response);
                        }
                    } else {
                        request.setAttribute("error", "Username already taken");
                        doGet(request, response);
                    }
                } else {
                    request.setAttribute("error", "Please enter valid Account No.");
                    doGet(request, response);
                }
            } else {
                request.setAttribute("error", "Please enter valid Account No.");
                doGet(request, response);
            }
        } catch (Exception e) {
            
        } 

    }

}
