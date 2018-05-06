
<%@include file = "../../shared/header.jspf" %>
<%@include file = "customer_nav.jsp" %>


<h1>Transaction Activity</h1>
<table class="table table-bordered table-striped table-hover" style="margin-top: 20px">
    <tr>
        <th>Account No</th>        
        <th>Date of Transaction</th>        
        <th>Transaction Type</th>
        <th>Transaction Amount</th>
        <th>Available Balance</th>
    </tr>

    <c:forEach items="${transaction}" var="t">
        <tr>
            <td>${t.accountNo}</td>             
            <td>${t.transactionDate}</td>        
            <td>${t.transactionType}</td>
            <td>${t.transactionAmount}</td>
            <td>${t.availableBalance}</td>        
        </tr>
    </c:forEach>
</table>

<%@include file="../../shared/footer.jspf" %>
