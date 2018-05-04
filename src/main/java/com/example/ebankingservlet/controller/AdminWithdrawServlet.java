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
@WebServlet(urlPatterns = "/withdraw")
public class AdminWithdrawServlet extends HttpServlet {

    private SystemDAO sysDAO = new SystemDAOImpl();
    private Transaction transaction = new Transaction();
    private TransactionDAO transDAO = new TransactionDAOImpl();
    private Account account = new Account();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/admin/withdraw.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String accountNo = request.getParameter("account_no");
            String confirmAcc = request.getParameter("confirm_account_no");
            double transactionAmount = Double.parseDouble(request.getParameter("withdraw_amt"));
            double newBalance = sysDAO.getBalance(accountNo) - transactionAmount;

            if (sysDAO.checkByAccountNo(accountNo, "tbl_account") == true) {
                if (accountNo.equals(confirmAcc)) {
                    if (sysDAO.getBalance(accountNo) >= transactionAmount) {
                        transaction.setAccountNo(accountNo);
                        transaction.setTransactionAmount(transactionAmount);
                        transaction.setTransactionType("Withdraw");
                        transaction.setAvailableBalance(newBalance);
                        account.setAccountNo(accountNo);

                        transDAO.insertTransation(transaction);
                        transDAO.updateBalance(account, newBalance);

                        request.setAttribute("success", "Successful Withdrawal of Rs. " + transactionAmount + " from Account " + accountNo + "");
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
                request.setAttribute("message", "Account no. invalid");
                doGet(request, response);
            }
        } catch (Exception e) {

        }
    }

}
