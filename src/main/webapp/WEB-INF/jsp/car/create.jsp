<%@ include file="/webresources/header.jspf"%>
    <h1>New car</h1>
    <form role="form" action="${contextPath}/car/create" method="POST">
        <div class="form-group">
            <label for="number">Number</label>
            <input class="form-control" type="text" name="number" path="number" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="carType">Car type</label>
            <select path="carType" name="carType" class="form-control" id="carType">
                <c:forEach var="carType" items="${carTypeList}">
                    <option value="${carType.name()}">${carType.getDescription()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="account">Driver</label>
            <select path="account" name="account" class="form-control" id="account">
                <option value="${null}">Free</option>
                <c:forEach var="account" items="${accountList}">
                    <option value="${account.id}">${account.login}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Save</button>
        <a href="${pageContext.request.contextPath}/car" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Cancel</a>
    </form>
<%@ include file="/webresources/footer.jspf"%>
