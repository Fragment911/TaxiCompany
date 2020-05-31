<%@ include file="/webresources/header.jspf"%>
    <h1>Accounts</h1>
    <table class="table table-striped table-dark">
        <thead>
            <tr>
                <th>Login</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Phone</th>
                <th width="50">Rating</th>
                <th width="90">Status</th>
                <th width="150">Role</th>
                <th width="101"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var = "account" items="${accountList}">
                <tr>
                    <td>${account.login}</td>
                    <td>${account.firstname}</td>
                    <td>${account.lastname}</td>
                    <td>${account.phone}</td>
                    <td>${account.rating}</td>
                    <td>${account.statusUser}</td>
                    <td>${account.role}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/account/${account.id}" class="btn btn-primary btn-sm" title="Info"><i class="fa fa-info-circle fa-fw"></i></a>
                        <a href="${pageContext.request.contextPath}/account/update/${account.id}" class="btn btn-warning btn-sm" title="Edit"><i class="fa fa-pencil-square fa-fw"></i></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<%@ include file="/webresources/footer.jspf"%>
