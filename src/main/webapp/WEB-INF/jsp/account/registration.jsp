<%@ include file="/webresources/header.jspf"%>
       <h1>Sign up</h1>
    <form role="form" method="POST">
        <div class="form-group">
            <label for="login">Login</label>
            <input class="form-control" type="text" name="login" path="login" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="firstname">Firstname</label>
            <input class="form-control" type="text" name="firstname" path="firstname" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="lastname">Lastname</label>
            <input class="form-control" type="text" name="lastname" path="lastname" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="phone">Phone</label>
            <input class="form-control" type="text" name="phone" path="phone" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input class="form-control" type="text" name="password" path="password" autocomplete="off" />
        </div>
        <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Save</button>
    </form>
<%@ include file="/webresources/footer.jspf"%>
