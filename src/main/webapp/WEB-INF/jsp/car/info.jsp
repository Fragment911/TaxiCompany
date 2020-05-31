<%@ include file="/webresources/header.jspf"%>
    <h1>Car</h1>
    <table class="table table-striped table-dark">
        <tbody>
            <tr>
                <td><b>Number</b></td>
                <td>${car.number}</td>
            </tr>
            <tr>
                <td><b>Driver</b></td>
                <c:if test="${car.account != null}">
                    <td>${car.account.login} ${car.account.firstname} ${car.account.lastname}</td>
                </c:if>
                <c:if test="${car.account == null}">
                    <td>Free</td>
                </c:if>
            </tr>
            <tr>
                <td><b>Type</b></td>
                <td>${car.carType}</td>
            </tr>
        </tbody>
    </table>

    <h2>Car`s options</h2>
    <table class="table table-striped table-dark">
        <thead>
        <tr>
            <th>Text</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "option" items="${carOptionList}">
            <tr>
                <td>${option.text}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${role.contains('ADMIN') || role.contains('MODER')}">
        <a href="${pageContext.request.contextPath}/car/update/${car.id}" class="btn btn-warning"><i class="fa fa-pencil-square fa-fw"></i>Update</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/car" class="btn btn-outline-secondary"><i class="fa fa-reply fa-fw"></i>Back</a>
<%@ include file="/webresources/footer.jspf"%>
