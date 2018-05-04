<%-- 
    Document   : home
    Created on : Dec 10, 2017, 11:11:03 PM
    Author     : acer
--%>

<%@include file="../../shared/header.jspf" %>

<%@include file="customer_nav.jsp" %>

<div class="row">
    <div class="col-md-8 col-md-offset-2">
        <div class="jumbotron">
            <h5> Welcome, ${username}</h5>
            <br />
            <br />
            <h4>Account Details</h4>
            <hr>
            <h5>Account no.: ${accountNo}</h5>
            <h5>Current Balance: Rs. ${balance}</h5>
        </div>
    </div>
</div>

<%@include file="../../shared/footer.jspf" %>
