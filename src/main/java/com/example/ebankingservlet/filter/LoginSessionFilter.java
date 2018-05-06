/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ebankingservlet.filter;

import com.example.ebankingservlet.controller.LoginServlet;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author acer
 */
@WebFilter(urlPatterns = "/*")
public class LoginSessionFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;        
        String uri = request.getRequestURI().toLowerCase();

        if (request.getSession().getAttribute("username") != null || uri.contains("/register")) {            
            fc.doFilter(sr, sr1);
        } else {
            request.getRequestDispatcher("index").forward(sr, sr1);
            
        }
    }

    @Override
    public void destroy() {

    }

}
