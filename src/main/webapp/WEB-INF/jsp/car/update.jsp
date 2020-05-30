<%@ include file="/webresources/header.jspf"%>
    <div class="panel-heading">
        <h1>Update car</h1>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-lg-12">
                <form role="form" action="${contextPath}/car/update" method="POST">
                    <div class="form-group" style = "display: none">
                        <label for="id">Id</label>
                        <input class="form-control" type="number" name="id" readonly value="${car.id}" autocomplete="off" />
                    </div>
                    <div class="form-group">
                        <label for="number">Number</label>
                        <input class="form-control" type="text" name="number" value="${car.number}" autocomplete="off" />
                    </div>
                    <div class="form-group">
                        <label for="carType">Car type</label>
                        <select path="carType" name="carType" class="form-control" id="carType">
                            <c:forEach var = "carType" items="${carTypeList}">
                                <c:if test="${not (carType.name().equals(car.carType))}">
                                    <option>${carType.name()}</option>
                                </c:if>
                                <c:if test="${carType.name().equals(car.carType)}">
                                    <option selected>${carType.name()}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="account">Account</label>
                        <select path="account" name="account" class="form-control" id="account">
                            <option value="null">Free</option>
                            <c:forEach var = "account" items="${accountList}">
                                <c:if test="${account.id != car.account.id}">
                                    <option value="${account.id}">${account.login}</option>
                                </c:if>
                                <c:if test="${account.id == car.account.id}">
                                    <option value="${account.id}" selected>${account.login}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                    <h2>Car`s options</h2>
                    <table class="table table-striped table-dark">
                        <thead>
                            <tr>
                                <th>Text</th>
                                <th width="50"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var = "option" items="${optionList}">
                                <tr>
                                    <td>${option.text}</td>
                                    <td><a href="${pageContext.request.contextPath}/car/removeOption/${car.id}/${option.id}" class="btn btn-danger btn-sm"><i class="fa fa-times fa-fw"></i></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <table class="table table-striped table-dark">
                        <h2>Other options</h2>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Text</th>
                                <th width="50"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var = "option" items="${optionList1}">
                                <tr>
                                    <td>${option.id}</td>
                                    <td>${option.text}</td>
                                    <td><a href="${pageContext.request.contextPath}/car/addOption/${car.id}/${option.id}" class="btn btn-success btn-sm"><i class="fa fa-plus fa-fw"></i></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Save</button>
                    <a href="${pageContext.request.contextPath}/car" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Cancel</a>
                </form>
            </div>
        </div>
    </div>
<%@ include file="/webresources/footer.jspf"%>
