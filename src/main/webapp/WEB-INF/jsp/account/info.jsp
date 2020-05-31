<%@ include file="/webresources/header.jspf"%>
    <h1>Account</h1>
    <table class="table table-striped table-dark">
        <tbody>
            <tr>
                <td>Login</td>
                <td>${account.login}</td>
            </tr>
            <tr>
                <td>Firstname</td>
                <td>${account.firstname}</td>
            </tr>
            <tr>
                <td>Lastname</td>
                <td>${account.lastname}</td>
            </tr>
            <tr>
                <td>Phone</td>
                <td>${account.phone}</td>
            </tr>
            <tr>
                <td>Rating</td>
                <td>${account.rating}</td>
            </tr>
            <tr>
                <td>Status</td>
                <td>${account.statusUser}</td>
            </tr>
            <tr>
                <td>Role</td>
                <td>${account.role}</td>
            </tr>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/account/update/${account.id}" class="btn btn-warning"><i class="fa fa-pencil-square fa-fw"></i>Update</a>
    <a href="${pageContext.request.contextPath}/account" class="btn btn-outline-secondary"><i class="fa fa-reply fa-fw"></i>Back</a>
<%@ include file="/webresources/footer.jspf"%>
