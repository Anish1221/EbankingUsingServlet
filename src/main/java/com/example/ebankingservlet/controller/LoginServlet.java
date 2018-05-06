/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.controller;

import com.example.ebankingservlet.dao.SystemDAO;
import com.example.ebankingservlet.dao.impl.SystemDAOImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/index"})
public class LoginServlet extends HttpServlet {

    SystemDAO systemDAO = new SystemDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") != null) {
            try {
                String username = request.getSession().getAttribute("username").toString();
                
                String userRole = systemDAO.checkSession(username);

                if (userRole != null && userRole.equalsIgnoreCase("admin")) {                    
                    response.sendRedirect("admin");
                } else if (userRole != null && userRole.equalsIgnoreCase("customer")) {                    
                    response.sendRedirect("user");
                }
            } catch (Exception e) {

            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String userRole = systemDAO.checkLogin(username, password);

            if (userRole != null && userRole.equalsIgnoreCase("admin")) {
                request.getSession().setAttribute("username", username);
                response.sendRedirect("admin");
            } else if (userRole != null && userRole.equalsIgnoreCase("customer")) {
                request.getSession().setAttribute("username", username);
                response.sendRedirect("user");
            } else {
                request.setAttribute("error", "Invalid Credentials!!");
                doGet(request, response);
            }

        } catch (Exception e) {

        }
    }

}
