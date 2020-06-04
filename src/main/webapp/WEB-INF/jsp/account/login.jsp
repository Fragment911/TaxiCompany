<%@ include file="/webresources/header.jspf"%>
       <h1>Sign in</h1>
    <form role="form" method="POST">
        <div class="form-group">
            <label for="username">User</label>
            <input class="form-control" type="text" name="username" path="username" autofocus />
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input class="form-control" type="password" name="password" path="password" />
        </div>
        <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Login</button>
    </form>
<%@ include file="/webresources/footer.jspf"%>
