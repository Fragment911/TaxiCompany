<%@ include file="/webresources/header.jspf"%>
    <h1>Update option</h1>
    <form role="form" action="${contextPath}/option/update" method="POST">
        <div class="form-group">
            <label for="id">Id</label>
            <input class="form-control" type="number" name="id" readonly value="${option.id}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="text">Option</label>
            <input class="form-control" type="text" name="text" value="${option.text}" autocomplete="off" />
        </div>
        <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Save</button>
        <a href="${pageContext.request.contextPath}/option" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Cancel</a>
    </form>
<%@ include file="/webresources/footer.jspf"%>
