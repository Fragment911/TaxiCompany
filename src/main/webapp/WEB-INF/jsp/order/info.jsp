<%@ include file="/webresources/header.jspf"%>
    <h1>Order</h1>
    <table class="table table-striped">
        <tbody>
            <tr>
                <td>Id</td>
                <td>${order.id}</td>
            </tr>
            <tr>
                <td>Comment</td>
                <td>${order.comment}</td>
            </tr>
            <tr>
                <td>Location</td>
                <td>${order.location}</td>
            </tr>
            <tr>
                <td>Target</td>
                <td>${order.target}</td>
            </tr>
            <tr>
                <td>Price</td>
                <td>${order.price}</td>
            </tr>
            <tr>
                <td>Passenger</td>
                <td>${order.account.login}</td>
            </tr>
            <tr>
                <td>Car</td>
                <td>${order.car}</td>
            </tr>
            <tr>
                <td>Status ${back}</td>
                <td>${order.statusOrder}</td>
            </tr>
        </tbody>
    </table>
<c:if test="${order.statusOrder.equals('AWAIT') && role.contains('DRIVER')}">
    <a href="${pageContext.request.contextPath}/order/take/${order.id}" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Take</a>
</c:if>
    <a href="${pageContext.request.contextPath}/order/${back}" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Back</a>
<%@ include file="/webresources/footer.jspf"%>
