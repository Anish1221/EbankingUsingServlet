/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.filter;

import com.example.ebankingservlet.dao.SystemDAO;
import com.example.ebankingservlet.dao.impl.SystemDAOImpl;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acer
 */
@WebFilter(urlPatterns = {"/admin", 
    "/info", 
    "/add-account", 
    "/add-customer",
    "/deposit",
    "/withdraw",
    "/user",
    "/transactions",
    "/transfer"})
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        SystemDAO systemDAO = new SystemDAOImpl();

        if (request.getSession().getAttribute("username") != null) {
            try {
                String username = request.getSession().getAttribute("username").toString();
                String userRole = systemDAO.checkSession(username);
                
                if (userRole != null && userRole.equalsIgnoreCase("admin")) {
                    String uri = request.getRequestURI().toLowerCase();
                    if (uri.contains("/user") || uri.contains("/transactions") || uri.contains("/transfer")) {
                        request.getRequestDispatcher("error").forward(sr, sr1);
                    }   
                    fc.doFilter(sr, sr1);
                } else if (userRole != null && userRole.equalsIgnoreCase("customer")) {
                    String uri = request.getRequestURI().toLowerCase();
                    if (uri.contains("/admin") || uri.contains("/info") || uri.contains("/add-account") || uri.contains("/add-customer") || uri.contains("/deposit") || uri.contains("/withdraw")) {
                        request.getRequestDispatcher("error").forward(sr, sr1);
                    }                    
                    fc.doFilter(sr, sr1);
                }else{
                    request.getRequestDispatcher("index").forward(sr, sr1);
                }
                
            } catch (Exception e) {

            }
        }else{
            request.getRequestDispatcher("index").forward(sr, sr1);
        }

    }

    @Override
    public void destroy() {

    }

}
