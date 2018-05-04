/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.controller;

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
@WebServlet(urlPatterns = "/admin/*")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {                     

        String uri = request.getRequestURI().toLowerCase();
        String view = "admin_home.jsp";
        if (uri.contains("/add")) {
            view = "index.jsp";
        } else {
            request.getAttribute("username");
        }
        request.getRequestDispatcher("WEB-INF/views/admin/" + view).forward(request, resp);
    }

}
