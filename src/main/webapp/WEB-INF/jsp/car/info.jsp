<%@ include file="/webresources/header.jspf"%>
    <h1>Account</h1>
    <table class="table table-striped">
        <tbody>
            <tr>
                <td>Id</td>
                <td>${car.id}</td>
            </tr>
            <tr>
                <td>Number</td>
                <td>${car.number}</td>
            </tr>
            <tr>
                <td>Account</td>
                <td>${car.account}</td>
            </tr>
            <tr>
                <td>Type</td>
                <td>${car.carType}</td>
            </tr>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/car/update/${car.id}" class="btn btn-warning"><i class="fa fa-pencil-square fa-fw"></i>Update</a>
    <a href="${pageContext.request.contextPath}/car" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Back</a>
<%@ include file="/webresources/footer.jspf"%>
