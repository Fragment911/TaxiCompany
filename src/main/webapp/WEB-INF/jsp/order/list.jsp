<%@ include file="/webresources/header.jspf"%>
    <h1>${title}</h1>
    <table class="table table-striped table-dark">
        <thead>
            <tr>
                <th>Comment</th>
                <th>Location</th>
                <th>Target</th>
                <th>Price</th>
                <c:if test="${status.equals('DONE')}">
                    <th>Mark</th>
                </c:if>
                <th>Passenger</th>
                <th>Driver</th>
                <th>Car</th>
                <th width="100">Status</th>
                <c:if test="${!status.equals('DONE') || !role.contains('PASSENGER')}">
                    <th width="50"></th>
                </c:if>
                <c:if test="${status.equals('DONE') && role.contains('PASSENGER')}">
                    <th width="101"></th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var = "order" items="${orderList}">
                <tr>
                    <td>${order.comment}</td>
                    <td>${order.location}</td>
                    <td>${order.target}</td>
                    <td>${order.price}</td>
                    <c:if test="${order.statusOrder.equals('DONE')}">
                        <td>${order.mark}</td>
                    </c:if>
                    <td>${order.passenger.login}</td>
                    <c:if test="${order.driver != null}">
                        <td>${order.driver.login}</td>
                    </c:if>
                    <c:if test="${order.driver == null}">
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
                        <c:if test="${role.contains('ADMIN') || role.contains('MODER')}">
                            <a href="${pageContext.request.contextPath}/order/${order.id}" class="btn btn-primary btn-sm" title="Info"><i class="fa fa-info-circle fa-fw"></i></a>
                        </c:if>
                        <c:if test="${!order.statusOrder.equals('AWAIT') && role.contains('PASSENGER')}">
                            <a href="${pageContext.request.contextPath}/order/${order.id}" class="btn btn-primary btn-sm" title="Info"><i class="fa fa-info-circle fa-fw"></i></a>
                        </c:if>
                        <c:if test="${order.statusOrder.equals('AWAIT') && role.contains('PASSENGER')}">
                            <a href="${pageContext.request.contextPath}/order/update/${order.id}" class="btn btn-warning btn-sm" title="Edit"><i class="fa fa-pencil-square fa-fw"></i></a>
                        </c:if>
                        <c:if test="${order.statusOrder.equals('DONE') && role.contains('PASSENGER')}">
                            <a href="${pageContext.request.contextPath}/order/mark/${order.id}" class="btn btn-warning btn-sm" title="Mark"><i class="fa fa-check-square fa-fw"></i></a>
                        </c:if>
                        <c:if test="${!order.statusOrder.equals('AWAIT') && role.contains('DRIVER')}">
                            <a href="${pageContext.request.contextPath}/order/${order.id}" class="btn btn-primary btn-sm" title="Info"><i class="fa fa-info-circle fa-fw"></i></a>
                        </c:if>
                        <c:if test="${order.statusOrder.equals('AWAIT') && role.contains('DRIVER') && hasOrder}">
                            <a href="${pageContext.request.contextPath}/order/${order.id}" class="btn btn-primary btn-sm" title="Info"><i class="fa fa-info-circle fa-fw"></i></a>
                        </c:if>
                        <c:if test="${order.statusOrder.equals('AWAIT') && role.contains('DRIVER') && !hasOrder}">
                            <a href="${pageContext.request.contextPath}/order/take/${order.id}" class="btn btn-warning btn-sm" title="Take"><i class="fa fa-share fa-fw"></i></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${role.contains('PASSENGER') && !hasOrder}">
        <a href="${pageContext.request.contextPath}/order/create" class="btn btn-success"><i class="fa fa-plus fa-fw"></i>New order</a>
    </c:if>
<%@ include file="/webresources/footer.jspf"%>
