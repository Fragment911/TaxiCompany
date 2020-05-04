<%@ include file="/webresources/header.jspf"%>
    <h1>Order list</h1>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>Comment</th>
                <th>Location</th>
                <th>Target</th>
                <th>Price</th>
                <th>Passenger</th>
                <th>Car</th>
                <th>Status</th>
                <th width="100"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var = "order" items="${orderList}">
                <tr>
                    <td>${order.comment}</td>
                    <td>${order.location}</td>
                    <td>${order.target}</td>
                    <td>${order.price}</td>
                    <c:if test="${order.account != null}">
                        <td>${order.account.login}</td>
                    </c:if>
                    <c:if test="${order.account == null}">
                        <td>Free</td>
                    </c:if>
                    <c:if test="${order.car != null}">
                        <td>${order.car.number}</td>
                    </c:if>
                    <c:if test="${order.car == null}">
                        <td>Free</td>
                    </c:if>
                    <td>${order.statusOrder}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/order/${order.id}" class="btn btn-primary btn-sm"><i class="fa fa-info-circle fa-fw"></i></a>
                        <c:if test="${order.statusOrder.equals('AWAIT') && role.contains('DRIVER')}">
                            <a href="${pageContext.request.contextPath}/order/update/${order.id}" class="btn btn-warning btn-sm"><i class="fa fa-pencil-square fa-fw"></i></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${role.contains('PASSENGER')}">
        <a href="${pageContext.request.contextPath}/order/create" class="btn btn-success"><i class="fa fa-plus fa-fw"></i>New order</a>
    </c:if>
<%@ include file="/webresources/footer.jspf"%>
