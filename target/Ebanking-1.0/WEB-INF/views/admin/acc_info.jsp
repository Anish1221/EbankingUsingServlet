<%-- 
    Document   : acc_manager
    Created on : Dec 14, 2017, 1:02:50 PM
    Author     : acer
--%>

<%@page import="com.example.ebankingservlet.entity.CustomerInfo"%>
<%@page import="com.example.ebankingservlet.dao.impl.AdminDAOImpl"%>
<%@page import="com.example.ebankingservlet.dao.AdminDAO"%>

<%@include file="../../shared/header.jspf" %>
<%@include file="admin_nav.jsp" %>

<h1>Customer Database</h1>
<table class="table table-bordered table-striped table-hover" style="margin-top: 20px">
    <tr>
        <td>Account No.</td>        
        <td>Name</td>        
        <td>Gender</td>
        <td>DOB</td>
        <td>Email</td>
        <td>Contact No.</td>
        <td>Address</td>
        <td>Status</td>
        <td>Balance</td>
    </tr>

    <%
        AdminDAO adminDAO = new AdminDAOImpl();
        for (CustomerInfo c : adminDAO.getInfo()) {

    %>

    <tr>
        <td><%=c.getAccount().getAccountNo()%></td>             
        <td><%=c.getCustomer().getFirstName()%> <%=c.getCustomer().getLastName()%></td>        
        <td><%=c.getCustomer().getGender()%></td>
        <td><%=c.getCustomer().getDob()%></td>
        <td><%=c.getCustomer().getEmail()%></td>
        <td><%=c.getCustomer().getContactNo()%></td>
        <td><%=c.getCustomer().getAddress()%></td>
        <td><%=c.getCustomer().getStatus()%></td>
        <td><%=c.getAccount().getBalance()%></td>

    </tr>

    <%
        }
    %>


</table>

<%@include file="../../shared/footer.jspf" %>
