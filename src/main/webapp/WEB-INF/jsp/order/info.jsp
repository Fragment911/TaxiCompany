<%@ include file="/webresources/header.jspf"%>
    <h1>Order</h1>
    <table class="table table-striped table-dark">
        <tbody>
            <tr>
                <td><b>Comment</b></td>
                <td>${order.comment}</td>
            </tr>
            <tr>
                <td><b>Location</b></td>
                <td>${order.location}</td>
            </tr>
            <tr>
                <td><b>Target</b></td>
                <td>${order.target}</td>
            </tr>
            <tr>
                <td><b>Price</b></td>
                <td>${order.price}</td>
            </tr>
            <tr>
                <td><b>Mark</b></td>
                <td>${order.mark}</td>
            </tr>
            <tr>
                <td><b>Passenger</b></td>
                <td>${order.passenger.login} ${order.passenger.firstname} ${order.passenger.lastname}</td>
            </tr>
            <tr>
                <td><b>Driver</b></td>
                <c:if test="${order.driver != null}">
                    <td>${order.driver.login} ${order.driver.firstname} ${order.driver.lastname}</td>
                </c:if>
                <c:if test="${order.driver == null}">
                    <td>Free</td>
                </c:if>
            </tr>
            <tr>
                <td><b>Car</b></td>
                <c:if test="${order.car != null}">
                    <td>${order.car.number}</td>
                </c:if>
                <c:if test="${order.car == null}">
                    <td>Free</td>
                </c:if>
            </tr>
            <tr>
                <td><b>Status</b></td>
                <td>${order.statusOrder}</td>
            </tr>
        </tbody>
    </table>
    <c:if test="${order.statusOrder.equals('AWAIT') && role.contains('DRIVER') && !hasOrder}">
        <a href="${pageContext.request.contextPath}/order/take/${order.id}" class="btn btn-warning"><i class="fa fa-share fa-fw"></i>Take</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/order/${back}" class="btn btn-outline-secondary"><i class="fa fa-reply fa-fw"></i>Back</a>
<%@ include file="/webresources/footer.jspf"%>
