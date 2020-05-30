<%@ include file="/webresources/header.jspf"%>
    <h1>Car list</h1>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Number</th>
                <th>Type</th>
                <th>Driver</th>
                <c:if test="${role.contains('ADMIN') || role.contains('MODER')}">
                    <th width="140"></th>
                </c:if>
                <c:if test="${role.contains('DRIVER') || role.contains('PASSENGER')}">
                    <th width="50"></th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var = "car" items="${carList}">
                <tr>
                    <td>${car.number}</td>
                    <td>${car.carType}</td>
                    <c:if test="${car.account != null}">
                        <td>${car.account.login}</td>
                    </c:if>
                    <c:if test="${car.account == null}">
                        <td>Free</td>
                    </c:if>
                    <c:if test="${role.contains('ADMIN') || role.contains('MODER')}">
                        <td>
                            <a href="${pageContext.request.contextPath}/car/${car.id}" class="btn btn-primary btn-sm"><i class="fa fa-info-circle fa-fw"></i></a>
                            <a href="${pageContext.request.contextPath}/car/update/${car.id}" class="btn btn-warning btn-sm"><i class="fa fa-pencil-square fa-fw"></i></a>
                            <a data-toggle="modal" data-target="#confirm_delete_${car.id}" href="#" class="btn btn-danger btn-sm"><i class="fa fa-trash fa-fw"></i></a>
                            <div class="modal fade" id="confirm_delete_${car.id}" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title">Confirmation</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p>Are you sure to delete car?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <form action="${pageContext.request.contextPath}/car/remove/${car.id}" method="DELETE">
                                                <a href="#" class="btn" data-dismiss="modal">Cancel</a><button type="submit" class="btn btn-primary">Confirm</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
