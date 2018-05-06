
<%@page import="com.example.ebankingservlet.dao.impl.SystemDAOImpl"%>
<%@page import="com.example.ebankingservlet.dao.SystemDAO"%>
<%@page import="java.util.Random"%>

<%@include file="../../shared/header.jspf" %>

<h1>Add Account Info.</h1>

<form method="post" action="add-account">
    <div class="form-group">
        <label>Account No.</label>
        <input type="text" name="account_no" readonly="readonly" class="form-control"
               value="<%
                        SystemDAO sysDAO = new SystemDAOImpl();
                        out.println(sysDAO.generateAccount());
                        
                        %>" style="color: blue">
    </div>
    <div class="form-group">
        <label>Account Balance</label>
        <input type="number" name="balance" required="required" class="form-control">
    </div>
    <div class="form-group">
        <button class="submit btn btn-md btn-success" type="submit" name="balance" class="form-control">Submit</button>
    </div>
</form>

<%@include file="../../shared/footer.jspf" %>