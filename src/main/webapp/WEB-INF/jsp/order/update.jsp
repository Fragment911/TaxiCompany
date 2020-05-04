<%@ include file="/webresources/header.jspf"%>
    <h1>Update order</h1>
    <div class="form-group">
        <c:if test="${role.contains('DRIVER') && order.statusOrder.equals('AWAIT')}">
            <a href="${pageContext.request.contextPath}/order/run/${order.id}" class="btn btn-primary"><i class="fa fa-share fa-fw"></i>Run</a>
        </c:if>
        <c:if test="${(role.contains('ADMIN') || role.contains('MODER') || role.contains('DRIVER')) && order.statusOrder.equals('RUN')}">
            <a href="${pageContext.request.contextPath}/order/done/${order.id}" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Done</a>
        </c:if>
        <c:if test="${(role.contains('ADMIN') || role.contains('MODER') || role.contains('PASSENGER')) && order.statusOrder.equals('AWAIT')}">
            <a href="${pageContext.request.contextPath}/order/cancel/${order.id}" class="btn btn-warning"><i class="fa fa-reply fa-fw"></i>Cancel</a>
        </c:if>
        <c:if test="${(role.contains('ADMIN') || role.contains('MODER') || role.contains('PASSENGER')) && order.statusOrder.equals('RUN')}">
            <a href="${pageContext.request.contextPath}/order/fail/${order.id}" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Fail</a>
        </c:if>
    </div>
    <form role="form" action="${contextPath}/order/update" method="POST">
        <div class="form-group">
            <label for="id">Id</label>
            <input class="form-control" type="number" name="id" readonly value="${order.id}" autocomplete="off" />
        </div>
        <c:if test="${role.contains('PASSENGER')}">
            <div class="form-group">
                <label for="comment">Comment</label>
                <input class="form-control" type="text" name="comment" value="${order.comment}" autocomplete="off" />
            </div>
            <div class="form-group">
                <label for="location">Location</label>
                <input class="form-control" type="text" name="location" value="${order.location}" autocomplete="off" />
            </div>
            <div class="form-group">
                <label for="target">Target</label>
                <input class="form-control" type="text" name="target" value="${order.target}" autocomplete="off" />
            </div>
        </c:if>
        <c:if test="${role.contains('ADMIN') || role.contains('MODER') || role.contains('DRIVER')}">
            <div class="form-group">
                <label for="price">Price</label>
                <input class="form-control" type="number" name="price" value="${order.price}" step="0.5" autocomplete="off" />
            </div>
        </c:if>
        <c:if test="${role.contains('ADMIN') || role.contains('MODER')}">
            <div class="form-group">
                <label for="car">Car</label>
                <select path="car" name="car" class="form-control" id="car">
                    <option value="null">Free</option>
                    <c:forEach var = "car" items="${carList}">
                        <c:if test="${car.id != order.car.id}">
                            <option value="${car.id}">${car.number}</option>
                        </c:if>
                        <c:if test="${car.id == order.car.id}">
                            <option value="${car.id}" selected>${car.number}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </c:if>
        <c:if test="${order.statusOrder.contains('AWAIT')}">
            <c:set var="statusNum" value="1"/>
        </c:if>
        <c:if test="${order.statusOrder.contains('RUN')}">
            <c:set var="statusNum" value="2"/>
        </c:if>
        <c:if test="${order.statusOrder.contains('DONE')}">
            <c:set var="statusNum" value="3"/>
        </c:if>
        <c:if test="${order.statusOrder.contains('CANCELLED')}">
            <c:set var="statusNum" value="4"/>
        </c:if>
        <c:if test="${order.statusOrder.contains('FAILED')}">
            <c:set var="statusNum" value="5"/>
        </c:if>
        <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Save</button>
        <a href="${pageContext.request.contextPath}/order/${back}" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Back</a>
    </form>
<%@ include file="/webresources/footer.jspf"%>
