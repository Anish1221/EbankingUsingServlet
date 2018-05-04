<%-- 
    Document   : transfer
    Created on : Jan 8, 2018, 12:44:47 PM
    Author     : acer
--%>

<%@include file="../../shared/header.jspf" %>
<%@include file="customer_nav.jsp" %>

<div class="row">
    <div class="col-md-8 col-md-offset-2">
        <div class="jumbotron">            
            <h4>Transfer Transaction</h4>
            <hr>
            <form method="post" action="transfer">
                
                <div class="form-group">
                    <label>Destination Account No.</label>
                    <input type="text" name="dest_account_no" required="required" class="form-control">
                </div>
                <div class="form-group">
                    <label>Confirm Destination Account No.</label>
                    <input type="text" name="confirm_dest_account_no" required="required" class="form-control">
                </div>
                <div class="form-group">
                    <label>Transfer Amount</label>
                    <input type="number" name="transfer_amt" required="required" class="form-control">
                </div>
                <button type="submit" class="btn btn-success btn-md">Transfer</button>
            </form>
            <p class="text-center" style="color: green">${success}</p>
            <p class="text-center" style="color: red">${message}</p>
        </div>
    </div>
</div>


<%@include file="../../shared/footer.jspf" %>

