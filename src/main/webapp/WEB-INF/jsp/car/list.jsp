<%@ include file="/webresources/header.jspf"%>
    <h1>Car list</h1>
    <table class="table table-striped table-dark">
        <thead>
            <tr>
                <th>Number</th>
                <th>Driver</th>
                <th>Type</th>
                <th width="50"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var = "car" items="${carList}">
                <tr>
                    <td>${car.number}</td>
                    <c:if test="${car.account != null}">
                        <td>${car.account.login}</td>
                    </c:if>
                    <c:if test="${car.account == null}">
                        <td>Free</td>
                    </c:if>
                    <td width="100">${car.carType}</td>
                    <c:if test="${role.contains('ADMIN') || role.contains('MODER')}">
                        <td>
                            <a href="${pageContext.request.contextPath}/car/update/${car.id}" class="btn btn-warning btn-sm"><i class="fa fa-pencil-square fa-fw"></i></a>
                        </td>
                    </c:if>
                    <c:if test="${role.contains('DRIVER') || role.contains('PASSENGER')}">
                        <td>
                            <a href="${pageContext.request.contextPath}/car/${car.id}" class="btn btn-primary btn-sm"><i class="fa fa-info-circle fa-fw"></i></a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${role.contains('ADMIN') || role.contains('MODER')}">
        <a href="${pageContext.request.contextPath}/car/create" class="btn btn-success"><i class="fa fa-plus fa-fw"></i>Add</a>
    </c:if>
<%@ include file="/webresources/footer.jspf"%>
