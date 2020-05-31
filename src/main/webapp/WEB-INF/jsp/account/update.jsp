<%@ include file="/webresources/header.jspf"%>
    <h1>Update account ${account.login} (${account.firstname} ${account.lastname})</h1>
    <form role="form" action="${contextPath}/account/update" method="POST">
        <div class="form-group" style = "display: none">
            <label for="id">Id</label>
            <input class="form-control" type="number" name="id" readonly value="${account.id}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="login">Login</label>
            <input class="form-control" type="text" name="login" readonly value="${account.login}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="role">Role list</label>
            <select path="role" name="role" class="form-control" id="role">
                <c:forEach var="role" items="${roleList}">
                    <c:if test="${!account.role.contains(role.name())}">
                        <option>ROLE_${role.name()}</option>
                    </c:if>
                    <c:if test="${account.role.contains(role.name())}">
                        <option selected>ROLE_${role.name()}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="statusUser">Status list</label>
            <select path="statusUser" name="statusUser" class="form-control" id="statusUser">
                <c:forEach var="statusUser" items="${statusList}">
                    <c:if test="${not (statusUser.name().equals(account.statusUser))}">
                        <option>${statusUser.name()}</option>
                    </c:if>
                    <c:if test="${statusUser.name().equals(account.statusUser)}">
                        <option selected>${statusUser.name()}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Save</button>
        <a href="${pageContext.request.contextPath}/account" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Cancel</a>
    </form>
<%@ include file="/webresources/footer.jspf"%>