<%@ include file="/webresources/header.jspf"%>
    <h1>Update order</h1>
    <div class="form-group">
        <c:if test="${role.contains('DRIVER') && order.statusOrder.equals('AWAIT')}">
            <a href="${pageContext.request.contextPath}/order/run/${order.id}" class="btn btn-primary"><i class="fa fa-share fa-fw"></i>Run order</a>
        </c:if>
        <c:if test="${role.contains('DRIVER') && order.statusOrder.equals('RUN')}">
            <a href="${pageContext.request.contextPath}/order/done/${order.id}" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Done order</a>
        </c:if>
        <c:if test="${role.contains('PASSENGER') && order.statusOrder.equals('AWAIT')}">
            <a href="${pageContext.request.contextPath}/order/cancel/${order.id}" class="btn btn-warning"><i class="fa fa-reply fa-fw"></i>Cancel order</a>
        </c:if>
    </div>
    <form role="form" action="${contextPath}/order/update" method="POST">
        <div class="form-group" style = "display: none">
            <label for="id">Id</label>
            <input class="form-control" type="number" name="id" readonly value="${order.id}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="comment">Comment</label>
            <input class="form-control" type="text" name="comment" value="${order.comment}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="location">Location</label>
            <input class="form-control" type="text" name="location" value="${order.location}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="target">Target</label>
            <input class="form-control" type="text" name="target" value="${order.target}" autocomplete="off" />
        </div>
        <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Save</button>
        <a href="${pageContext.request.contextPath}/order/${back}" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Cancel</a>
    </form>
<%@ include file="/webresources/footer.jspf"%>
