<%-- 
    Document   : add_account
    Created on : Dec 14, 2017, 1:35:30 PM
    Author     : acer
--%>

<%@include file="../../shared/header.jspf" %>
<%@include file="admin_nav.jsp" %>

<div class="col-md-8 col-md-offset-2">
    <h3 style="margin-bottom: 30px">Add Customer Account</h3>
    <p class="text-center" style="color: red">${error}</p>
    <form method="post" action="add-customer">

        <div class="form-group">
            <label>Name</label>
            <input type="text" name="first_name"  placeholder="First Name" class="form-control" required="required">
            <label></label>
            <input type="text" name="last_name" placeholder="Last Name" class="form-control" required="required">
        </div>
        <div class="form-group">
            <label>Gender</label>
            <input type="radio" name="gender" value="male" checked> Male
            <input type="radio" name="gender" value="female" > Female
        </div>
        <div class="form-group">
            <label>Date of Birth: </label>
            <input type="date" name="bday" class="form-control" required="required">
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required="required">
        </div>
        <div class="form-group">
            <label>Contact No.</label>
            <input type="number" name="contact_no" class="form-control" required="required">
        </div>
        <div class="form-group">
            <label>Address</label>
            <input type="text" name="address" class="form-control" required="required">
        </div>
        <div class="form-group">
            <label>Status</label>
            <label><input type="checkbox" name="status"/>Active</label>
        </div>
        <button type="submit" class="btn btn-success btn-md">Add Account</button>

    </form>
</div>
<%@include file="../../shared/footer.jspf" %>