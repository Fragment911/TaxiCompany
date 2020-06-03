<%@ include file="/webresources/header.jspf"%>
<h1>Take order</h1>
<form role="form" action="${contextPath}/order/mark" method="POST">
    <div class="form-group" style = "display: none">
        <label for="id">Id</label>
        <input class="form-control" type="number" name="id" readonly value="${order.id}" autocomplete="off" />
    </div>
    <div class="form-group">
        <label for="mark">Mark</label>
        <input class="form-control" type="number" name="mark" value="${order.mark}" step="1" min="1" max="5" autocomplete="off" />
    </div>
    <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Save</button>
    <a href="${pageContext.request.contextPath}/order/${back}" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Cancel</a>
</form>
<%@ include file="/webresources/footer.jspf"%>
