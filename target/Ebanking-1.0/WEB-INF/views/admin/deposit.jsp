<%-- 
    Document   : deposit
    Created on : Jan 8, 2018, 12:44:29 PM
    Author     : acer
--%>

<%@include file="../../shared/header.jspf" %>
<%@include file="admin_nav.jsp" %>

<div class="row">
    <div class="col-md-8 col-md-offset-2">
        <div class="jumbotron">            
            <h4>Deposit Transaction</h4>
            <hr>
            <form method="post" action="deposit">
                <div class="form-group">
                    <label>Account No.</label>
                    <input type="text" name="account_no" required="required" class="form-control">
                </div>
                <div class="form-group">
                    <label>Confirm Account No.</label>
                    <input type="text" name="confirm_account_no" required="required" class="form-control">
                </div>
                <div class="form-group">
                    <label>Deposit Amount</label>
                    <input type="number" name="deposit_amt" required="required" class="form-control">
                </div>
                <button type="submit" class="btn btn-success btn-md">Deposit</button>
            </form>
            <p class="text-center" style="color: green">${success}</p>
            <p class="text-center" style="color: red">${message}</p>
        </div>
    </div>
</div>

<%@include file="../../shared/footer.jspf" %>