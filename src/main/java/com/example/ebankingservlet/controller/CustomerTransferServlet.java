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
import com.example.ebankingservlet.entity.Account;
import com.example.ebankingservlet.entity.Transaction;
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
@WebServlet(urlPatterns = "/transfer")
public class CustomerTransferServlet extends HttpServlet {

    private SystemDAO sysDAO = new SystemDAOImpl();
    private Transaction transaction = new Transaction();
    private TransactionDAO transDAO = new TransactionDAOImpl();
    private Account account = new Account();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/customer/transfer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getSession().getAttribute("username").toString();
            String srcAccountNo = sysDAO.getAccountNo(username);

            String destAccountNo = request.getParameter("dest_account_no");
            String confirmDest = request.getParameter("confirm_dest_account_no");
            double transactionAmount = Double.parseDouble(request.getParameter("transfer_amt"));
            double newSrcBalance = sysDAO.getBalance(srcAccountNo) - transactionAmount;
            double newDestBalance = sysDAO.getBalance(destAccountNo) + transactionAmount;

            if (sysDAO.checkByAccountNo(destAccountNo, "tbl_account") == true) {
                if (!destAccountNo.equals(srcAccountNo)) {
                    if (destAccountNo.equals(confirmDest)) {
                        if (sysDAO.getBalance(srcAccountNo) >= transactionAmount) {
                            transaction.setAccountNo(confirmDest);
                            transaction.setTransactionAmount(transactionAmount);
                            transaction.setTransactionType("Transfer from: " + srcAccountNo);
                            transaction.setAvailableBalance(newDestBalance);
                            transDAO.insertTransation(transaction);

                            account.setAccountNo(destAccountNo);
                            transDAO.updateBalance(account, newDestBalance);

                            transaction.setAccountNo(srcAccountNo);
                            transaction.setTransactionAmount(transactionAmount);
                            transaction.setTransactionType("Transfer to: " + destAccountNo);
                            transaction.setAvailableBalance(newSrcBalance);
                            transDAO.insertTransation(transaction);

                            account.setAccountNo(srcAccountNo);
                            transDAO.updateBalance(account, newSrcBalance);

                            request.setAttribute("success", "Successful Transfer of Rs. " + transactionAmount + " to Account " + confirmDest);
                            doGet(request, response);
                        } else {
                            request.setAttribute("message", "Sorry, You don't have enough Balance to proceed the transaction.");
                            doGet(request, response);
                        }
                    } else {
                        request.setAttribute("message", "Mismatched Account no.");
                        doGet(request, response);
                    }
                } else {
                    request.setAttribute("message", "You can't transfer money in own Account!:(");
                    doGet(request, response);
                }
            } else {
                request.setAttribute("message", "Account no. invalid");
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
