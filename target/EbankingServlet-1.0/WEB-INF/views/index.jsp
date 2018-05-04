
<%@include file="../shared/header.jspf" %>

<div class="row">
    <div class="col-sm-8">        
        <img src="http://www.depensez.com/wp-content/uploads/2017/01/banque-en-ligne.jpg" height="500px" width="820px" alt="Bank Homepage"/>        
    </div>
    <div class="col-sm-3" >
        <p class="text-center" style="color: green">${message}</p>
        <h3>Login</h3>
        <form method="post" action="index">
            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" class="form-control" required="required">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" class="form-control" required="required">
            </div>
            <div class="form-group">
                <button type="submit" id="submit" class="btn btn-success form-control" >Sign In</button>
            </div>
        </form>
        <p class="text-center">No Account Yet? <a href="register">Sign Up</a></p>
        <p class="text-center" style="color: red">${error}</p>
    </div>

</div>

<%@include file="../shared/footer.jspf" %>