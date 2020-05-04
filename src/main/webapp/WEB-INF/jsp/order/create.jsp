<%@ include file="/webresources/header.jspf"%>
    <h1>New order</h1>
    <form role="form" action="${contextPath}/order/create" method="POST">
        <div class="form-group">
            <label for="location">Location</label>
            <input class="form-control" type="text" name="location" path="location" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="target">Target</label>
            <input class="form-control" type="text" name="target" path="target" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="comment">Comment</label>
            <input class="form-control" type="text" name="comment" path="comment" autocomplete="off" />
        </div>
        <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Save</button>
        <a href="${pageContext.request.contextPath}/order/awaitlist" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Cancel</a>
    </form>
<%@ include file="/webresources/footer.jspf"%>
